package com.admitx.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.Student;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;

public class MeritDAO {

    private final Firestore db =
            FirebaseConfig.getFirestore();

    // =========================================================
    // GENERATE PROVISIONAL MERIT LIST
    // =========================================================

    public int generateProvisionalMeritList() {

        try {

            List<MeritCandidate> candidates =
                    getVerifiedCandidates();

            candidates.sort(
                    Comparator.comparingDouble(
                            (MeritCandidate candidate) ->
                                    candidate.percentileValue
                    ).reversed()
            );

            Map<String, Integer> categoryRanks =
                    new HashMap<>();

            int generalRank = 1;

            for (MeritCandidate candidate : candidates) {

                int categoryRank =
                        categoryRanks.getOrDefault(
                                candidate.category,
                                0
                        ) + 1;

                categoryRanks.put(
                        candidate.category,
                        categoryRank
                );

                Map<String, Object> merit =
                        new HashMap<>();

                merit.put(
                        "studentEmail",
                        candidate.email
                );

                merit.put(
                        "candidateName",
                        candidate.candidateName
                );

                merit.put(
                        "category",
                        candidate.category
                );

                merit.put(
                        "cetPercentile",
                        candidate.percentile
                );

                merit.put(
                        "provisionalMeritNumber",
                        generalRank
                );

                merit.put(
                        "categoryRank",
                        categoryRank
                );

                merit.put(
                        "provisionalGenerated",
                        true
                );

                merit.put(
                        "provisionalPublished",
                        false
                );

                merit.put(
                        "finalGenerated",
                        false
                );

                merit.put(
                        "finalPublished",
                        false
                );

                merit.put(
                        "status",
                        "Provisional Generated"
                );

                merit.put(
                        "generatedAt",
                        Timestamp.now()
                );

                db.collection("MeritList")
                        .document(candidate.email)
                        .set(
                                merit,
                                SetOptions.merge()
                        )
                        .get();

                generalRank++;
            }

            Map<String, Object> settings =
                    new HashMap<>();

            settings.put(
                    "provisionalGenerated",
                    true
            );

            settings.put(
                    "provisionalPublished",
                    false
            );

            settings.put(
                    "finalGenerated",
                    false
            );

            settings.put(
                    "finalPublished",
                    false
            );

            settings.put(
                    "generatedAt",
                    Timestamp.now()
            );

            db.collection("MeritSettings")
                    .document("status")
                    .set(
                            settings,
                            SetOptions.merge()
                    )
                    .get();

            return candidates.size();

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }

    // =========================================================
    // PUBLISH PROVISIONAL MERIT LIST
    // =========================================================

    public boolean publishProvisionalMeritList() {

        try {

            QuerySnapshot snapshot =
                    db.collection("MeritList")
                            .get()
                            .get();

            if (snapshot.isEmpty()) {

                return false;
            }

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                Map<String, Object> update =
                        new HashMap<>();

                update.put(
                        "provisionalPublished",
                        true
                );

                update.put(
                        "status",
                        "Provisional Published"
                );

                update.put(
                        "publishedAt",
                        Timestamp.now()
                );

                db.collection("MeritList")
                        .document(document.getId())
                        .set(
                                update,
                                SetOptions.merge()
                        )
                        .get();
            }

            Map<String, Object> settings =
                    new HashMap<>();

            settings.put(
                    "provisionalPublished",
                    true
            );

            settings.put(
                    "provisionalPublishedAt",
                    Timestamp.now()
            );

            db.collection("MeritSettings")
                    .document("status")
                    .set(
                            settings,
                            SetOptions.merge()
                    )
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GENERATE FINAL MERIT LIST
    // =========================================================

    public int generateFinalMeritList() {

        try {

            // Final merit should not be generated while
            // grievances are still waiting for counsellor review.

            if (getUnresolvedGrievanceCount() > 0) {

                return -1;
            }

            if (!isProvisionalPublished()) {

                return -2;
            }

            List<MeritCandidate> candidates =
                    getVerifiedCandidates();

            candidates.sort(
                    Comparator.comparingDouble(
                            (MeritCandidate candidate) ->
                                    candidate.percentileValue
                    ).reversed()
            );

            Map<String, Integer> categoryRanks =
                    new HashMap<>();

            int finalRank = 1;

            for (MeritCandidate candidate : candidates) {

                int finalCategoryRank =
                        categoryRanks.getOrDefault(
                                candidate.category,
                                0
                        ) + 1;

                categoryRanks.put(
                        candidate.category,
                        finalCategoryRank
                );

                Map<String, Object> update =
                        new HashMap<>();

                update.put(
                        "studentEmail",
                        candidate.email
                );

                update.put(
                        "candidateName",
                        candidate.candidateName
                );

                update.put(
                        "category",
                        candidate.category
                );

                update.put(
                        "cetPercentile",
                        candidate.percentile
                );

                update.put(
                        "finalMeritNumber",
                        finalRank
                );

                update.put(
                        "finalCategoryRank",
                        finalCategoryRank
                );

                update.put(
                        "finalGenerated",
                        true
                );

                update.put(
                        "finalPublished",
                        false
                );

                update.put(
                        "status",
                        "Final Merit Generated"
                );

                update.put(
                        "finalGeneratedAt",
                        Timestamp.now()
                );

                db.collection("MeritList")
                        .document(candidate.email)
                        .set(
                                update,
                                SetOptions.merge()
                        )
                        .get();

                finalRank++;
            }

            Map<String, Object> settings =
                    new HashMap<>();

            settings.put(
                    "finalGenerated",
                    true
            );

            settings.put(
                    "finalPublished",
                    false
            );

            settings.put(
                    "finalGeneratedAt",
                    Timestamp.now()
            );

            db.collection("MeritSettings")
                    .document("status")
                    .set(
                            settings,
                            SetOptions.merge()
                    )
                    .get();

            return candidates.size();

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }

    // =========================================================
    // PUBLISH FINAL MERIT LIST
    // =========================================================

    public boolean publishFinalMeritList() {

        try {

            if (!isFinalGenerated()) {

                return false;
            }

            if (getUnresolvedGrievanceCount() > 0) {

                return false;
            }

            QuerySnapshot snapshot =
                    db.collection("MeritList")
                            .get()
                            .get();

            if (snapshot.isEmpty()) {

                return false;
            }

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                Boolean finalGenerated =
                        document.getBoolean(
                                "finalGenerated"
                        );

                if (!Boolean.TRUE.equals(finalGenerated)) {

                    continue;
                }

                Map<String, Object> update =
                        new HashMap<>();

                update.put(
                        "finalPublished",
                        true
                );

                update.put(
                        "status",
                        "Final Merit Published"
                );

                update.put(
                        "finalPublishedAt",
                        Timestamp.now()
                );

                db.collection("MeritList")
                        .document(document.getId())
                        .set(
                                update,
                                SetOptions.merge()
                        )
                        .get();
            }

            Map<String, Object> settings =
                    new HashMap<>();

            settings.put(
                    "finalPublished",
                    true
            );

            settings.put(
                    "finalPublishedAt",
                    Timestamp.now()
            );

            db.collection("MeritSettings")
                    .document("status")
                    .set(
                            settings,
                            SetOptions.merge()
                    )
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // PROVISIONAL GENERATED
    // =========================================================

    public boolean isProvisionalGenerated() {

        try {

            DocumentSnapshot document =
                    db.collection("MeritSettings")
                            .document("status")
                            .get()
                            .get();

            if (!document.exists()) {

                return false;
            }

            Boolean value =
                    document.getBoolean(
                            "provisionalGenerated"
                    );

            return Boolean.TRUE.equals(value);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // PROVISIONAL PUBLISHED
    // =========================================================

    public boolean isProvisionalPublished() {

        try {

            DocumentSnapshot document =
                    db.collection("MeritSettings")
                            .document("status")
                            .get()
                            .get();

            if (!document.exists()) {

                return false;
            }

            Boolean value =
                    document.getBoolean(
                            "provisionalPublished"
                    );

            return Boolean.TRUE.equals(value);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // FINAL GENERATED
    // =========================================================

    public boolean isFinalGenerated() {

        try {

            DocumentSnapshot document =
                    db.collection("MeritSettings")
                            .document("status")
                            .get()
                            .get();

            if (!document.exists()) {

                return false;
            }

            Boolean value =
                    document.getBoolean(
                            "finalGenerated"
                    );

            return Boolean.TRUE.equals(value);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // FINAL PUBLISHED
    // =========================================================

    public boolean isFinalPublished() {

        try {

            DocumentSnapshot document =
                    db.collection("MeritSettings")
                            .document("status")
                            .get()
                            .get();

            if (!document.exists()) {

                return false;
            }

            Boolean value =
                    document.getBoolean(
                            "finalPublished"
                    );

            return Boolean.TRUE.equals(value);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // CURRENT STUDENT PROVISIONAL MERIT
    // =========================================================

    public MeritRecord getCurrentStudentMerit() {

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
                    db.collection("MeritList")
                            .document(email)
                            .get()
                            .get();

            if (!document.exists()) {

                return null;
            }

            MeritRecord merit =
                    convertToMeritRecord(
                            document
                    );

            if (!merit.isProvisionalPublished()) {

                return null;
            }

            return merit;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // CURRENT STUDENT FINAL MERIT
    // =========================================================

    public MeritRecord getCurrentStudentFinalMerit() {

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
                    db.collection("MeritList")
                            .document(email)
                            .get()
                            .get();

            if (!document.exists()) {

                return null;
            }

            MeritRecord merit =
                    convertToMeritRecord(
                            document
                    );

            if (!merit.isFinalPublished()) {

                return null;
            }

            return merit;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // GET ALL MERIT RECORDS
    // =========================================================

    public List<MeritRecord> getAllMeritRecords() {

        List<MeritRecord> records =
                new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection("MeritList")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                records.add(
                        convertToMeritRecord(
                                document
                        )
                );
            }

            records.sort(
                    Comparator.comparingInt(
                            MeritRecord::getProvisionalMeritNumber
                    )
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        return records;
    }

    // =========================================================
    // GET VERIFIED CANDIDATES
    // =========================================================

    private List<MeritCandidate> getVerifiedCandidates() {

        List<MeritCandidate> candidates =
                new ArrayList<>();

        try {

            QuerySnapshot applications =
                    db.collection("Applications")
                            .get()
                            .get();

            for (QueryDocumentSnapshot application :
                    applications.getDocuments()) {

                String verificationStatus =
                        application.getString(
                                "verificationStatus"
                        );

                if (!"Verified".equalsIgnoreCase(
                        verificationStatus
                )) {

                    continue;
                }

                String email =
                        application.getString(
                                "studentEmail"
                        );

                if (
                        email == null ||
                        email.isBlank()
                ) {

                    email =
                            application.getId();
                }

                DocumentSnapshot studentDocument =
                        db.collection("Students")
                                .document(email)
                                .get()
                                .get();

                String candidateName =
                        null;

                String category =
                        null;

                String percentile =
                        null;

                if (studentDocument.exists()) {

                    candidateName =
                            studentDocument.getString(
                                    "candidateName"
                            );

                    category =
                            studentDocument.getString(
                                    "category"
                            );

                    percentile =
                            studentDocument.getString(
                                    "cetPercentile"
                            );
                }

                if (
                        candidateName == null ||
                        candidateName.isBlank()
                ) {

                    candidateName =
                            application.getString(
                                    "candidateName"
                            );
                }

                if (
                        candidateName == null ||
                        candidateName.isBlank()
                ) {

                    candidateName =
                            application.getString(
                                    "studentName"
                            );
                }

                if (
                        category == null ||
                        category.isBlank()
                ) {

                    category =
                            application.getString(
                                    "category"
                            );
                }

                MeritCandidate candidate =
                        new MeritCandidate();

                candidate.email =
                        email;

                candidate.candidateName =
                        safe(candidateName);

                candidate.category =
                        safeCategory(category);

                candidate.percentile =
                        safePercentile(percentile);

                candidate.percentileValue =
                        parsePercentile(
                                percentile
                        );

                candidates.add(
                        candidate
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return candidates;
    }

    // =========================================================
    // ELIGIBLE STUDENT COUNT
    // =========================================================

    public int getEligibleStudentCount() {

        int count = 0;

        try {

            QuerySnapshot snapshot =
                    db.collection("Applications")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                String verificationStatus =
                        document.getString(
                                "verificationStatus"
                        );

                if ("Verified".equalsIgnoreCase(
                        verificationStatus
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
    // PROVISIONAL PUBLISHED COUNT
    // =========================================================

    public int getPublishedMeritCount() {

        int count = 0;

        try {

            QuerySnapshot snapshot =
                    db.collection("MeritList")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                Boolean published =
                        document.getBoolean(
                                "provisionalPublished"
                        );

                if (Boolean.TRUE.equals(published)) {

                    count++;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return count;
    }

    // =========================================================
    // FINAL PUBLISHED COUNT
    // =========================================================

    public int getFinalPublishedCount() {

        int count = 0;

        try {

            QuerySnapshot snapshot =
                    db.collection("MeritList")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                Boolean published =
                        document.getBoolean(
                                "finalPublished"
                        );

                if (Boolean.TRUE.equals(published)) {

                    count++;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return count;
    }

    // =========================================================
    // UNRESOLVED GRIEVANCES
    // =========================================================

    public int getUnresolvedGrievanceCount() {

        int count = 0;

        try {

            QuerySnapshot snapshot =
                    db.collection("Grievances")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                String status =
                        document.getString(
                                "status"
                        );

                if ("Pending".equalsIgnoreCase(
                        status
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
    // CONVERT FIRESTORE DOCUMENT
    // =========================================================

    private MeritRecord convertToMeritRecord(
            DocumentSnapshot document
    ) {

        MeritRecord record =
                new MeritRecord();

        record.setStudentEmail(
                document.getString(
                        "studentEmail"
                )
        );

        record.setCandidateName(
                document.getString(
                        "candidateName"
                )
        );

        record.setCategory(
                document.getString(
                        "category"
                )
        );

        record.setCetPercentile(
                document.getString(
                        "cetPercentile"
                )
        );

        Long provisionalMerit =
                document.getLong(
                        "provisionalMeritNumber"
                );

        if (provisionalMerit != null) {

            record.setProvisionalMeritNumber(
                    provisionalMerit.intValue()
            );
        }

        Long categoryRank =
                document.getLong(
                        "categoryRank"
                );

        if (categoryRank != null) {

            record.setCategoryRank(
                    categoryRank.intValue()
            );
        }

        Long finalMerit =
                document.getLong(
                        "finalMeritNumber"
                );

        if (finalMerit != null) {

            record.setFinalMeritNumber(
                    finalMerit.intValue()
            );
        }

        Long finalCategoryRank =
                document.getLong(
                        "finalCategoryRank"
                );

        if (finalCategoryRank != null) {

            record.setFinalCategoryRank(
                    finalCategoryRank.intValue()
            );
        }

        Boolean provisionalPublished =
                document.getBoolean(
                        "provisionalPublished"
                );

        record.setProvisionalPublished(
                Boolean.TRUE.equals(
                        provisionalPublished
                )
        );

        Boolean finalGenerated =
                document.getBoolean(
                        "finalGenerated"
                );

        record.setFinalGenerated(
                Boolean.TRUE.equals(
                        finalGenerated
                )
        );

        Boolean finalPublished =
                document.getBoolean(
                        "finalPublished"
                );

        record.setFinalPublished(
                Boolean.TRUE.equals(
                        finalPublished
                )
        );

        record.setStatus(
                document.getString(
                        "status"
                )
        );

        return record;
    }

    // =========================================================
    // PERCENTILE PARSER
    // =========================================================

    private double parsePercentile(
            String value
    ) {

        try {

            if (
                    value == null ||
                    value.isBlank()
            ) {

                return 0;
            }

            String cleaned =
                    value.replace(
                            "%",
                            ""
                    ).trim();

            return Double.parseDouble(
                    cleaned
            );

        } catch (Exception e) {

            return 0;
        }
    }

    private String safe(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {

            return "Not Available";
        }

        return value;
    }

    private String safeCategory(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {

            return "Open";
        }

        return value;
    }

    private String safePercentile(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {

            return "0";
        }

        return value;
    }

    // =========================================================
    // INTERNAL MERIT CANDIDATE
    // =========================================================

    private static class MeritCandidate {

        private String email;

        private String candidateName;

        private String category;

        private String percentile;

        private double percentileValue;
    }

    // =========================================================
    // MERIT RECORD
    // =========================================================

    public static class MeritRecord {

        private String studentEmail;

        private String candidateName;

        private String category;

        private String cetPercentile;

        private int provisionalMeritNumber;

        private int categoryRank;

        private int finalMeritNumber;

        private int finalCategoryRank;

        private boolean provisionalPublished;

        private boolean finalGenerated;

        private boolean finalPublished;

        private String status;

        public String getStudentEmail() {

            return studentEmail;
        }

        public void setStudentEmail(
                String studentEmail
        ) {

            this.studentEmail =
                    studentEmail;
        }

        public String getCandidateName() {

            return candidateName;
        }

        public void setCandidateName(
                String candidateName
        ) {

            this.candidateName =
                    candidateName;
        }

        public String getCategory() {

            return category;
        }

        public void setCategory(
                String category
        ) {

            this.category =
                    category;
        }

        public String getCetPercentile() {

            return cetPercentile;
        }

        public void setCetPercentile(
                String cetPercentile
        ) {

            this.cetPercentile =
                    cetPercentile;
        }

        public int getProvisionalMeritNumber() {

            return provisionalMeritNumber;
        }

        public void setProvisionalMeritNumber(
                int provisionalMeritNumber
        ) {

            this.provisionalMeritNumber =
                    provisionalMeritNumber;
        }

        public int getCategoryRank() {

            return categoryRank;
        }

        public void setCategoryRank(
                int categoryRank
        ) {

            this.categoryRank =
                    categoryRank;
        }

        public int getFinalMeritNumber() {

            return finalMeritNumber;
        }

        public void setFinalMeritNumber(
                int finalMeritNumber
        ) {

            this.finalMeritNumber =
                    finalMeritNumber;
        }

        public int getFinalCategoryRank() {

            return finalCategoryRank;
        }

        public void setFinalCategoryRank(
                int finalCategoryRank
        ) {

            this.finalCategoryRank =
                    finalCategoryRank;
        }

        public boolean isProvisionalPublished() {

            return provisionalPublished;
        }

        public void setProvisionalPublished(
                boolean provisionalPublished
        ) {

            this.provisionalPublished =
                    provisionalPublished;
        }

        public boolean isFinalGenerated() {

            return finalGenerated;
        }

        public void setFinalGenerated(
                boolean finalGenerated
        ) {

            this.finalGenerated =
                    finalGenerated;
        }

        public boolean isFinalPublished() {

            return finalPublished;
        }

        public void setFinalPublished(
                boolean finalPublished
        ) {

            this.finalPublished =
                    finalPublished;
        }

        public String getStatus() {

            return status;
        }

        public void setStatus(
                String status
        ) {

            this.status =
                    status;
        }
    }
}