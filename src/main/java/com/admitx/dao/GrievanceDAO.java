package com.admitx.dao;

import java.util.ArrayList;
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

public class GrievanceDAO {

    private final Firestore db =
            FirebaseConfig.getFirestore();

    // =========================================================
    // SUBMIT GRIEVANCE
    // =========================================================

    public boolean submitGrievance(
            String grievanceText,
            String proofUrl,
            String proofFileName,
            int provisionalMeritNumber
    ) {

        try {

            String email =
                    Student.getInstance()
                            .getEmail();

            if (
                    email == null ||
                    email.isBlank()
            ) {

                return false;
            }

            Map<String, Object> data =
                    new HashMap<>();

            data.put(
                    "studentEmail",
                    email
            );

            data.put(
                    "candidateName",
                    safe(
                            Student.getInstance()
                                    .getCandidateName()
                    )
            );

            data.put(
                    "grievanceText",
                    grievanceText
            );

            data.put(
                    "proofUrl",
                    proofUrl == null
                            ? ""
                            : proofUrl
            );

            data.put(
                    "proofFileName",
                    proofFileName == null
                            ? ""
                            : proofFileName
            );

            data.put(
                    "provisionalMeritNumber",
                    provisionalMeritNumber
            );

            data.put(
                    "status",
                    "Pending"
            );

            data.put(
                    "counsellorComment",
                    ""
            );

            data.put(
                    "submittedAt",
                    Timestamp.now()
            );

            data.put(
                    "reviewedAt",
                    null
            );

            db.collection("Grievances")
                    .document(email)
                    .set(
                            data,
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
    // GET CURRENT STUDENT GRIEVANCE
    // =========================================================

    public GrievanceRecord getCurrentStudentGrievance() {

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
                    db.collection("Grievances")
                            .document(email)
                            .get()
                            .get();

            if (!document.exists()) {

                return null;
            }

            return convert(
                    document
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // CHECK PENDING
    // =========================================================

    public boolean hasPendingGrievance() {

        GrievanceRecord record =
                getCurrentStudentGrievance();

        return record != null &&
                "Pending".equalsIgnoreCase(
                        record.getStatus()
                );
    }

    // =========================================================
    // GET ALL GRIEVANCES
    // =========================================================

    public List<GrievanceRecord> getAllGrievances() {

        List<GrievanceRecord> grievances =
                new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection("Grievances")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                grievances.add(
                        convert(document)
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return grievances;
    }

    // =========================================================
    // APPROVE
    // =========================================================

    public boolean approveGrievance(
            String studentEmail,
            String counsellorComment
    ) {

        return updateStatus(
                studentEmail,
                "Approved",
                counsellorComment
        );
    }

    // =========================================================
    // REJECT
    // =========================================================

    public boolean rejectGrievance(
            String studentEmail,
            String counsellorComment
    ) {

        return updateStatus(
                studentEmail,
                "Rejected",
                counsellorComment
        );
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    private boolean updateStatus(
            String studentEmail,
            String status,
            String comment
    ) {

        try {

            Map<String, Object> update =
                    new HashMap<>();

            update.put(
                    "status",
                    status
            );

            update.put(
                    "counsellorComment",
                    comment == null
                            ? ""
                            : comment
            );

            update.put(
                    "reviewedAt",
                    Timestamp.now()
            );

            db.collection("Grievances")
                    .document(studentEmail)
                    .set(
                            update,
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
    // COUNTS
    // =========================================================

    public int getPendingCount() {

        return countByStatus(
                "Pending"
        );
    }

    public int getApprovedCount() {

        return countByStatus(
                "Approved"
        );
    }

    public int getRejectedCount() {

        return countByStatus(
                "Rejected"
        );
    }

    private int countByStatus(
            String status
    ) {

        int count =
                0;

        try {

            QuerySnapshot snapshot =
                    db.collection("Grievances")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                String currentStatus =
                        document.getString(
                                "status"
                        );

                if (
                        status.equalsIgnoreCase(
                                currentStatus
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
    // CONVERT
    // =========================================================

    private GrievanceRecord convert(
            DocumentSnapshot document
    ) {

        GrievanceRecord record =
                new GrievanceRecord();

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

        record.setGrievanceText(
                document.getString(
                        "grievanceText"
                )
        );

        record.setProofUrl(
                document.getString(
                        "proofUrl"
                )
        );

        record.setProofFileName(
                document.getString(
                        "proofFileName"
                )
        );

        record.setStatus(
                document.getString(
                        "status"
                )
        );

        record.setCounsellorComment(
                document.getString(
                        "counsellorComment"
                )
        );

        Long meritNumber =
                document.getLong(
                        "provisionalMeritNumber"
                );

        if (meritNumber != null) {

            record.setProvisionalMeritNumber(
                    meritNumber.intValue()
            );
        }

        return record;
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

    // =========================================================
    // RECORD
    // =========================================================

    public static class GrievanceRecord {

        private String studentEmail;
        private String candidateName;
        private String grievanceText;
        private String proofUrl;
        private String proofFileName;
        private String status;
        private String counsellorComment;

        private int provisionalMeritNumber;

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

        public String getGrievanceText() {
            return grievanceText;
        }

        public void setGrievanceText(
                String grievanceText
        ) {
            this.grievanceText =
                    grievanceText;
        }

        public String getProofUrl() {
            return proofUrl;
        }

        public void setProofUrl(
                String proofUrl
        ) {
            this.proofUrl =
                    proofUrl;
        }

        public String getProofFileName() {
            return proofFileName;
        }

        public void setProofFileName(
                String proofFileName
        ) {
            this.proofFileName =
                    proofFileName;
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

        public String getCounsellorComment() {
            return counsellorComment;
        }

        public void setCounsellorComment(
                String counsellorComment
        ) {
            this.counsellorComment =
                    counsellorComment;
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
    }
}