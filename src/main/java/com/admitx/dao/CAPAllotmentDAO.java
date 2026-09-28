package com.admitx.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.CAPAllotment;
import com.admitx.model.Student;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;

public class CAPAllotmentDAO {

    private final Firestore db = FirebaseConfig.getFirestore();

    private final NotificationDAO notificationDAO =
            new NotificationDAO();

    // =========================================================
    // RESET / START NEW CAP CYCLE
    // =========================================================

    public boolean resetCAPCycle() {

        try {

            // 1. Restore every college/branch seat count from intake.
            QuerySnapshot collegeSnapshot =
                    db.collection("Colleges")
                            .get()
                            .get();

            for (QueryDocumentSnapshot college :
                    collegeSnapshot.getDocuments()) {

                Long intake = college.getLong("intake");

                if (intake == null) {
                    continue;
                }

                college.getReference()
                        .update("seatsAvailable", intake)
                        .get();
            }

            // 2. Delete all previous round allotments.
            QuerySnapshot allotmentSnapshot =
                    db.collection("CAPAllotments")
                            .get()
                            .get();

            for (QueryDocumentSnapshot allotment :
                    allotmentSnapshot.getDocuments()) {

                allotment.getReference()
                        .delete()
                        .get();
            }

            // 3. Reset publication flags for all three rounds.
            Map<String, Object> settings = new HashMap<>();
            settings.put("round1Published", false);
            settings.put("round2Published", false);
            settings.put("round3Published", false);

            db.collection("CAPSettings")
                    .document("rounds")
                    .set(settings, SetOptions.merge())
                    .get();

            System.out.println(
                    "CAP cycle reset successfully. Seats restored and old allotments cleared."
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "CAP cycle reset failed."
            );

            e.printStackTrace();

            return false;
        }
    }


