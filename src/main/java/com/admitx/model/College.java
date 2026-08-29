package com.admitx.model;

public class College {

    private String collegeID;
    private String collegeName;
    private String district;
    private String university;
    private String branch;
    private int intake;

    // Required by Firestore
    public College() {
    }

    public College(
            String collegeID,
            String collegeName,
            String district,
            String university,
            String branch,
            int intake) {

        this.collegeID = collegeID;
        this.collegeName = collegeName;
        this.district = district;
        this.university = university;
        this.branch = branch;
        this.intake = intake;
    }

    // College ID
    public String getCollegeID() {
        return collegeID;
    }

    public void setCollegeID(String collegeID) {
        this.collegeID = collegeID;
    }

    // College Name
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    // District
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    // University
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    // Branch
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    // Intake
    public int getIntake() {
        return intake;
    }

    public void setIntake(int intake) {
        this.intake = intake;
    }

    @Override
    public String toString() {
        return collegeID + " - " + collegeName + " - " + branch;
    }
}