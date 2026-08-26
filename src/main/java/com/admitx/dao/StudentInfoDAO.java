package com.admitx.dao;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.Student;
import com.google.cloud.firestore.Firestore;

public class StudentInfoDAO {

    private Firestore db = new FirebaseConfig().getFirestore();

    public void saveStudentInfo(Student student){
        
        // try{
        //     db.collection("Students")
        //                     .document(clg.getCollegeID())
        //                     .create(clg);
        //     System.out.println("Colleges added by counsellor");
        // }catch(Exception e){
        //         e.printStackTrace();
        // }

    }
    
}