    public boolean hasExistingCAPCycle() {

        try {

            QuerySnapshot snapshot =
                    db.collection("CAPAllotments")
                            .limit(1)
                            .get()
                            .get();

            return !snapshot.isEmpty();

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // ROUND 1 ALLOTMENT
    // =========================================================

    public boolean runRound1Allotment() {

        try {

            // A CAP cycle must be reset before Round 1 can be run again.
            // This prevents duplicate allotments and repeated seat deduction.
            QuerySnapshot existingCycle =
                    db.collection("CAPAllotments")
                            .limit(1)
                            .get()
                            .get();

            if (!existingCycle.isEmpty()) {
                System.out.println(
                        "CAP cycle already exists. Reset CAP before running Round 1 again."
                );
                return false;
            }

            QuerySnapshot studentSnapshot =
                    db.collection("Students")
                            .get()
                            .get();

            List<QueryDocumentSnapshot> students =
                    new ArrayList<>(studentSnapshot.getDocuments());

            // Highest CET percentile first.
            students.sort((s1, s2) ->
                    Double.compare(
                            getStudentPercentile(s2),
                            getStudentPercentile(s1)
                    )
            );

            int allottedCount = 0;
            int participantCount = 0;

            for (QueryDocumentSnapshot student : students) {

                String studentEmail = student.getId();

                // Only verified applications can participate in CAP.
                DocumentSnapshot applicationDocument =
                        db.collection("Applications")
                                .document(studentEmail)
                                .get()
                                .get();

                if (!applicationDocument.exists()) {
                    continue;
                }

                String verificationStatus =
                        applicationDocument.getString("verificationStatus");

                if (!"Verified".equalsIgnoreCase(verificationStatus)) {
                    continue;
                }

                // AcademicDetailsPage/StudentInfoDAO store CET percentile as
                // "cetPercentile" (normally a String), not "percentile".
                // The old code used student.getDouble("percentile"), which
                // made every valid student get skipped.
                double percentile = getStudentPercentile(student);

                if (percentile < 0) {
                    continue;
                }

                // Check preferences
                DocumentSnapshot preferenceDocument =
                        db.collection("StudentPreferences")
                                .document(studentEmail)
                                .get()
                                .get();

                if (!preferenceDocument.exists()) {
                    continue;
                }

                Boolean locked =
                        preferenceDocument.getBoolean("locked");

                if (locked == null || !locked) {
                    continue;
                }

                @SuppressWarnings("unchecked")
                List<?> preferences =
                        (List<?>) preferenceDocument.get("preferences");

                if (preferences == null || preferences.isEmpty()) {
                    continue;
                }

                // Sort preferences by preference number
                List<Map<?, ?>> sortedPreferences =
                        getSortedPreferences(preferences);

                participantCount++;

                Map<String, Object> allottedPreference = null;

                for (Map<?, ?> preference : sortedPreferences) {

                    String college =
                            getString(preference, "college");

                    String branch =
                            getString(preference, "branch");

                    int preferenceNumber =
                            getInt(preference, "preferenceNumber");

                    if (college.isBlank()
                            || branch.isBlank()
                            || preferenceNumber <= 0) {
                        continue;
                    }

                    DocumentSnapshot collegeDocument =
                            findCollegeDocument(college, branch);

                    if (collegeDocument == null) {
                        continue;
                    }
                 
                    Long seatsAvailable =
                                collegeDocument.getLong("seatsAvailable");

                        Double cutoff =
                                collegeDocument.getDouble("cutoff");

                        if (cutoff == null) {
                        cutoff = 0.0;
                        }

                        // Check student eligibility
                        if (percentile < cutoff) {
                        continue;
                        }

                        // Check available seats
                        if (seatsAvailable == null ||
                                seatsAvailable <= 0) {
                        continue;
                 }
                 
                    

                    // Try to reserve seat safely
                    boolean seatReserved =
                            reserveSeat(collegeDocument.getReference());

                    if (!seatReserved) {
                        continue;
                    }

                    allottedPreference = new HashMap<>();

                    allottedPreference.put(
                            "college",
                            college
                    );

                    allottedPreference.put(
                            "branch",
                            branch
                    );

                    allottedPreference.put(
                            "preferenceNumber",
                            preferenceNumber
                    );

                    allottedPreference.put(
                            "collegeDocumentId",
                            collegeDocument.getId()
                    );

                    break;
                }

                if (allottedPreference == null) {
                    Map<String, Object> round1 = new HashMap<>();
                    round1.put("college", "");
                    round1.put("branch", "");
                    round1.put("preferenceNumber", 0);
                    round1.put("percentile", percentile);
                    round1.put("status", "No Seat Allotted");
                    round1.put("upgradeStatus", "Not Allotted");
                    round1.put("decision", "Auto Continue");
                    round1.put("published", false);

                    Map<String, Object> data = new HashMap<>();
                    data.put("studentEmail", studentEmail);
                    data.put("round1", round1);

                    db.collection("CAPAllotments")
                            .document(studentEmail)
                            .set(data, SetOptions.merge())
                            .get();
                    continue;
                }

                Map<String, Object> round1 =
                        createRoundData(
                                allottedPreference,
                                percentile,
                                "Seat Allotted",
                                "",
                                "Pending"
                        );

                Map<String, Object> data =
                        new HashMap<>();

                data.put("studentEmail", studentEmail);
                data.put("round1", round1);

                db.collection("CAPAllotments")
                        .document(studentEmail)
                        .set(data, SetOptions.merge())
                        .get();

                allottedCount++;
            }

            System.out.println(
                    "Round 1 allotment completed."
            );

            System.out.println(
                    "Students allotted: " + allottedCount
            );

            return participantCount > 0;

        } catch (Exception e) {

            System.out.println(
                    "Round 1 allotment failed."
            );

            e.printStackTrace();

            return false;
        }
    }

    public boolean publishRound1() {
        return publishRound(1);
    }


    // =========================================================
    // ROUND 2 ALLOTMENT
    // =========================================================

    public boolean runRound2Allotment() {

        return runBettermentRound(
                1,
                2,
                "Seat Allotted"
        );
    }

    public boolean publishRound2() {
        return publishRound(2);
    }


    // =========================================================
    // ROUND 3 ALLOTMENT
    // =========================================================

    public boolean runRound3Allotment() {

        return runBettermentRound(
                2,
                3,
                "Final Seat Allotted"
        );
    }

    public boolean publishRound3() {
        return publishRound(3);
    }


    // =========================================================
    // COMMON BETTERMENT LOGIC
    // =========================================================

    private boolean runBettermentRound(
            int previousRound,
            int currentRound,
            String status) {

        try {

            QuerySnapshot allotmentSnapshot =
                    db.collection("CAPAllotments")
                            .get()
                            .get();

            List<QueryDocumentSnapshot> students =
                    new ArrayList<>(
                            allotmentSnapshot.getDocuments()
                    );

            String previousRoundName =
                    "round" + previousRound;

            String currentRoundName =
                    "round" + currentRound;

            students.sort((s1, s2) -> {

                Map<String, Object> round1 =
                        getMap(s1, previousRoundName);

                Map<String, Object> round2 =
                        getMap(s2, previousRoundName);

                double p1 =
                        getDouble(round1, "percentile");

                double p2 =
                        getDouble(round2, "percentile");

                return Double.compare(p2, p1);
            });

            int processed = 0;

            for (QueryDocumentSnapshot document : students) {

                String studentEmail = document.getId();

                // Never process the same student twice in the same round.
                // This prevents duplicate seat deduction/release if the counsellor
                // clicks the Run button again or retries after a partial run.
                Map<String, Object> existingCurrentRound =
                        getMap(
                                document,
                                currentRoundName
                        );

                if (existingCurrentRound != null) {
                    continue;
                }

                Map<String, Object> previousData =
                        getMap(
                                document,
                                previousRoundName
                        );

                if (previousData == null) {
                    continue;
                }

                String decision =
                        getString(
                                previousData,
                                "decision"
                        );

                String previousStatus =
                        getString(previousData, "status");

                boolean previouslyUnallotted =
                        "No Seat Allotted".equalsIgnoreCase(previousStatus)
                                || getInt(previousData, "preferenceNumber") <= 0;

                // Students without a seat automatically continue.
                // Students holding a seat continue only after choosing betterment.
                if (!previouslyUnallotted
                        && !"Betterment Requested".equalsIgnoreCase(decision)) {
                    continue;
                }

                String oldCollege =
                        getString(
                                previousData,
                                "college"
                        );

                String oldBranch =
                        getString(
                                previousData,
                                "branch"
                        );

                int oldPreference =
                        getInt(
                                previousData,
                                "preferenceNumber"
                        );

                double percentile =
                        getDouble(
                                previousData,
                                "percentile"
                        );

                if (!previouslyUnallotted
                        && (oldCollege.isBlank()
                        || oldBranch.isBlank()
                        || oldPreference <= 0)) {
                    continue;
                }

                DocumentSnapshot preferenceDocument =
                        db.collection("StudentPreferences")
                                .document(studentEmail)
                                .get()
                                .get();

                if (!preferenceDocument.exists()) {
                    continue;
                }

                @SuppressWarnings("unchecked")
                List<?> preferences =
                        (List<?>) preferenceDocument.get(
                                "preferences"
                        );

                if (preferences == null
                        || preferences.isEmpty()) {

                    continue;
                }

                List<Map<?, ?>> sortedPreferences =
                        getSortedPreferences(preferences);

                Map<String, Object> newPreference = null;

                // Check only BETTER preferences
                for (Map<?, ?> preference : sortedPreferences) {

                    int preferenceNumber =
                            getInt(
                                    preference,
                                    "preferenceNumber"
                            );

                    if (preferenceNumber <= 0) {
                        continue;
                    }

                    if (!previouslyUnallotted
                            && preferenceNumber >= oldPreference) {
                        continue;
                    }

                    String college =
                            getString(
                                    preference,
                                    "college"
                            );

                    String branch =
                            getString(
                                    preference,
                                    "branch"
                            );

                    if (college.isBlank()
                            || branch.isBlank()) {

                        continue;
                    }

                    DocumentSnapshot collegeDocument =
                            findCollegeDocument(
                                    college,
                                    branch
                            );

                    if (collegeDocument == null) {
                        continue;
                    }

                    Long seatsAvailable =
                            collegeDocument.getLong(
                                    "seatsAvailable"
                            );

                    double cutoff =
                            getDocumentDouble(
                                    collegeDocument,
                                    "cutoff"
                            );

                    // IMPORTANT:
                    // Check cutoff during betterment also
                    if (percentile < cutoff) {
                        continue;
                    }

                    if (seatsAvailable == null
                            || seatsAvailable <= 0) {

                        continue;
                    }

                    // Reserve new seat first
                    boolean newSeatReserved =
                            reserveSeat(
                                    collegeDocument.getReference()
                            );

                    if (!newSeatReserved) {
                        continue;
                    }

                    newPreference = new HashMap<>();

                    newPreference.put(
                            "college",
                            college
                    );

                    newPreference.put(
                            "branch",
                            branch
                    );

                    newPreference.put(
                            "preferenceNumber",
                            preferenceNumber
                    );

                    newPreference.put(
                            "collegeDocumentId",
                            collegeDocument.getId()
                    );

                    break;
                }

                Map<String, Object> currentRoundData;

                // =================================================
                // NO BETTER SEAT
                // =================================================

                if (newPreference == null) {

                    currentRoundData =
                            new HashMap<>();

                    currentRoundData.put(
                            "previousCollege",
                            oldCollege
                    );

                    currentRoundData.put(
                            "previousBranch",
                            oldBranch
                    );

                    currentRoundData.put(
                            "college",
                            oldCollege
                    );

                    currentRoundData.put(
                            "branch",
                            oldBranch
                    );

                    currentRoundData.put(
                            "preferenceNumber",
                            oldPreference
                    );

                    currentRoundData.put(
                            "percentile",
                            percentile
                    );

                    currentRoundData.put(
                            "status",
                            previouslyUnallotted ? "No Seat Allotted" : status
                    );

                    currentRoundData.put(
                            "upgradeStatus",
                            previouslyUnallotted ? "Not Allotted" : "Not Upgraded"
                    );

                    currentRoundData.put(
                            "decision",
                            previouslyUnallotted && currentRound < 3 ? "Auto Continue" : "Pending"
                    );

                    currentRoundData.put(
                            "published",
                            false
                    );

                } else {

                    // =================================================
                    // NEW BETTER SEAT FOUND
                    // Release old seat
                    // =================================================

                    DocumentReference oldCollegeReference =
                            findCollegeReference(
                                    oldCollege,
                                    oldBranch
                            );

                    if (!previouslyUnallotted && oldCollegeReference != null) {

                        oldCollegeReference.update(
                                "seatsAvailable",
                                FieldValue.increment(1)
                        ).get();
                    }

                    currentRoundData =
                            new HashMap<>();

                    currentRoundData.put(
                            "previousCollege",
                            oldCollege
                    );

                    currentRoundData.put(
                            "previousBranch",
                            oldBranch
                    );

                    currentRoundData.put(
                            "college",
                            newPreference.get("college")
                    );

                    currentRoundData.put(
                            "branch",
                            newPreference.get("branch")
                    );

                    currentRoundData.put(
                            "preferenceNumber",
                            newPreference.get(
                                    "preferenceNumber"
                            )
                    );

                    currentRoundData.put(
                            "percentile",
                            percentile
                    );

                    currentRoundData.put(
                            "status",
                            status
                    );

                    currentRoundData.put(
                            "upgradeStatus",
                            "Upgraded"
                    );

                    currentRoundData.put(
                            "decision",
                            "Pending"
                    );

                    currentRoundData.put(
                            "published",
                            false
                    );
                }

                Map<String, Object> update =
                        new HashMap<>();

                update.put(
                        "round" + currentRound,
                        currentRoundData
                );

                db.collection("CAPAllotments")
                        .document(studentEmail)
                        .set(
                                update,
                                SetOptions.merge()
                        )
                        .get();

                processed++;
            }

            System.out.println(
                    "Round " + currentRound
                            + " allotment completed."
            );

            System.out.println(
                    "Students processed: "
                            + processed
            );

            return processed > 0;

        } catch (Exception e) {

            System.out.println(
                    "Round allotment failed."
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // SAFE SEAT RESERVATION
    // =========================================================

    private boolean reserveSeat(
            DocumentReference collegeReference) {

        try {

            return db.runTransaction(transaction -> {

                DocumentSnapshot snapshot =
                        transaction.get(collegeReference).get();

                Long seats =
                        snapshot.getLong("seatsAvailable");

                if (seats == null || seats <= 0) {
                    return false;
                }

                transaction.update(
                        collegeReference,
                        "seatsAvailable",
                        seats - 1
                );

                return true;

            }).get();

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // FIND COLLEGE DOCUMENT
    // =========================================================

    private DocumentSnapshot findCollegeDocument(
            String college,
            String branch) {

        try {

            QuerySnapshot snapshot =
                    db.collection("Colleges")
                            .whereEqualTo(
                                    "collegeName",
                                    college
                            )
                            .whereEqualTo(
                                    "branch",
                                    branch
                            )
                            .get()
                            .get();

            if (snapshot.isEmpty()) {
                return null;
            }

            return snapshot
                    .getDocuments()
                    .get(0);

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }


    // =========================================================
    // FIND COLLEGE REFERENCE
    // =========================================================

    private DocumentReference findCollegeReference(
            String college,
            String branch) {

        DocumentSnapshot document =
                findCollegeDocument(college, branch);

        if (document == null) {
            return null;
        }

        return document.getReference();
    }


    // =========================================================
    // CREATE ROUND DATA
    // =========================================================

    private Map<String, Object> createRoundData(
            Map<String, Object> preference,
            double percentile,
            String status,
            String upgradeStatus,
            String decision) {

        Map<String, Object> round =
                new HashMap<>();

        round.put(
                "college",
                preference.get("college")
        );

        round.put(
                "branch",
                preference.get("branch")
        );

        round.put(
                "preferenceNumber",
                preference.get("preferenceNumber")
        );

        round.put(
                "percentile",
                percentile
        );

        round.put(
                "status",
                status
        );

        if (upgradeStatus != null
                && !upgradeStatus.isBlank()) {

            round.put(
                    "upgradeStatus",
                    upgradeStatus
            );
        }

        round.put(
                "decision",
                decision
        );

        round.put(
                "published",
                false
        );

        return round;
    }


    // =========================================================
    // PUBLISH ROUND
    // =========================================================

    private boolean publishRound(int round) {

        try {

            QuerySnapshot snapshot =
                    db.collection("CAPAllotments")
                            .get()
                            .get();

            String roundName = "round" + round;

            int publishedCount = 0;

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                Map<String, Object> roundData =
                        getMap(document, roundName);

                if (roundData == null) {
                    continue;
                }

                document.getReference()
                        .update(
                                roundName + ".published",
                                true
                        )
                        .get();

                publishedCount++;
            }

            if (publishedCount == 0) {

                System.out.println(
                        "No Round " + round
                                + " allotments found."
                );

                return false;
            }

            Map<String, Object> settings =
                    new HashMap<>();

            settings.put(
                    "round" + round + "Published",
                    true
            );

            db.collection("CAPSettings")
                    .document("rounds")
                    .set(
                            settings,
                            SetOptions.merge()
                    )
                    .get();

            System.out.println(
                    "Round " + round + " published."
            );

            notificationDAO.createNotificationForAllStudents(
                    "CAP Round " + round + " Result Published",
                    "CAP Round " + round + " allotment result is now available. Open the CAP Rounds section to check your result.",
                    "CAP_ROUND_" + round + "_PUBLISHED"
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET STUDENT ALLOTMENT
    // =========================================================

    public CAPAllotment getStudentAllotment(
            int round) {

        try {

            String email =
                    Student.getInstance().getEmail();

            if (email == null || email.isBlank()) {
                return null;
            }

            DocumentSnapshot document =
                    db.collection("CAPAllotments")
                            .document(email)
                            .get()
                            .get();

            if (!document.exists()) {
                return null;
            }

            Map<String, Object> roundData =
                    getMap(
                            document,
                            "round" + round
                    );

            if (roundData == null) {
                return null;
            }

            Boolean published =
                    getBoolean(
                            roundData,
                            "published"
                    );

            if (published == null || !published) {
                return null;
            }

            CAPAllotment allotment =
                    new CAPAllotment();

            allotment.setStudentEmail(email);

            allotment.setRound(round);

            allotment.setCollege(
                    getString(roundData, "college")
            );

            allotment.setBranch(
                    getString(roundData, "branch")
            );

            allotment.setPreviousCollege(
                    getString(
                            roundData,
                            "previousCollege"
                    )
            );

            allotment.setPreviousBranch(
                    getString(
                            roundData,
                            "previousBranch"
                    )
            );

            allotment.setPreferenceNumber(
                    getInt(
                            roundData,
                            "preferenceNumber"
                    )
            );

            allotment.setStatus(
                    getString(roundData, "status")
            );

            allotment.setUpgradeStatus(
                    getString(
                            roundData,
                            "upgradeStatus"
                    )
            );

            allotment.setDecision(
                    getString(roundData, "decision")
            );

            allotment.setPublished(true);

            return allotment;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }


    // =========================================================
    // SAVE STUDENT DECISION
    // =========================================================

    public boolean saveDecision(
            int round,
            String decision) {

        try {

            if (round < 1 || round > 3) {
                return false;
            }

            if (decision == null
                    || decision.isBlank()) {
                return false;
            }

            String email =
                    Student.getInstance().getEmail();

            if (email == null || email.isBlank()) {
                return false;
            }

            DocumentReference reference =
                    db.collection("CAPAllotments")
                            .document(email);

            DocumentSnapshot document =
                    reference.get().get();

            if (!document.exists()) {
                return false;
            }

            Map<String, Object> roundData =
                    getMap(
                            document,
                            "round" + round
                    );

            if (roundData == null) {
                return false;
            }

            Boolean published =
                    getBoolean(
                            roundData,
                            "published"
                    );

            if (published == null || !published) {
                return false;
            }

            String currentDecision =
                    getString(
                            roundData,
                            "decision"
                    );

            if (!currentDecision.isBlank()
                    && !"Pending".equalsIgnoreCase(
                            currentDecision
                    )) {

                System.out.println(
                        "Decision already submitted."
                );

                return false;
            }

            Map<String, Object> decisionUpdate =
                    new HashMap<>();

            decisionUpdate.put(
                    "round" + round + ".decision",
                    decision
            );

            // A rejected seat must return to the vacant-seat pool.
            // Without this, later CAP rounds incorrectly see the seat as occupied.
            if ("Seat Rejected".equalsIgnoreCase(decision)) {

                String college =
                        getString(roundData, "college");

                String branch =
                        getString(roundData, "branch");

                DocumentReference collegeReference =
                        findCollegeReference(
                                college,
                                branch
                        );

                if (collegeReference != null) {
                    collegeReference.update(
                            "seatsAvailable",
                            FieldValue.increment(1)
                    ).get();

                    decisionUpdate.put(
                            "round" + round + ".seatReleased",
                            true
                    );
                }
            }

            reference.update(
                    decisionUpdate
            ).get();

            System.out.println(
                    "Round " + round
                            + " decision saved: "
                            + decision
            );

            String decisionTitle;
            String decisionMessage;

            if ("Admission Accepted".equalsIgnoreCase(decision)) {
                decisionTitle = "Admission Accepted";
                decisionMessage = "Your final admission acceptance has been recorded successfully.";
            } else if ("Betterment Requested".equalsIgnoreCase(decision)) {
                decisionTitle = "Betterment Requested";
                decisionMessage = "Your betterment request for CAP Round " + round + " has been recorded.";
            } else if ("Seat Rejected".equalsIgnoreCase(decision)) {
                decisionTitle = "Seat Rejected";
                decisionMessage = "Your CAP Round " + round + " seat rejection has been recorded.";
            } else {
                decisionTitle = "CAP Decision Recorded";
                decisionMessage = "Your CAP Round " + round + " decision has been recorded: " + decision + ".";
            }

            notificationDAO.createNotification(
                    email,
                    decisionTitle,
                    decisionMessage,
                    "CAP_DECISION"
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // FINAL ADMISSION
    // =========================================================

    public boolean acceptFinalAdmission() {

        return saveDecision(
                3,
                "Admission Accepted"
        );
    }


    // =========================================================
    // ROUND 1 OVERVIEW / ELIGIBILITY
    // =========================================================

    public int getEligibleRound1StudentCount() {

        int count = 0;

        try {
            QuerySnapshot preferenceSnapshot =
                    db.collection("StudentPreferences")
                            .whereEqualTo("locked", true)
                            .get()
                            .get();

            for (QueryDocumentSnapshot preferenceDocument :
                    preferenceSnapshot.getDocuments()) {

                String studentEmail = preferenceDocument.getId();

                DocumentSnapshot applicationDocument =
                        db.collection("Applications")
                                .document(studentEmail)
                                .get()
                                .get();

                if (!applicationDocument.exists()) {
                    continue;
                }

                String verificationStatus =
                        applicationDocument.getString("verificationStatus");

                if (!"Verified".equalsIgnoreCase(verificationStatus)) {
                    continue;
                }

                DocumentSnapshot studentDocument =
                        db.collection("Students")
                                .document(studentEmail)
                                .get()
                                .get();

                if (!studentDocument.exists()
                        || getStudentPercentile(studentDocument) < 0) {
                    continue;
                }

                Object preferences = preferenceDocument.get("preferences");

                if (!(preferences instanceof List<?>)
                        || ((List<?>) preferences).isEmpty()) {
                    continue;
                }

                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }


    public long getTotalAvailableSeatCount() {

        long total = 0;

        try {
            QuerySnapshot snapshot =
                    db.collection("Colleges")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                Long seats = document.getLong("seatsAvailable");

                if (seats != null && seats > 0) {
                    total += seats;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }


    private double getStudentPercentile(
            DocumentSnapshot studentDocument) {

        if (studentDocument == null || !studentDocument.exists()) {
            return -1;
        }

        Object value = studentDocument.get("cetPercentile");

        // Backward compatibility for older records that may have used
        // the old "percentile" field.
        if (value == null) {
            value = studentDocument.get("percentile");
        }

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        if (value instanceof String) {
            String text = ((String) value)
                    .trim()
                    .replace("%", "");

            if (text.isBlank()) {
                return -1;
            }

            try {
                return Double.parseDouble(text);
            } catch (NumberFormatException ignored) {
                return -1;
            }
        }

        return -1;
    }


    // =========================================================
    // ROUND OVERVIEW HELPERS
    // =========================================================

    public int getRoundAllotmentCount(int round) {

        if (round < 1 || round > 3) {
            return 0;
        }

        int count = 0;

        try {
            QuerySnapshot snapshot =
                    db.collection("CAPAllotments")
                            .get()
                            .get();

            String roundName = "round" + round;

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                if (getMap(document, roundName) != null) {
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean isRoundPublished(int round) {

        if (round < 1 || round > 3) {
            return false;
        }

        try {
            DocumentSnapshot document =
                    db.collection("CAPSettings")
                            .document("rounds")
                            .get()
                            .get();

            if (!document.exists()) {
                return false;
            }

            Boolean published =
                    document.getBoolean(
                            "round" + round + "Published"
                    );

            return published != null && published;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // COUNSELLOR COUNTS
    // =========================================================

    public int getLockedPreferenceCount() {

        try {

            QuerySnapshot snapshot =
                    db.collection("StudentPreferences")
                            .whereEqualTo(
                                    "locked",
                                    true
                            )
                            .get()
                            .get();

            return snapshot.size();

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }

    public int getRound1FrozenCount() {
        return getDecisionCount(
                1,
                "Seat Accepted"
        );
    }

    public int getRound1BettermentCount() {
        return getDecisionCount(
                1,
                "Betterment Requested"
        );
    }

    public int getRound1RejectedCount() {
        return getDecisionCount(
                1,
                "Seat Rejected"
        );
    }

    public int getNextRoundEligibleCount(int previousRound) {

        if (previousRound < 1 || previousRound > 2) {
            return 0;
        }

        int count = 0;

        try {
            QuerySnapshot snapshot =
                    db.collection("CAPAllotments")
                            .get()
                            .get();

            String roundName = "round" + previousRound;

            for (QueryDocumentSnapshot document : snapshot.getDocuments()) {
                Map<String, Object> roundData = getMap(document, roundName);
                if (roundData == null) {
                    continue;
                }

                String decision = getString(roundData, "decision");
                String status = getString(roundData, "status");
                int preferenceNumber = getInt(roundData, "preferenceNumber");

                boolean unallotted =
                        "No Seat Allotted".equalsIgnoreCase(status)
                                || preferenceNumber <= 0;

                if (unallotted
                        || "Betterment Requested".equalsIgnoreCase(decision)) {
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public int getRound2FrozenCount() {
        return getDecisionCount(
                2,
                "Seat Accepted"
        );
    }

    public int getRound2BettermentCount() {
        return getDecisionCount(
                2,
                "Betterment Requested"
        );
    }

    public int getFinalAdmissionCount() {
        return getDecisionCount(
                3,
                "Admission Accepted"
        );
    }


    private int getDecisionCount(
            int round,
            String requiredDecision) {

        int count = 0;

        try {

            QuerySnapshot snapshot =
                    db.collection("CAPAllotments")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                Map<String, Object> roundData =
                        getMap(
                                document,
                                "round" + round
                        );

                if (roundData == null) {
                    continue;
                }

                String decision =
                        getString(
                                roundData,
                                "decision"
                        );

                if (requiredDecision.equalsIgnoreCase(
                        decision
                )) {
                    count++;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return count;
    }


    // =========================================================
    // SORT PREFERENCES
    // =========================================================

    private List<Map<?, ?>> getSortedPreferences(
            List<?> preferences) {

        List<Map<?, ?>> result =
                new ArrayList<>();

        for (Object item : preferences) {

            if (item instanceof Map<?, ?>) {
                result.add((Map<?, ?>) item);
            }
        }

        result.sort(
                Comparator.comparingInt(
                        map -> getInt(
                                map,
                                "preferenceNumber"
                        )
                )
        );

        return result;
    }


    // =========================================================
    // HELPERS
    // =========================================================

    @SuppressWarnings("unchecked")
    private Map<String, Object> getMap(
            DocumentSnapshot document,
            String field) {

        if (document == null) {
            return null;
        }

        Object value = document.get(field);

        if (value instanceof Map<?, ?>) {
            return (Map<String, Object>) value;
        }

        return null;
    }


    private String getString(
            Map<?, ?> map,
            String key) {

        if (map == null) {
            return "";
        }

        Object value = map.get(key);

        if (value == null) {
            return "";
        }

        return value.toString();
    }


    private int getInt(
            Map<?, ?> map,
            String key) {

        if (map == null) {
            return 0;
        }

        Object value = map.get(key);

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        try {

            return Integer.parseInt(
                    String.valueOf(value)
            );

        } catch (Exception e) {

            return 0;
        }
    }


    private double getDouble(
            Map<?, ?> map,
            String key) {

        if (map == null) {
            return 0.0;
        }

        Object value = map.get(key);

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        try {

            return Double.parseDouble(
                    String.valueOf(value)
            );

        } catch (Exception e) {

            return 0.0;
        }
    }


    private double getDocumentDouble(
            DocumentSnapshot document,
            String field) {

        if (document == null) {
            return 0.0;
        }

        Object value = document.get(field);

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        try {

            return Double.parseDouble(
                    String.valueOf(value)
            );

        } catch (Exception e) {

            return 0.0;
        }
    }


    private Boolean getBoolean(
            Map<?, ?> map,
            String key) {

        if (map == null) {
            return false;
        }

        Object value = map.get(key);

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        return false;
    }
}