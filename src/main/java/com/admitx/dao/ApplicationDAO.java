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

    private final NotificationDAO notificationDAO =
            new NotificationDAO();

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

        if (!areAllUploadedDocumentsVerified(studentEmail)) {
            return false;
        }

        boolean verified =
                updateVerification(
                        studentEmail,
                        "Verified"
                );

        if (verified) {
            notificationDAO.createNotification(
                    studentEmail,
                    "Application Verified",
                    "Your application and uploaded documents have been verified by the counsellor.",
                    "APPLICATION_VERIFIED"
            );
        }

        return verified;
    }

    // =========================================================
    // COUNSELLOR - REJECT
    // =========================================================

    public boolean rejectApplication(
            String studentEmail
    ) {

        boolean rejected =
                updateVerification(
                        studentEmail,
                        "Rejected"
                );

        if (rejected) {
            notificationDAO.createNotification(
                    studentEmail,
                    "Application Rejected",
                    "Your application requires attention. Please check the counsellor remark for details.",
                    "APPLICATION_REJECTED"
            );
        }

        return rejected;
    }

    // =========================================================
    // COUNSELLOR - REQUEST CORRECTION
    // =========================================================

    public boolean requestCorrection(
            String studentEmail,
            String comment
    ) {

        try {
            if (studentEmail == null || studentEmail.isBlank()) {
                return false;
            }

            String email = studentEmail.trim().toLowerCase();

            Map<String, Object> update = new HashMap<>();
            update.put("verificationStatus", "Correction Required");
            update.put("locked", false);
            update.put("counsellorComment", comment == null ? "" : comment.trim());
            update.put("correctionRequestedAt", Timestamp.now());

            db.collection("Applications")
                    .document(email)
                    .set(update, SetOptions.merge())
                    .get();

            notificationDAO.createNotification(
                    email,
                    "Correction Required",
                    comment == null || comment.isBlank()
                            ? "The counsellor requested corrections in your application."
                            : "Counsellor remark: " + comment.trim(),
                    "CORRECTION_REQUIRED"
            );

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // STUDENT - RESUBMIT CORRECTED DOCUMENTS
    // =========================================================

    public boolean resubmitCorrections() {

        try {
            String email = Student.getInstance().getEmail();

            if (email == null || email.isBlank()) {
                return false;
            }

            email = email.trim().toLowerCase();

            if (hasRejectedDocuments(email)) {
                return false;
            }

            Map<String, Object> update = new HashMap<>();
            update.put("status", "Submitted");
            update.put("verificationStatus", "Pending");
            update.put("locked", true);
            update.put("correctionResubmittedAt", Timestamp.now());

            db.collection("Applications")
                    .document(email)
                    .set(update, SetOptions.merge())
                    .get();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
    // STUDENT - CONSOLIDATED APPLICATION STATUS (ONE READ)
    // =========================================================

    public ApplicationStatusData getCurrentApplicationStatusData() {

        try {
            String email = Student.getInstance().getEmail();

            if (email == null || email.isBlank()) {
                return new ApplicationStatusData("Draft", "Not Submitted", false, "");
            }

            DocumentSnapshot document = db.collection("Applications")
                    .document(email)
                    .get()
                    .get();

            if (!document.exists()) {
                return new ApplicationStatusData("Draft", "Not Submitted", false, "");
            }

            String status = document.getString("status");
            String verificationStatus = document.getString("verificationStatus");

            if (status == null || status.isBlank()) {
                status = "Draft";
            }

            if (verificationStatus == null || verificationStatus.isBlank()) {
                verificationStatus = "Not Submitted";
            }

            boolean submitted = "Submitted".equalsIgnoreCase(status);
            String counsellorComment = document.getString("counsellorComment");

            return new ApplicationStatusData(
                    status,
                    verificationStatus,
                    submitted,
                    counsellorComment == null ? "" : counsellorComment
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new ApplicationStatusData("Draft", "Not Submitted", false, "");
        }
    }

    public static class ApplicationStatusData {
        private final String applicationStatus;
        private final String verificationStatus;
        private final boolean submitted;
        private final String counsellorComment;

        public ApplicationStatusData(
                String applicationStatus,
                String verificationStatus,
                boolean submitted
        ) {
            this(applicationStatus, verificationStatus, submitted, "");
        }

        public ApplicationStatusData(
                String applicationStatus,
                String verificationStatus,
                boolean submitted,
                String counsellorComment
        ) {
            this.applicationStatus = applicationStatus;
            this.verificationStatus = verificationStatus;
            this.submitted = submitted;
            this.counsellorComment = counsellorComment == null ? "" : counsellorComment;
        }

        public String getApplicationStatus() {
            return applicationStatus;
        }

        public String getVerificationStatus() {
            return verificationStatus;
        }

        public boolean isSubmitted() {
            return submitted;
        }

        public String getCounsellorComment() {
            return counsellorComment;
        }
    }



    // =========================================================
    // COUNSELLOR - STUDENT DOCUMENT URLS
    // =========================================================

    @SuppressWarnings("unchecked")
    public Map<String, String> getStudentDocumentUrls(String studentEmail) {

        Map<String, String> urls = new HashMap<>();

        try {
            if (studentEmail == null || studentEmail.isBlank()) {
                return urls;
            }

            String email = studentEmail.trim().toLowerCase();

            DocumentSnapshot document =
                    db.collection("Students")
                            .document(email)
                            .get()
                            .get();

            if (!document.exists()) {
                return urls;
            }

            Object value = document.get("uploadedDocumentUrls");

            if (value instanceof Map<?, ?> rawMap) {
                for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
                    if (entry.getKey() != null && entry.getValue() != null) {
                        urls.put(
                                String.valueOf(entry.getKey()),
                                String.valueOf(entry.getValue())
                        );
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return urls;
    }

    // =========================================================
    // COUNSELLOR - LOAD DOCUMENT VERIFICATION
    // =========================================================

    @SuppressWarnings("unchecked")
    public Map<String, DocumentVerificationRecord> getDocumentVerification(
            String studentEmail
    ) {

        Map<String, DocumentVerificationRecord> result = new HashMap<>();

        try {
            if (studentEmail == null || studentEmail.isBlank()) {
                return result;
            }

            String email = studentEmail.trim().toLowerCase();

            DocumentSnapshot document =
                    db.collection("Applications")
                            .document(email)
                            .get()
                            .get();

            if (!document.exists()) {
                return result;
            }

            Object value = document.get("documentVerification");

            if (!(value instanceof Map<?, ?> rawMap)) {
                return result;
            }

            for (Map.Entry<?, ?> entry : rawMap.entrySet()) {

                String documentName = String.valueOf(entry.getKey());
                Object recordValue = entry.getValue();

                if (recordValue instanceof Map<?, ?> recordMap) {
                    String status = safeObject(recordMap.get("status"));
                    String remark = safeObject(recordMap.get("remark"));

                    result.put(
                            documentName,
                            new DocumentVerificationRecord(status, remark)
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // =========================================================
    // COUNSELLOR - SAVE ONE DOCUMENT VERIFICATION
    // =========================================================

    public boolean saveDocumentVerification(
            String studentEmail,
            String documentName,
            String status,
            String remark
    ) {

        try {
            if (studentEmail == null || studentEmail.isBlank()
                    || documentName == null || documentName.isBlank()
                    || status == null || status.isBlank()) {
                return false;
            }

            String email = studentEmail.trim().toLowerCase();

            Map<String, DocumentVerificationRecord> existing =
                    getDocumentVerification(email);

            Map<String, Object> verificationMap = new HashMap<>();

            for (Map.Entry<String, DocumentVerificationRecord> entry
                    : existing.entrySet()) {

                Map<String, Object> item = new HashMap<>();
                item.put("status", entry.getValue().getStatus());
                item.put("remark", entry.getValue().getRemark());

                verificationMap.put(entry.getKey(), item);
            }

            Map<String, Object> selected = new HashMap<>();
            selected.put("status", status.trim());
            selected.put("remark", remark == null ? "" : remark.trim());
            selected.put("updatedAt", Timestamp.now());

            verificationMap.put(documentName, selected);

            Map<String, Object> update = new HashMap<>();
            update.put("documentVerification", verificationMap);
            update.put("documentVerificationUpdatedAt", Timestamp.now());

            if ("Rejected".equalsIgnoreCase(status)) {
                update.put("verificationStatus", "Correction Required");
                update.put("locked", false);
                update.put("correctionRequestedAt", Timestamp.now());
            }

            db.collection("Applications")
                    .document(email)
                    .set(update, SetOptions.merge())
                    .get();

            if ("Verified".equalsIgnoreCase(status)) {
                notificationDAO.createNotification(
                        email,
                        "Document Verified",
                        documentName + " has been verified by the counsellor.",
                        "DOCUMENT_VERIFIED"
                );

            } else if ("Rejected".equalsIgnoreCase(status)) {

                String rejectionMessage =
                        documentName
                                + " was rejected."
                                + (remark == null || remark.isBlank()
                                ? " Please upload the corrected document."
                                : " Reason: " + remark.trim());

                notificationDAO.createNotification(
                        email,
                        "Document Correction Required",
                        rejectionMessage,
                        "DOCUMENT_REJECTED"
                );
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // COUNSELLOR - CHECK WHETHER ALL UPLOADED DOCUMENTS VERIFIED
    // =========================================================

    public boolean areAllUploadedDocumentsVerified(String studentEmail) {

        Map<String, String> urls = getStudentDocumentUrls(studentEmail);

        if (urls.isEmpty()) {
            return false;
        }

        Map<String, DocumentVerificationRecord> verification =
                getDocumentVerification(studentEmail);

        for (String documentName : urls.keySet()) {
            DocumentVerificationRecord record = verification.get(documentName);

            if (record == null
                    || !"Verified".equalsIgnoreCase(record.getStatus())) {
                return false;
            }
        }

        return true;
    }

    public boolean hasRejectedDocuments(String studentEmail) {
        Map<String, DocumentVerificationRecord> verification =
                getDocumentVerification(studentEmail);

        for (DocumentVerificationRecord record : verification.values()) {
            if ("Rejected".equalsIgnoreCase(record.getStatus())) {
                return true;
            }
        }

        return false;
    }

    public boolean markDocumentReuploaded(
            String studentEmail,
            String documentName
    ) {
        return saveDocumentVerification(
                studentEmail,
                documentName,
                "Pending",
                "Corrected document uploaded by student. Awaiting re-verification."
        );
    }

    public String getCounsellorComment(String studentEmail) {
        try {
            if (studentEmail == null || studentEmail.isBlank()) {
                return "";
            }

            DocumentSnapshot document = db.collection("Applications")
                    .document(studentEmail.trim().toLowerCase())
                    .get()
                    .get();

            if (!document.exists()) {
                return "";
            }

            return safe(document.getString("counsellorComment"));

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static class DocumentVerificationRecord {

        private final String status;
        private final String remark;

        public DocumentVerificationRecord(String status, String remark) {
            this.status = status == null || status.isBlank()
                    ? "Pending"
                    : status;
            this.remark = remark == null ? "" : remark;
        }

        public String getStatus() {
            return status;
        }

        public String getRemark() {
            return remark;
        }
    }

    private String safeObject(Object value) {
        return value == null ? "" : String.valueOf(value);
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