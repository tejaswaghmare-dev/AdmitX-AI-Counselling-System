package com.admitx.dao;

import java.util.ArrayList;
import java.util.List;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.College;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class CollegeDAO {

    private Firestore db = new FirebaseConfig().getFirestore();

    public void saveCollegeInfo(College clg){

        try{
            db.collection("Colleges")
                            .document(clg.getCollegeID())
                            .create(clg);
            System.out.println("Colleges added by counsellor");
        }catch(Exception e){
                e.printStackTrace();
        }
         
    }

    public List<College> getAllColleges(){

        List<College> colleges = new ArrayList<>();
        
         try {
            ApiFuture<QuerySnapshot> future = db.collection("Colleges").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot document : snapshot.getDocuments()) {
                if (document.exists()) {
                    College college = document.toObject(College.class);
                    colleges.add(college);
                 }
            }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return colleges;

    }

    public void deleteCollege(String collegeID) {

        try {

            db.collection("Colleges")
            .document(collegeID)
            .delete()
            .get();

            System.out.println(
                    "College deleted successfully by admin "
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void updateCollege(String oldCollegeID, College updatedCollege) {

        try {

            db.collection("Colleges")
                    .document(oldCollegeID)
                    .set(updatedCollege)
                    .get();

            System.out.println(
                    "College updated in Firestore"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    
    
}
