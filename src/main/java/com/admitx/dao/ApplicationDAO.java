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

public class ApplicationDAO {

    private final Firestore db =
            FirebaseConfig.getFirestore();

    // =========================================================
    // STUDENT - SUBMIT APPLICATION
    // =========================================================

    public boolean submitApplication() {

        try {

            Student student =
                    Student.getInstance();

            String email =
                    student.getEmail();

            if (
                    email == null ||
                    email.isBlank()
            ) {

                return false;
            }

            if (isApplicationSubmitted()) {

                return true;
            }

            Map<String, Object> application =
                    new HashMap<>();

            application.put(
                    "studentEmail",
                    email
            );

            application.put(
                    "studentName",
                    safe(student.getUsername())
            );

            application.put(
                    "candidateName",
                    safe(student.getCandidateName())
            );

            application.put(
                    "mobileNumber",
                    safe(student.getMobileno())
            );

            application.put(
                    "category",
                    safe(student.getCategory())
            );

            application.put(
                    "status",
                    "Submitted"
            );

            application.put(
                    "verificationStatus",
                    "Pending"
            );

            application.put(
                    "counsellorComment",
                    ""
            );

            application.put(
                    "locked",
                    true
            );

            application.put(
                    "submittedAt",
                    Timestamp.now()
            );

            db.collection(
                    "Applications"
            )
            .document(
                    email
            )
            .set(
                    application,
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
    // STUDENT - CHECK SUBMISSION
    // =========================================================

    public boolean isApplicationSubmitted() {

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

            DocumentSnapshot document =
                    db.collection(
                            "Applications"
                    )
                    .document(
                            email
                    )
                    .get()
                    .get();

            if (!document.exists()) {

                return false;
            }

            String status =
                    document.getString(
                            "status"
                    );

            return "Submitted"
                    .equalsIgnoreCase(status);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // STUDENT - APPLICATION STATUS
    // =========================================================

    public String getApplicationStatus() {

        try {

            String email =
                    Student.getInstance()
                            .getEmail();

            if (
                    email == null ||
                    email.isBlank()
            ) {

                return "Draft";
            }

            DocumentSnapshot document =
                    db.collection(
                            "Applications"
                    )
                    .document(
                            email
                    )
                    .get()
                    .get();

            if (!document.exists()) {

                return "Draft";
            }

            String status =
                    document.getString(
                            "status"
                    );

            if (
                    status == null ||
                    status.isBlank()
            ) {

                return "Draft";
            }

            return status;

        } catch (Exception e) {

            e.printStackTrace();

            return "Draft";
        }
    }

    // =========================================================
    // STUDENT - VERIFICATION STATUS
    // =========================================================

    public String getVerificationStatus() {

        try {

            String email =
                    Student.getInstance()
                            .getEmail();

            if (
                    email == null ||
                    email.isBlank()
            ) {

                return "Not Submitted";
            }

            DocumentSnapshot document =
                    db.collection(
                            "Applications"
                    )
                    .document(
                            email
                    )
                    .get()
                    .get();

            if (!document.exists()) {

                return "Not Submitted";
            }

            String status =
                    document.getString(
                            "verificationStatus"
                    );

            if (
                    status == null ||
                    status.isBlank()
            ) {

                return "Pending";
            }

            return status;

        } catch (Exception e) {

            e.printStackTrace();

            return "Not Submitted";
        }
    }

    // =========================================================
    // STUDENT - LOCK STATUS
    // =========================================================

    public boolean isApplicationLocked() {

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

            DocumentSnapshot document =
                    db.collection(
                            "Applications"
                    )
                    .document(
                            email
                    )
                    .get()
                    .get();

            if (!document.exists()) {

                return false;
            }

            Boolean locked =
                    document.getBoolean(
                            "locked"
                    );

            return locked != null
                    && locked;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // COUNSELLOR - LOAD APPLICATIONS
    // =========================================================

    public List<ApplicationRecord> getAllApplications() {

        List<ApplicationRecord> applications =
                new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "Applications"
                    )
                    .get()
                    .get();

            for (
                    QueryDocumentSnapshot document :
                    snapshot.getDocuments()
            ) {

                ApplicationRecord application =
                        new ApplicationRecord();

                application.setStudentEmail(
                        safe(
                                document.getString(
                                        "studentEmail"
                                )
                        )
                );

                application.setStudentName(
                        safe(
                                document.getString(
                                        "studentName"
                                )
                        )
                );

                application.setCandidateName(
                        safe(
                                document.getString(
                                        "candidateName"
                                )
                        )
                );

                application.setMobileNumber(
                        safe(
                                document.getString(
                                        "mobileNumber"
                                )
                        )
                );

                application.setCategory(
                        safe(
                                document.getString(
                                        "category"
                                )
                        )
                );

                application.setStatus(
                        safe(
                                document.getString(
                                        "status"
                                )
                        )
                );

                application.setVerificationStatus(
                        safe(
                                document.getString(
                                        "verificationStatus"
                                )
                        )
                );

                application.setCounsellorComment(
                        safe(
                                document.getString(
                                        "counsellorComment"
                                )
                        )
                );

                Timestamp submittedAt =
                        document.getTimestamp(
                                "submittedAt"
                        );

                if (submittedAt != null) {

                    application.setSubmittedAt(
                            submittedAt.toDate()
                                    .toString()
                    );

                } else {

                    application.setSubmittedAt(
                            "Not Available"
                    );
                }

                applications.add(
                        application
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return applications;
    }

    // =========================================================
    // COUNSELLOR - VERIFY
    // =========================================================

    public boolean verifyApplication(
            String studentEmail
    ) {

        return updateVerification(
                studentEmail,
                "Verified"
        );
    }

    // =========================================================
    // COUNSELLOR - REJECT
    // =========================================================

    public boolean rejectApplication(
            String studentEmail
    ) {

        return updateVerification(
                studentEmail,
                "Rejected"
        );
    }

    // =========================================================
    // COUNSELLOR - SAVE COMMENT
    // =========================================================

    public boolean saveCounsellorComment(
            String studentEmail,
            String comment
    ) {

        try {

            if (
                    studentEmail == null ||
                    studentEmail.isBlank()
            ) {

                return false;
            }

            Map<String, Object> update =
                    new HashMap<>();

            update.put(
                    "counsellorComment",
                    comment == null
                            ? ""
                            : comment.trim()
            );

            update.put(
                    "commentUpdatedAt",
                    Timestamp.now()
            );

            db.collection(
                    "Applications"
            )
            .document(
                    studentEmail
            )
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
    // COUNSELLOR - COUNTS
    // =========================================================

    public int getTotalApplicationCount() {

        return getAllApplications()
                .size();
    }

    public int getPendingApplicationCount() {

        int count = 0;

        for (
                ApplicationRecord application :
                getAllApplications()
        ) {

            if (
                    "Pending".equalsIgnoreCase(
                            application
                                    .getVerificationStatus()
                    )
            ) {

                count++;
            }
        }

        return count;
    }

    public int getVerifiedApplicationCount() {

        int count = 0;

        for (
                ApplicationRecord application :
                getAllApplications()
        ) {

            if (
                    "Verified".equalsIgnoreCase(
                            application
                                    .getVerificationStatus()
                    )
            ) {

                count++;
            }
        }

        return count;
    }

    public int getRejectedApplicationCount() {

        int count = 0;

        for (
                ApplicationRecord application :
                getAllApplications()
        ) {

            if (
                    "Rejected".equalsIgnoreCase(
                            application
                                    .getVerificationStatus()
                    )
            ) {

                count++;
            }
        }

        return count;
    }

    // =========================================================
    // PRIVATE UPDATE
    // =========================================================

    private boolean updateVerification(
            String studentEmail,
            String verificationStatus
    ) {

        try {

            if (
                    studentEmail == null ||
                    studentEmail.isBlank()
            ) {

                return false;
            }

            DocumentSnapshot document =
                    db.collection(
                            "Applications"
                    )
                    .document(
                            studentEmail
                    )
                    .get()
                    .get();

            if (!document.exists()) {

                return false;
            }

            Map<String, Object> update =
                    new HashMap<>();

            update.put(
                    "verificationStatus",
                    verificationStatus
            );

            update.put(
                    "verifiedAt",
                    Timestamp.now()
            );

            db.collection(
                    "Applications"
            )
            .document(
                    studentEmail
            )
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
    // SAFE
    // =========================================================

    private String safe(
            String value
    ) {

        if (value == null) {

            return "";
        }

        return value;
    }

    // =========================================================
    // APPLICATION RECORD
    // =========================================================

    public static class ApplicationRecord {

        private String studentEmail;
        private String studentName;
        private String candidateName;
        private String mobileNumber;
        private String category;
        private String status;
        private String verificationStatus;
        private String submittedAt;
        private String counsellorComment;

        public ApplicationRecord() {
        }

        public String getStudentEmail() {
            return studentEmail;
        }

        public void setStudentEmail(
                String studentEmail
        ) {
            this.studentEmail =
                    studentEmail;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(
                String studentName
        ) {
            this.studentName =
                    studentName;
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

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(
                String mobileNumber
        ) {
            this.mobileNumber =
                    mobileNumber;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(
                String status
        ) {
            this.status =
                    status;
        }

        public String getVerificationStatus() {
            return verificationStatus;
        }

        public void setVerificationStatus(
                String verificationStatus
        ) {
            this.verificationStatus =
                    verificationStatus;
        }

        public String getSubmittedAt() {
            return submittedAt;
        }

        public void setSubmittedAt(
                String submittedAt
        ) {
            this.submittedAt =
                    submittedAt;
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
    }
}