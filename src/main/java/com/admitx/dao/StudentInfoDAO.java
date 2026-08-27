package com.admitx.dao;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.Student;
import com.admitx.view.StudentRegistrationPage;
import com.google.cloud.firestore.Firestore;

public class StudentInfoDAO {

    private Firestore db = new FirebaseConfig().getFirestore();

    public void saveStudentInfo(Student student){
        
        try{
            db.collection("Students")
                            .document(StudentRegistrationPage.studentemail)
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
        }catch(Exception e){
                e.printStackTrace();
        }

    }

    public void registrationDetails(Student registeredStudent) {

    try {

            db.collection("Students")
            .document(registeredStudent.getEmail())
            .create(registeredStudent)
            .get();

            System.out.println("Student registration info is saved!");

        } catch (Exception e) {

            System.out.println("Failed to save student registration info!");
            e.printStackTrace();
        }
    }
    
}
