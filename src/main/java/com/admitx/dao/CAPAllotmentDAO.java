package com.admitx.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.CAPAllotment;
import com.admitx.model.Student;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;

public class CAPAllotmentDAO {

    private final Firestore db =
            FirebaseConfig.getFirestore();

    // =========================================================
    // ROUND 1
    // =========================================================

    public boolean runRound1Allotment() {

        try {

            QuerySnapshot preferenceSnapshot =
                    db.collection("StudentPreferences")
                            .get()
                            .get();

            int allottedCount = 0;

            for (
                    QueryDocumentSnapshot document :
                    preferenceSnapshot.getDocuments()
            ) {

                Boolean locked =
                        document.getBoolean("locked");

                if (
                        locked == null ||
                        !locked
                ) {
                    continue;
                }

                String studentEmail =
                        document.getString("studentEmail");

                if (
                        studentEmail == null ||
                        studentEmail.isBlank()
                ) {

                    studentEmail =
                            document.getId();
                }

                List<?> preferenceList =
                        (List<?>) document.get(
                                "preferences"
                        );

                if (
                        preferenceList == null ||
                        preferenceList.isEmpty()
                ) {
                    continue;
                }

                /*
                 * For demo CAP flow:
                 *
                 * Preference 1 = highest choice
                 * Larger number = lower choice
                 *
                 * Round 1 starts from the lowest
                 * available preference.
                 *
                 * Example:
                 * 1 COEP
                 * 2 PICT
                 * 3 VIT
                 * 4 PCCOE
                 * 5 DY Patil
                 *
                 * Round 1 -> Preference 5
                 */

                Map<?, ?> selectedPreference =
                        findRound1Preference(
                                preferenceList
                        );

                if (selectedPreference == null) {
                    continue;
                }

                String college =
                        getString(
                                selectedPreference,
                                "college"
                        );

                String branch =
                        getString(
                                selectedPreference,
                                "branch"
                        );

                int preferenceNumber =
                        getInt(
                                selectedPreference,
                                "preferenceNumber"
                        );

                if (
                        college.isBlank() ||
                        branch.isBlank() ||
                        preferenceNumber <= 0
                ) {
                    continue;
                }

                Map<String, Object> round1 =
                        new HashMap<>();

                round1.put(
                        "college",
                        college
                );

                round1.put(
                        "branch",
                        branch
                );

                round1.put(
                        "preferenceNumber",
                        preferenceNumber
                );

                round1.put(
                        "status",
                        "Seat Allotted"
                );

                round1.put(
                        "decision",
                        "Pending"
                );

                round1.put(
                        "published",
                        false
                );

                Map<String, Object> data =
                        new HashMap<>();

                data.put(
                        "studentEmail",
                        studentEmail
                );

                data.put(
                        "round1",
                        round1
                );

                db.collection(
                        "CAPAllotments"
                )
                .document(
                        studentEmail
                )
                .set(
                        data,
                        SetOptions.merge()
                )
                .get();

                allottedCount++;
            }

            System.out.println(
                    "Round 1 allotment completed."
            );

            System.out.println(
                    "Students allotted: "
                            + allottedCount
            );

            return allottedCount > 0;

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
    // ROUND 2
    // =========================================================

    public boolean runRound2Allotment() {

        try {

            QuerySnapshot allotmentSnapshot =
                    db.collection(
                            "CAPAllotments"
                    )
                    .get()
                    .get();

            int processed = 0;

            for (
                    QueryDocumentSnapshot document :
                    allotmentSnapshot.getDocuments()
            ) {

                String studentEmail =
                        document.getId();

                Map<String, Object> round1 =
                        getMap(
                                document,
                                "round1"
                        );

                if (round1 == null) {
                    continue;
                }

                String decision =
                        getString(
                                round1,
                                "decision"
                        );

                /*
                 * Only students requesting betterment
                 * participate in Round 2.
                 */

                if (
                        !"Betterment Requested"
                                .equalsIgnoreCase(
                                        decision
                                )
                ) {
                    continue;
                }

                DocumentSnapshot preferenceDocument =
                        db.collection(
                                "StudentPreferences"
                        )
                        .document(
                                studentEmail
                        )
                        .get()
                        .get();

                if (!preferenceDocument.exists()) {
                    continue;
                }

                Boolean locked =
                        preferenceDocument.getBoolean(
                                "locked"
                        );

                if (
                        locked == null ||
                        !locked
                ) {
                    continue;
                }

                List<?> preferences =
                        (List<?>) preferenceDocument.get(
                                "preferences"
                        );

                if (
                        preferences == null ||
                        preferences.isEmpty()
                ) {
                    continue;
                }

                int oldPreference =
                        getInt(
                                round1,
                                "preferenceNumber"
                        );

                /*
                 * Betterment moves towards
                 * SMALLER preference number.
                 *
                 * 5 -> 4
                 * 4 -> 3
                 * 3 -> 2
                 * 2 -> 1
                 */

                Map<?, ?> selectedPreference =
                        findBetterPreference(
                                preferences,
                                oldPreference
                        );

                String previousCollege =
                        getString(
                                round1,
                                "college"
                        );

                String previousBranch =
                        getString(
                                round1,
                                "branch"
                        );

                String newCollege =
                        previousCollege;

                String newBranch =
                        previousBranch;

                int newPreferenceNumber =
                        oldPreference;

                String upgradeStatus =
                        "Not Upgraded";

                if (selectedPreference != null) {

                    String selectedCollege =
                            getString(
                                    selectedPreference,
                                    "college"
                            );

                    String selectedBranch =
                            getString(
                                    selectedPreference,
                                    "branch"
                            );

                    int selectedNumber =
                            getInt(
                                    selectedPreference,
                                    "preferenceNumber"
                            );

                    if (
                            !selectedCollege.isBlank() &&
                            !selectedBranch.isBlank() &&
                            selectedNumber > 0
                    ) {

                        newCollege =
                                selectedCollege;

                        newBranch =
                                selectedBranch;

                        newPreferenceNumber =
                                selectedNumber;

                        upgradeStatus =
                                "Upgraded";
                    }
                }

                Map<String, Object> round2 =
                        new HashMap<>();

                round2.put(
                        "previousCollege",
                        previousCollege
                );

                round2.put(
                        "previousBranch",
                        previousBranch
                );

                round2.put(
                        "college",
                        newCollege
                );

                round2.put(
                        "branch",
                        newBranch
                );

                round2.put(
                        "preferenceNumber",
                        newPreferenceNumber
                );

                round2.put(
                        "status",
                        "Seat Allotted"
                );

                round2.put(
                        "upgradeStatus",
                        upgradeStatus
                );

                round2.put(
                        "decision",
                        "Pending"
                );

                round2.put(
                        "published",
                        false
                );

                Map<String, Object> update =
                        new HashMap<>();

                update.put(
                        "round2",
                        round2
                );

                db.collection(
                        "CAPAllotments"
                )
                .document(
                        studentEmail
                )
                .set(
                        update,
                        SetOptions.merge()
                )
                .get();

                processed++;
            }

            System.out.println(
                    "Round 2 allotment completed."
            );

            System.out.println(
                    "Students processed: "
                            + processed
            );

            return processed > 0;

        } catch (Exception e) {

            System.out.println(
                    "Round 2 allotment failed."
            );

            e.printStackTrace();

            return false;
        }
    }


    public boolean publishRound2() {

        return publishRound(2);
    }

    // =========================================================
    // ROUND 3
    // =========================================================

    public boolean runRound3Allotment() {

        try {

            QuerySnapshot allotmentSnapshot =
                    db.collection(
                            "CAPAllotments"
                    )
                    .get()
                    .get();

            int processed = 0;

            for (
                    QueryDocumentSnapshot document :
                    allotmentSnapshot.getDocuments()
            ) {

                String studentEmail =
                        document.getId();

                Map<String, Object> round2 =
                        getMap(
                                document,
                                "round2"
                        );

                if (round2 == null) {
                    continue;
                }

                String decision =
                        getString(
                                round2,
                                "decision"
                        );

                /*
                 * Only Round 2 Betterment students
                 * participate in Round 3.
                 */

                if (
                        !"Betterment Requested"
                                .equalsIgnoreCase(
                                        decision
                                )
                ) {
                    continue;
                }

                DocumentSnapshot preferenceDocument =
                        db.collection(
                                "StudentPreferences"
                        )
                        .document(
                                studentEmail
                        )
                        .get()
                        .get();

                if (!preferenceDocument.exists()) {
                    continue;
                }

                Boolean locked =
                        preferenceDocument.getBoolean(
                                "locked"
                        );

                if (
                        locked == null ||
                        !locked
                ) {
                    continue;
                }

                List<?> preferences =
                        (List<?>) preferenceDocument.get(
                                "preferences"
                        );

                if (
                        preferences == null ||
                        preferences.isEmpty()
                ) {
                    continue;
                }

                int oldPreference =
                        getInt(
                                round2,
                                "preferenceNumber"
                        );

                Map<?, ?> selectedPreference =
                        findBetterPreference(
                                preferences,
                                oldPreference
                        );

                String previousCollege =
                        getString(
                                round2,
                                "college"
                        );

                String previousBranch =
                        getString(
                                round2,
                                "branch"
                        );

                String finalCollege =
                        previousCollege;

                String finalBranch =
                        previousBranch;

                int finalPreferenceNumber =
                        oldPreference;

                String upgradeStatus =
                        "Not Upgraded";

                if (selectedPreference != null) {

                    String selectedCollege =
                            getString(
                                    selectedPreference,
                                    "college"
                            );

                    String selectedBranch =
                            getString(
                                    selectedPreference,
                                    "branch"
                            );

                    int selectedNumber =
                            getInt(
                                    selectedPreference,
                                    "preferenceNumber"
                            );

                    if (
                            !selectedCollege.isBlank() &&
                            !selectedBranch.isBlank() &&
                            selectedNumber > 0
                    ) {

                        finalCollege =
                                selectedCollege;

                        finalBranch =
                                selectedBranch;

                        finalPreferenceNumber =
                                selectedNumber;

                        upgradeStatus =
                                "Upgraded";
                    }
                }

                Map<String, Object> round3 =
                        new HashMap<>();

                /*
                 * Keep previous seat details so
                 * student Round 3 can display them.
                 */

                round3.put(
                        "previousCollege",
                        previousCollege
                );

                round3.put(
                        "previousBranch",
                        previousBranch
                );

                round3.put(
                        "college",
                        finalCollege
                );

                round3.put(
                        "branch",
                        finalBranch
                );

                round3.put(
                        "preferenceNumber",
                        finalPreferenceNumber
                );

                round3.put(
                        "status",
                        "Final Seat Allotted"
                );

                round3.put(
                        "upgradeStatus",
                        upgradeStatus
                );

                round3.put(
                        "decision",
                        "Pending"
                );

                round3.put(
                        "published",
                        false
                );

                Map<String, Object> update =
                        new HashMap<>();

                update.put(
                        "round3",
                        round3
                );

                db.collection(
                        "CAPAllotments"
                )
                .document(
                        studentEmail
                )
                .set(
                        update,
                        SetOptions.merge()
                )
                .get();

                processed++;
            }

            System.out.println(
                    "Round 3 allotment completed."
            );

            System.out.println(
                    "Students processed: "
                            + processed
            );

            return processed > 0;

        } catch (Exception e) {

            System.out.println(
                    "Round 3 allotment failed."
            );

            e.printStackTrace();

            return false;
        }
    }


    public boolean publishRound3() {

        return publishRound(3);
    }

    // =========================================================
    // PUBLISH ROUND
    // =========================================================

    private boolean publishRound(
            int round
    ) {

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "CAPAllotments"
                    )
                    .get()
                    .get();

            String roundName =
                    "round" + round;

            String publishedField =
                    roundName + ".published";

            int publishedCount = 0;

            for (
                    QueryDocumentSnapshot document :
                    snapshot.getDocuments()
            ) {

                Object roundObject =
                        document.get(
                                roundName
                        );

                if (roundObject == null) {
                    continue;
                }

                document.getReference()
                        .update(
                                publishedField,
                                true
                        )
                        .get();

                publishedCount++;
            }

            /*
             * If no Round document exists,
             * do not mark round as published.
             */

            if (publishedCount == 0) {

                System.out.println(
                        "No Round "
                                + round
                                + " allotments found."
                );

                return false;
            }

            Map<String, Object> settings =
                    new HashMap<>();

            settings.put(
                    "round"
                            + round
                            + "Published",
                    true
            );

            db.collection(
                    "CAPSettings"
            )
            .document(
                    "rounds"
            )
            .set(
                    settings,
                    SetOptions.merge()
            )
            .get();

            System.out.println(
                    "Round "
                            + round
                            + " published."
            );

            System.out.println(
                    "Published students: "
                            + publishedCount
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Round publish failed."
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // STUDENT ALLOTMENT
    // =========================================================

    public CAPAllotment getStudentAllotment(
            int round
    ) {

        try {

            String email =
                    Student.getInstance()
                            .getEmail();

            if (
                    email == null ||
                    email.isBlank()
            ) {
                return null;
            }

            DocumentSnapshot document =
                    db.collection(
                            "CAPAllotments"
                    )
                    .document(
                            email
                    )
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

            /*
             * Student cannot see result
             * until counsellor publishes it.
             */

            if (
                    published == null ||
                    !published
            ) {
                return null;
            }

            CAPAllotment allotment =
                    new CAPAllotment();

            allotment.setStudentEmail(
                    email
            );

            allotment.setRound(
                    round
            );

            allotment.setCollege(
                    getString(
                            roundData,
                            "college"
                    )
            );

            allotment.setBranch(
                    getString(
                            roundData,
                            "branch"
                    )
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
                    getString(
                            roundData,
                            "status"
                    )
            );

            allotment.setUpgradeStatus(
                    getString(
                            roundData,
                            "upgradeStatus"
                    )
            );

            allotment.setDecision(
                    getString(
                            roundData,
                            "decision"
                    )
            );

            allotment.setPublished(
                    true
            );

            return allotment;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // STUDENT DECISION
    // =========================================================

    public boolean saveDecision(
            int round,
            String decision
    ) {

        try {

            if (
                    round < 1 ||
                    round > 3
            ) {

                return false;
            }

            if (
                    decision == null ||
                    decision.isBlank()
            ) {

                return false;
            }

            String email =
                    Student.getInstance()
                            .getEmail();

            if (
                    email == null ||
                    email.isBlank()
            ) {

                return false;
            }

            DocumentSnapshot document =
                    db.collection(
                            "CAPAllotments"
                    )
                    .document(
                            email
                    )
                    .get()
                    .get();

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

            /*
             * Prevent decisions on unpublished rounds.
             */

            if (
                    published == null ||
                    !published
            ) {

                return false;
            }

            String currentDecision =
                    getString(
                            roundData,
                            "decision"
                    );

            /*
             * Prevent changing decision after
             * it has already been submitted.
             */

            if (
                    currentDecision != null &&
                    !currentDecision.isBlank() &&
                    !"Pending".equalsIgnoreCase(
                            currentDecision
                    )
            ) {

                System.out.println(
                        "Decision already submitted: "
                                + currentDecision
                );

                return false;
            }

            String field =
                    "round"
                            + round
                            + ".decision";

            db.collection(
                    "CAPAllotments"
            )
            .document(
                    email
            )
            .update(
                    field,
                    decision
            )
            .get();

            System.out.println(
                    "Round "
                            + round
                            + " decision saved: "
                            + decision
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
    // COUNSELLOR COUNTS
    // =========================================================

    public int getLockedPreferenceCount() {

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "StudentPreferences"
                    )
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
            String requiredDecision
    ) {

        int count = 0;

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "CAPAllotments"
                    )
                    .get()
                    .get();

            for (
                    QueryDocumentSnapshot document :
                    snapshot.getDocuments()
            ) {

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

                if (
                        requiredDecision
                                .equalsIgnoreCase(
                                        decision
                                )
                ) {

                    count++;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return count;
    }

    // =========================================================
    // ROUND 1 PREFERENCE
    // =========================================================

    private Map<?, ?> findRound1Preference(
            List<?> preferences
    ) {

        Map<?, ?> selectedPreference =
                null;

        int highestPreferenceNumber =
                Integer.MIN_VALUE;

        for (Object item : preferences) {

            if (
                    !(item instanceof Map<?, ?>)
            ) {

                continue;
            }

            Map<?, ?> preference =
                    (Map<?, ?>) item;

            int number =
                    getInt(
                            preference,
                            "preferenceNumber"
                    );

            /*
             * Bigger number =
             * lower preference.
             *
             * Round 1 starts from
             * the lowest preference
             * for this demo workflow.
             */

            if (
                    number > highestPreferenceNumber
            ) {

                highestPreferenceNumber =
                        number;

                selectedPreference =
                        preference;
            }
        }

        return selectedPreference;
    }

    // =========================================================
    // BETTERMENT
    // =========================================================

    private Map<?, ?> findBetterPreference(
            List<?> preferences,
            int currentPreference
    ) {

        Map<?, ?> selectedPreference =
                null;

        int closestBetterPreference =
                Integer.MIN_VALUE;

        for (Object item : preferences) {

            if (
                    !(item instanceof Map<?, ?>)
            ) {

                continue;
            }

            Map<?, ?> preference =
                    (Map<?, ?>) item;

            int number =
                    getInt(
                            preference,
                            "preferenceNumber"
                    );

            /*
             * Preference 1 is highest.
             *
             * Find the closest SMALLER number.
             *
             * Example:
             *
             * Current = 5
             * Choose = 4
             *
             * Current = 4
             * Choose = 3
             */

            if (
                    number > 0 &&
                    number < currentPreference &&
                    number > closestBetterPreference
            ) {

                closestBetterPreference =
                        number;

                selectedPreference =
                        preference;
            }
        }

        return selectedPreference;
    }

    // =========================================================
    // HELPERS
    // =========================================================

    @SuppressWarnings("unchecked")
    private Map<String, Object> getMap(
            DocumentSnapshot document,
            String field
    ) {

        if (document == null) {
            return null;
        }

        Object value =
                document.get(
                        field
                );

        if (
                value instanceof Map<?, ?>
        ) {

            return (Map<String, Object>) value;
        }

        return null;
    }


    private String getString(
            Map<?, ?> map,
            String key
    ) {

        if (map == null) {
            return "";
        }

        Object value =
                map.get(
                        key
                );

        if (value == null) {
            return "";
        }

        return value.toString();
    }


    private int getInt(
            Map<?, ?> map,
            String key
    ) {

        if (map == null) {
            return 0;
        }

        Object value =
                map.get(
                        key
                );

        if (
                value instanceof Number
        ) {

            return ((Number) value)
                    .intValue();
        }

        try {

            return Integer.parseInt(
                    String.valueOf(
                            value
                    )
            );

        } catch (Exception e) {

            return 0;
        }
    }


    private Boolean getBoolean(
            Map<?, ?> map,
            String key
    ) {

        if (map == null) {
            return false;
        }

        Object value =
                map.get(
                        key
                );

        if (
                value instanceof Boolean
        ) {

            return (Boolean) value;
        }

        return false;
    }
}