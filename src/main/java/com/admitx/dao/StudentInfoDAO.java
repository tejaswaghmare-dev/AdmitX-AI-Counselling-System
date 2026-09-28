package com.admitx.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.Student;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

public class StudentInfoDAO {

    private final Firestore db =
            FirebaseConfig.getFirestore();

    public StudentInfoDAO() {

        if (db == null) {
            throw new IllegalStateException(
                    "Firestore connection is not available."
            );
        }
    }

    // =========================================================
    // PERSONAL DETAILS
    // =========================================================

    public boolean saveStudentInfo(
            Student student
    ) {

        try {

            String email =
                    student.getEmail();

            if (
                    email == null
                    ||
                    email.isBlank()
            ) {

                System.out.println(
                        "Cannot update student. No logged-in student."
                );

                return false;
            }

            db.collection("Students")
                    .document(email)
                    .update(
                            "candidateName",
                            student.getCandidateName(),

                            "fatherName",
                            student.getFatherName(),

                            "motherName",
                            student.getMotherName(),

                            "gender",
                            student.getGender(),

                            "dob",
                            student.getDob(),

                            "nationality",
                            student.getNationality(),

                            "aadhaar",
                            student.getAadhaar(),

                            "category",
                            student.getCategory(),

                            "religion",
                            student.getReligion(),

                            "caste",
                            student.getCaste(),

                            "minority",
                            student.getMinority(),

                            "pwd",
                            student.getPwd(),

                            "defence",
                            student.getDefence(),

                            "tfws",
                            student.getTfws(),

                            "ews",
                            student.getEws()
                    )
                    .get();

            System.out.println(
                    "Student personal information updated successfully!"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Failed to update student personal information."
            );

            e.printStackTrace();
            return false;
        }
    }

