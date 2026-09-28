
package com.admitx.dao;

import java.util.ArrayList;
import java.util.List;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.College;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;

public class CollegeDAO {

    private Firestore db =
            FirebaseConfig.getFirestore();


    // =========================================================
    // SAVE COLLEGE
    // =========================================================

    public void saveCollegeInfo(College clg) {

        try {

            // When a new college is created,
            // all seats are initially available.

            clg.setSeatsAvailable(
                    clg.getIntake()
            );


            db.collection("Colleges")
                    .document(clg.getCollegeID())
                    .create(clg)
                    .get();


            System.out.println(
                    "College added by counsellor"
            );


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // GET ALL COLLEGES
    // =========================================================

    public List<College> getAllColleges() {

        List<College> colleges =
                new ArrayList<>();


        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("Colleges")
                            .get();


            QuerySnapshot snapshot =
                    future.get();


            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                if (document.exists()) {

                    College college = document.toObject(College.class );

                    colleges.add(college);
                }
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return colleges;
    }


    // =========================================================
    // DELETE COLLEGE
    // =========================================================

    public void deleteCollege(String collegeID) {

        try {

            db.collection("Colleges")
                    .document(collegeID)
                    .delete()
                    .get();


            System.out.println(
                    "College deleted successfully by admin"
            );


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // UPDATE COLLEGE
    // =========================================================

    public void updateCollege(
            String oldCollegeID,
            College updatedCollege) {

        try {

            /*
             * Do NOT reset seatsAvailable here.
             *
             * Example:
             *
             * Intake = 120
             * Available = 87
             *
             * If admin changes only the district,
             * available seats must remain 87.
             */

            db.collection("Colleges")
                    .document(oldCollegeID)
                    .set(
                            updatedCollege,
                            SetOptions.merge()
                    )
                    .get();


            System.out.println(
                    "College updated in Firestore"
            );


        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

