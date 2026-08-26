package com.admitx.controller;

import java.util.List;

import com.admitx.dao.CollegeDAO;
import com.admitx.model.College;

public class CollegeAddController {

    CollegeDAO dao = new CollegeDAO();

    public void addcollege (String clgid,String collegename,String district,String university, String branch,int intake){
        
        College clg = new College(
            clgid,
            collegename,
            district,
            university,
            branch,
            intake
        );

       
        dao.saveCollegeInfo(clg);
        
    }

    public List<College> getAllColleges() {

        return dao.getAllColleges();

    }

    public void deleteCollege(String collegeID) {
        dao.deleteCollege(collegeID);
    }

    public void updateCollege(String oldCollegeID, College updatedCollege) {
        dao.updateCollege( oldCollegeID, updatedCollege);
    }
    
}

 /*private int collegeID;
    private String collegeName;
    private String district;
    private String university;
    private String branch;
    private int intake;
    */