    public boolean saveApplicationSection(Student student) {
        try {
            String email = student.getEmail();
            if (email == null || email.isBlank()) {
                return false;
            }

            db.collection("Students")
                    .document(email.trim().toLowerCase())
                    .update(
                            "permanentAddress", student.getPermanentAddress(),
                            "correspondenceAddress", student.getCorrespondenceAddress(),
                            "correspondenceState", student.getCorrespondenceState(),
                            "correspondenceDistrict", student.getCorrespondenceDistrict(),
                            "correspondenceTaluka", student.getCorrespondenceTaluka(),
                            "correspondencePinCode", student.getCorrespondencePinCode(),
                            "state", student.getState(),
                            "district", student.getDistrict(),
                            "taluka", student.getTaluka(),
                            "pinCode", student.getPinCode(),
                            "sscDetails", student.getSscDetails(),
                            "hscDetails", student.getHscDetails(),
                            "diplomaDetails", student.getDiplomaDetails(),
                            "pcmMarks", student.getPcmMarks(),
                            "cetPercentile", student.getCetPercentile(),
                            "jeePercentile", student.getJeePercentile(),
                            "yearOfPassing", student.getYearOfPassing(),
                            "homeUniversity", student.getHomeUniversity(),
                            "candidateType", student.getCandidateType(),
                            "maharashtraType", student.getMaharashtraType(),
                            "domicileStatus", student.getDomicileStatus(),
                            "validityCertificate", student.getValidityCertificate(),
                            "ncl", student.getNcl(),
                            "income", student.getIncome(),
                            "orphan", student.getOrphan(),
                            "uploadedDocumentUrls", student.getUploadedDocumentUrls()
                    )
                    .get();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    ///////////////////////////////////////////////////////////
    /// 
    ///////////////////////////////////////////////////////////
    
    public void saveSignUpInfo(Student s){
        db.collection("Students")
                        .document(s.getEmail())
                        .set(s);
    }
    //////////////////////////////////////////////////////////
    /// 
    //////////////////////////////////////////////////////////
    

    public boolean loginStudentWithFirebase(String email) {

        try {

                if (email == null || email.isBlank()) {
                return false;
                }

                email = email.trim().toLowerCase();

                Student student = getStudentProfile(email);

                if (student == null) {

                System.out.println(
                        "Student profile not found in Firestore."
                );

                return false;
                }

                // =============================================
                // CREATE STUDENT SESSION
                // =============================================

                Student currentStudent = Student.getInstance();

                currentStudent.setUsername(student.getUsername());

                currentStudent.setEmail(student.getEmail());

                currentStudent.setMobileno(student.getMobileno());

                currentStudent.setPasswordHash(student.getPasswordHash());

                currentStudent.setCandidateName(student.getCandidateName());
                currentStudent.setFatherName(student.getFatherName());
                currentStudent.setMotherName(student.getMotherName());

                currentStudent.setGender(student.getGender());
                currentStudent.setDob(student.getDob());

                currentStudent.setNationality(student.getNationality());

                currentStudent.setAadhaar(student.getAadhaar());

                currentStudent.setCategory(student.getCategory());

                currentStudent.setReligion(student.getReligion());

                currentStudent.setCaste(student.getCaste());

                currentStudent.setMinority(student.getMinority());

                currentStudent.setPwd(student.getPwd());

                currentStudent.setDefence(student.getDefence());

                currentStudent.setTfws(student.getTfws());

                currentStudent.setEws(student.getEws());

                currentStudent.setPermanentAddress(
                        student.getPermanentAddress()
                );

                currentStudent.setCorrespondenceAddress(
                        student.getCorrespondenceAddress()
                );

                currentStudent.setCorrespondenceState(student.getCorrespondenceState());
                currentStudent.setCorrespondenceDistrict(student.getCorrespondenceDistrict());
                currentStudent.setCorrespondenceTaluka(student.getCorrespondenceTaluka());
                currentStudent.setCorrespondencePinCode(student.getCorrespondencePinCode());

                currentStudent.setState(student.getState());

                currentStudent.setDistrict(student.getDistrict());

                currentStudent.setTaluka(student.getTaluka());

                currentStudent.setPinCode(student.getPinCode());

                currentStudent.setSscDetails(student.getSscDetails());

                currentStudent.setHscDetails(student.getHscDetails());

                currentStudent.setDiplomaDetails(
                        student.getDiplomaDetails()
                );

                currentStudent.setPcmMarks(student.getPcmMarks());

                currentStudent.setCetPercentile(
                        student.getCetPercentile()
                );

                currentStudent.setJeePercentile(
                        student.getJeePercentile()
                );

                currentStudent.setYearOfPassing(
                        student.getYearOfPassing()
                );

                currentStudent.setHomeUniversity(
                        student.getHomeUniversity()
                );

                currentStudent.setCandidateType(
                        student.getCandidateType()
                );

                currentStudent.setMaharashtraType(
                        student.getMaharashtraType()
                );

                currentStudent.setDomicileStatus(
                        student.getDomicileStatus()
                );

                currentStudent.setValidityCertificate(
                        student.getValidityCertificate()
                );

                currentStudent.setNcl(student.getNcl());

                currentStudent.setIncome(student.getIncome());

                currentStudent.setOrphan(student.getOrphan());

                // =============================================
                // DOCUMENTS
                // =============================================

                currentStudent.getUploadedDocumentUrls().clear();

                if (student.getUploadedDocumentUrls() != null) {

                currentStudent.getUploadedDocumentUrls().putAll(
                        student.getUploadedDocumentUrls()
                );
                }

                System.out.println(
                        "Firebase login session created successfully: "
                                + currentStudent.getEmail()
                );

                return true;

        } catch (Exception e) {

                System.out.println(
                        "Failed to create Firebase student session."
                );

                e.printStackTrace();

                return false;
        }
        }
    // =========================================================
    // REGISTRATION
    // =========================================================

    public boolean registrationDetails(
            Student registeredStudent,
            String password
    ) {

        try {

            String email =
                    registeredStudent
                            .getEmail()
                            .trim()
                            .toLowerCase();

            registeredStudent.setEmail(
                    email
            );

            DocumentSnapshot existing =
                    db.collection("Students")
                            .document(email)
                            .get()
                            .get();

            if (
                    existing.exists()
            ) {

                System.out.println(
                        "Student already registered."
                );

                return false;
            }

            String passwordHash =
                    hashPassword(
                            password
                    );

            registeredStudent.setPasswordHash(
                    passwordHash
            );

            db.collection("Students")
                    .document(email)
                    .create(
                            registeredStudent
                    )
                    .get();

            System.out.println(
                    "Student registration info saved successfully!"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Failed to save student registration info!"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // LOGIN
    // =========================================================

    public boolean loginStudent(
            String email,
            String password
    ) {

        try {

            if (
                    email == null
                    ||
                    email.isBlank()
                    ||
                    password == null
                    ||
                    password.isBlank()
            ) {

                return false;
            }

            email =
                    email.trim()
                            .toLowerCase();

            DocumentSnapshot document =
                    db.collection("Students")
                            .document(email)
                            .get()
                            .get();

            if (
                    !document.exists()
            ) {

                System.out.println(
                        "Student not found."
                );

                return false;
            }

            Student student =
                    document.toObject(
                            Student.class
                    );

            if (
                    student == null
            ) {

                return false;
            }

            String savedPassword =
                    student.getPasswordHash();

            if (
                    savedPassword == null
            ) {

                System.out.println(
                        "Password not configured for this student."
                );

                return false;
            }

            String enteredPasswordHash =
                    hashPassword(
                            password
                    );

            boolean correct =
                    MessageDigest.isEqual(
                            savedPassword.getBytes(
                                    StandardCharsets.UTF_8
                            ),
                            enteredPasswordHash.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    );

            if (
                    !correct
            ) {

                System.out.println(
                        "Incorrect password."
                );

                return false;
            }

            Student currentStudent =
                    Student.getInstance();

            currentStudent.setUsername(
                    student.getUsername()
            );

            currentStudent.setEmail(
                    student.getEmail()
            );

            currentStudent.setMobileno(
                    student.getMobileno()
            );

            currentStudent.setPasswordHash(
                    student.getPasswordHash()
            );

            currentStudent.setCandidateName(student.getCandidateName());
            currentStudent.setFatherName(student.getFatherName());
            currentStudent.setMotherName(student.getMotherName());
            currentStudent.setGender(student.getGender());
            currentStudent.setDob(student.getDob());
            currentStudent.setNationality(student.getNationality());
            currentStudent.setAadhaar(student.getAadhaar());
            currentStudent.setCategory(student.getCategory());
            currentStudent.setReligion(student.getReligion());
            currentStudent.setCaste(student.getCaste());
            currentStudent.setMinority(student.getMinority());
            currentStudent.setPwd(student.getPwd());
            currentStudent.setDefence(student.getDefence());
            currentStudent.setTfws(student.getTfws());
            currentStudent.setEws(student.getEws());

            currentStudent.setPermanentAddress(student.getPermanentAddress());
            currentStudent.setCorrespondenceAddress(student.getCorrespondenceAddress());
            currentStudent.setState(student.getState());
            currentStudent.setDistrict(student.getDistrict());
            currentStudent.setTaluka(student.getTaluka());
            currentStudent.setPinCode(student.getPinCode());

            currentStudent.setSscDetails(student.getSscDetails());
            currentStudent.setHscDetails(student.getHscDetails());
            currentStudent.setDiplomaDetails(student.getDiplomaDetails());
            currentStudent.setPcmMarks(student.getPcmMarks());
            currentStudent.setCetPercentile(student.getCetPercentile());
            currentStudent.setJeePercentile(student.getJeePercentile());
            currentStudent.setYearOfPassing(student.getYearOfPassing());

            currentStudent.setHomeUniversity(student.getHomeUniversity());
            currentStudent.setCandidateType(student.getCandidateType());
            currentStudent.setMaharashtraType(student.getMaharashtraType());
            currentStudent.setDomicileStatus(student.getDomicileStatus());

            currentStudent.setValidityCertificate(student.getValidityCertificate());
            currentStudent.setNcl(student.getNcl());
            currentStudent.setIncome(student.getIncome());
            currentStudent.setOrphan(student.getOrphan());

            currentStudent.getUploadedDocumentUrls().clear();

            if (student.getUploadedDocumentUrls() != null) {
                currentStudent.getUploadedDocumentUrls().putAll(
                        student.getUploadedDocumentUrls()
                );
            }

            System.out.println(
                    "Login successful: "
                            + currentStudent.getEmail()
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Student login failed."
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET CURRENT STUDENT PROFILE
    // =========================================================

    public Student getStudentProfile(
            String email
    ) {

        try {

            if (
                    email == null
                    ||
                    email.isBlank()
            ) {

                return null;
            }

            email =
                    email.trim()
                            .toLowerCase();

            DocumentSnapshot document =
                    db.collection("Students")
                            .document(email)
                            .get()
                            .get();

            if (
                    !document.exists()
            ) {

                return null;
            }

            return document.toObject(
                    Student.class
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to load student profile."
            );

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // CHANGE PASSWORD
    // =========================================================

    public boolean changePassword(
            String email,
            String currentPassword,
            String newPassword
    ) {

        try {

            if (
                    email == null
                    ||
                    email.isBlank()
                    ||
                    currentPassword == null
                    ||
                    currentPassword.isBlank()
                    ||
                    newPassword == null
                    ||
                    newPassword.isBlank()
            ) {

                return false;
            }

            email =
                    email.trim()
                            .toLowerCase();

            DocumentSnapshot document =
                    db.collection("Students")
                            .document(email)
                            .get()
                            .get();

            if (
                    !document.exists()
            ) {

                return false;
            }

            Student student =
                    document.toObject(
                            Student.class
                    );

            if (
                    student == null
                    ||
                    student.getPasswordHash()
                            == null
            ) {

                return false;
            }

            String currentPasswordHash =
                    hashPassword(
                            currentPassword
                    );

            boolean currentPasswordCorrect =
                    MessageDigest.isEqual(
                            student
                                    .getPasswordHash()
                                    .getBytes(
                                            StandardCharsets.UTF_8
                                    ),
                            currentPasswordHash
                                    .getBytes(
                                            StandardCharsets.UTF_8
                                    )
                    );

            if (
                    !currentPasswordCorrect
            ) {

                System.out.println(
                        "Current password is incorrect."
                );

                return false;
            }

            String newPasswordHash =
                    hashPassword(
                            newPassword
                    );

            db.collection("Students")
                    .document(email)
                    .update(
                            "passwordHash",
                            newPasswordHash
                    )
                    .get();

            Student.getInstance()
                    .setPasswordHash(
                            newPasswordHash
                    );

            System.out.println(
                    "Password changed successfully."
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Failed to change password."
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // VERIFY PASSWORD
    // =========================================================

    public boolean verifyPassword(
            String email,
            String password
    ) {

        try {

            if (
                    email == null
                    ||
                    password == null
            ) {

                return false;
            }

            DocumentSnapshot document =
                    db.collection("Students")
                            .document(
                                    email.trim()
                                            .toLowerCase()
                            )
                            .get()
                            .get();

            if (
                    !document.exists()
            ) {

                return false;
            }

            Student student =
                    document.toObject(
                            Student.class
                    );

            if (
                    student == null
                    ||
                    student.getPasswordHash()
                            == null
            ) {

                return false;
            }

            String enteredHash =
                    hashPassword(
                            password
                    );

            return MessageDigest.isEqual(
                    student
                            .getPasswordHash()
                            .getBytes(
                                    StandardCharsets.UTF_8
                            ),
                    enteredHash.getBytes(
                            StandardCharsets.UTF_8
                    )
            );

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // PASSWORD HASH
    // =========================================================

    private String hashPassword(
            String password
    ) throws Exception {

        MessageDigest digest =
                MessageDigest.getInstance(
                        "SHA-256"
                );

        byte[] hash =
                digest.digest(
                        password.getBytes(
                                StandardCharsets.UTF_8
                        )
                );

        return Base64
                .getEncoder()
                .encodeToString(
                        hash
                );
    }
}