package com.admitx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.Student;
import com.admitx.view.PreferenceFillingPage;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PreferenceDAO {

    private final Firestore db =
            new FirebaseConfig().getFirestore();

    // =========================================================
    // SAVE STUDENT PREFERENCES
    // =========================================================

    public boolean savePreferences(
            ObservableList<PreferenceFillingPage.Preference> preferences
    ) {

        try {

            String studentEmail =
                    Student.getInstance()
                            .getEmail();

            if (
                    studentEmail == null ||
                    studentEmail.isBlank()
            ) {

                System.out.println(
                        "No logged-in student found."
                );

                return false;
            }

            if (
                    preferences == null ||
                    preferences.isEmpty()
            ) {

                System.out.println(
                        "Preference list is empty."
                );

                return false;
            }

            // Do not allow new locking when choice filling is closed
            if (!isChoiceFillingOpen()) {

                System.out.println(
                        "Choice filling is currently closed."
                );

                return false;
            }

            // Already locked
            if (isPreferenceLocked()) {

                System.out.println(
                        "Option form is already locked."
                );

                return false;
            }

            List<Map<String, Object>> preferenceList =
                    new ArrayList<>();

            for (
                    PreferenceFillingPage.Preference preference
                    : preferences
            ) {

                Map<String, Object> data =
                        new HashMap<>();

                data.put(
                        "preferenceNumber",
                        preference.getPreferenceNumber()
                );

                data.put(
                        "college",
                        preference.getCollege()
                );

                data.put(
                        "branch",
                        preference.getBranch()
                );

                preferenceList.add(
                        data
                );
            }

            Map<String, Object> studentPreferenceData =
                    new HashMap<>();

            studentPreferenceData.put(
                    "studentEmail",
                    studentEmail
            );

            studentPreferenceData.put(
                    "preferences",
                    preferenceList
            );

            studentPreferenceData.put(
                    "locked",
                    true
            );

            studentPreferenceData.put(
                    "lockedAt",
                    FieldValue.serverTimestamp()
            );

            db.collection(
                    "StudentPreferences"
            )
            .document(
                    studentEmail
            )
            .set(
                    studentPreferenceData
            )
            .get();

            System.out.println(
                    "Preferences saved successfully."
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Failed to save preferences."
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // CURRENT STUDENT LOCK STATUS
    // =========================================================

    public boolean isPreferenceLocked() {

        try {

            String studentEmail =
                    Student.getInstance()
                            .getEmail();

            if (
                    studentEmail == null ||
                    studentEmail.isBlank()
            ) {

                return false;
            }

            DocumentSnapshot document =
                    db.collection(
                            "StudentPreferences"
                    )
                    .document(
                            studentEmail
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

            return Boolean.TRUE.equals(
                    locked
            );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // LOAD CURRENT STUDENT PREFERENCES
    // =========================================================

    public ObservableList<PreferenceFillingPage.Preference>
    loadPreferences() {

        ObservableList<PreferenceFillingPage.Preference>
                result =
                FXCollections.observableArrayList();

        try {

            String studentEmail =
                    Student.getInstance()
                            .getEmail();

            if (
                    studentEmail == null ||
                    studentEmail.isBlank()
            ) {

                return result;
            }

            DocumentSnapshot document =
                    db.collection(
                            "StudentPreferences"
                    )
                    .document(
                            studentEmail
                    )
                    .get()
                    .get();

            if (!document.exists()) {

                return result;
            }

            Object value =
                    document.get(
                            "preferences"
                    );

            if (!(value instanceof List<?>)) {

                return result;
            }

            List<?> list =
                    (List<?>) value;

            for (Object item : list) {

                if (!(item instanceof Map<?, ?>)) {

                    continue;
                }

                Map<?, ?> map =
                        (Map<?, ?>) item;

                Object numberObject =
                        map.get(
                                "preferenceNumber"
                        );

                Object collegeObject =
                        map.get(
                                "college"
                        );

                Object branchObject =
                        map.get(
                                "branch"
                        );

                int preferenceNumber =
                        0;

                if (
                        numberObject
                        instanceof Number
                ) {

                    preferenceNumber =
                            ((Number) numberObject)
                                    .intValue();
                }

                String college =
                        collegeObject == null
                                ? ""
                                : collegeObject.toString();

                String branch =
                        branchObject == null
                                ? ""
                                : branchObject.toString();

                result.add(
                        new PreferenceFillingPage.Preference(
                                preferenceNumber,
                                college,
                                branch
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    // =========================================================
    // CHOICE FILLING STATUS
    // =========================================================

    public boolean isChoiceFillingOpen() {

        try {

            DocumentSnapshot document =
                    db.collection(
                            "SystemSettings"
                    )
                    .document(
                            "optionForm"
                    )
                    .get()
                    .get();

            if (!document.exists()) {

                return false;
            }

            Boolean open =
                    document.getBoolean(
                            "choiceFillingOpen"
                    );

            return Boolean.TRUE.equals(
                    open
            );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // COUNSELLOR - OPEN CHOICE FILLING
    // =========================================================

    public boolean openChoiceFilling() {

        try {

            Map<String, Object> data =
                    new HashMap<>();

            data.put(
                    "choiceFillingOpen",
                    true
            );

            data.put(
                    "updatedAt",
                    FieldValue.serverTimestamp()
            );

            db.collection(
                    "SystemSettings"
            )
            .document(
                    "optionForm"
            )
            .set(
                    data
            )
            .get();

            System.out.println(
                    "Choice filling opened."
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // COUNSELLOR - CLOSE CHOICE FILLING
    // =========================================================

    public boolean closeChoiceFilling() {

        try {

            Map<String, Object> data =
                    new HashMap<>();

            data.put(
                    "choiceFillingOpen",
                    false
            );

            data.put(
                    "updatedAt",
                    FieldValue.serverTimestamp()
            );

            db.collection(
                    "SystemSettings"
            )
            .document(
                    "optionForm"
            )
            .set(
                    data
            )
            .get();

            System.out.println(
                    "Choice filling closed."
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // COUNSELLOR - GET ALL STUDENT OPTION FORMS
    // =========================================================

    public List<StudentPreferenceRecord>
    getAllStudentPreferences() {

        List<StudentPreferenceRecord> records =
                new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "StudentPreferences"
                    )
                    .get()
                    .get();

            for (
                    QueryDocumentSnapshot document :
                    snapshot.getDocuments()
            ) {

                StudentPreferenceRecord record =
                        new StudentPreferenceRecord();

                String email =
                        document.getString(
                                "studentEmail"
                        );

                if (
                        email == null ||
                        email.isBlank()
                ) {

                    email =
                            document.getId();
                }

                record.setStudentEmail(
                        email
                );

                Boolean locked =
                        document.getBoolean(
                                "locked"
                        );

                record.setLocked(
                        Boolean.TRUE.equals(
                                locked
                        )
                );

                Timestamp lockedAt =
                        document.getTimestamp(
                                "lockedAt"
                        );

                if (lockedAt != null) {

                    record.setLockedAt(
                            lockedAt.toDate()
                                    .toString()
                    );

                } else {

                    record.setLockedAt(
                            "-"
                    );
                }

                Object preferenceObject =
                        document.get(
                                "preferences"
                        );

                if (
                        preferenceObject
                        instanceof List<?>
                ) {

                    List<?> list =
                            (List<?>) preferenceObject;

                    for (Object item : list) {

                        if (
                                !(item instanceof Map<?, ?>)
                        ) {

                            continue;
                        }

                        Map<?, ?> map =
                                (Map<?, ?>) item;

                        PreferenceRecord preference =
                                new PreferenceRecord();

                        Object number =
                                map.get(
                                        "preferenceNumber"
                                );

                        if (
                                number instanceof Number
                        ) {

                            preference
                                    .setPreferenceNumber(
                                            ((Number) number)
                                                    .intValue()
                                    );
                        }

                        Object college =
                                map.get(
                                        "college"
                                );

                        preference.setCollege(
                                college == null
                                        ? ""
                                        : college.toString()
                        );

                        Object branch =
                                map.get(
                                        "branch"
                                );

                        preference.setBranch(
                                branch == null
                                        ? ""
                                        : branch.toString()
                        );

                        record.getPreferences()
                                .add(
                                        preference
                                );
                    }
                }

                records.add(
                        record
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return records;
    }

    // =========================================================
    // COUNSELLOR COUNTS
    // =========================================================

    public int getStartedPreferenceCount() {

        return getAllStudentPreferences()
                .size();
    }

    public int getLockedPreferenceCount() {

        int count =
                0;

        for (
                StudentPreferenceRecord record :
                getAllStudentPreferences()
        ) {

            if (
                    record.isLocked()
            ) {

                count++;
            }
        }

        return count;
    }

    // =========================================================
    // STUDENT PREFERENCE RECORD
    // =========================================================

    public static class StudentPreferenceRecord {

        private String studentEmail;

        private boolean locked;

        private String lockedAt;

        private final List<PreferenceRecord>
                preferences =
                new ArrayList<>();

        public StudentPreferenceRecord() {
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

        public boolean isLocked() {

            return locked;
        }

        public void setLocked(
                boolean locked
        ) {

            this.locked =
                    locked;
        }

        public String getLockedAt() {

            return lockedAt;
        }

        public void setLockedAt(
                String lockedAt
        ) {

            this.lockedAt =
                    lockedAt;
        }

        public List<PreferenceRecord>
        getPreferences() {

            return preferences;
        }

        public int getPreferenceCount() {

            return preferences.size();
        }

        public String getStatus() {

            return locked
                    ? "Locked"
                    : "Not Locked";
        }
    }

    // =========================================================
    // PREFERENCE RECORD
    // =========================================================

    public static class PreferenceRecord {

        private int preferenceNumber;

        private String college;

        private String branch;

        public PreferenceRecord() {
        }

        public int getPreferenceNumber() {

            return preferenceNumber;
        }

        public void setPreferenceNumber(
                int preferenceNumber
        ) {

            this.preferenceNumber =
                    preferenceNumber;
        }

        public String getCollege() {

            return college;
        }

        public void setCollege(
                String college
        ) {

            this.college =
                    college;
        }

        public String getBranch() {

            return branch;
        }

        public void setBranch(
                String branch
        ) {

            this.branch =
                    branch;
        }
    }
}