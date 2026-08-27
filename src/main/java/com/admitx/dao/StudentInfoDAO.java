package com.admitx.dao;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.Student;
import com.admitx.view.StudentRegistrationPage;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;

public class StudentInfoDAO {

    private Firestore db = new FirebaseConfig().getFirestore();

    // -------------------------------------------------
    // SAVE PERSONAL INFORMATION
    // -------------------------------------------------

    public void saveStudentInfo(Student student) {

        System.out.println(StudentRegistrationPage.studentemail);

        try {

            db.collection("Students")
                    .document(student.getEmail())
                    .update(
                            "candidateName", student.getCandidateName(),
                            "fatherName", student.getFatherName(),
                            "motherName", student.getMotherName(),
                            "gender", student.getGender(),
                            "dob", student.getDob(),
                            "nationality", student.getNationality(),
                            "aadhaar", student.getAadhaar(),
                            "category", student.getCategory(),
                            "religion", student.getReligion(),
                            "caste", student.getCaste(),
                            "minority", student.getMinority(),
                            "pwd", student.getPwd(),
                            "defence", student.getDefence(),
                            "tfws", student.getTfws(),
                            "ews", student.getEws()
                    )
                    .get();

            System.out.println("Student personal information updated successfully!");

        } catch (Exception e) {

            System.out.println("Failed to update student personal information!");
            e.printStackTrace();
        }
    }


    // -------------------------------------------------
    // SAVE REGISTRATION DETAILS
    // -------------------------------------------------

    public void registrationDetails(Student registeredStudent) {

        try {

            db.collection("Students")
                    .document(registeredStudent.getEmail())
                    .set(registeredStudent, SetOptions.merge())
                    .get();

            System.out.println("Student registration info is saved!");

        } catch (Exception e) {

            System.out.println("Failed to save student registration info!");
            e.printStackTrace();
        }
    }


    // -------------------------------------------------
    // SAVE ADDRESS DETAILS
    // -------------------------------------------------

    public void saveStudentAddress(Student addressStudent) {

        try {

            db.collection("Students")
                    .document(addressStudent.getEmail())
                    .set(addressStudent, SetOptions.merge())
                    .get();

            System.out.println("Student Address details are saved!");

        } catch (Exception e) {

            System.out.println("Failed to save student Address details!");
            e.printStackTrace();
        }
    }
}