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
            new FirebaseConfig().getFirestore();

    // =========================================================
    // PERSONAL DETAILS
    // =========================================================

    public void saveStudentInfo(
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

                return;
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

        } catch (Exception e) {

            System.out.println(
                    "Failed to update student personal information."
            );

            e.printStackTrace();
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