package com.admitx.model;

public class College {

private String collegeID;
private String collegeName;
private String district;
private String university;
private String branch;

private int intake;
private int seatsAvailable;

// Minimum percentile required for this college
private double cutoff;


// Required by Firestore
public College() {
}


public College(
        String collegeID,
        String collegeName,
        String district,
        String university,
        String branch,
        int intake,
        double cutoff) {

    this.collegeID = collegeID;
    this.collegeName = collegeName;
    this.district = district;
    this.university = university;
    this.branch = branch;
    this.intake = intake;
    this.cutoff = cutoff;

    // Initially all seats are available
    this.seatsAvailable = intake;
}


// ================================
// COLLEGE ID
// ================================

public String getCollegeID() {
    return collegeID;
}

public void setCollegeID(String collegeID) {
    this.collegeID = collegeID;
}


// ================================
// COLLEGE NAME
// ================================

public String getCollegeName() {
    return collegeName;
}

public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
}


// ================================
// DISTRICT
// ================================

public String getDistrict() {
    return district;
}

public void setDistrict(String district) {
    this.district = district;
}


// ================================
// UNIVERSITY
// ================================

public String getUniversity() {
    return university;
}

public void setUniversity(String university) {
    this.university = university;
}


// ================================
// BRANCH
// ================================

public String getBranch() {
    return branch;
}

public void setBranch(String branch) {
    this.branch = branch;
}


// ================================
// TOTAL INTAKE
// ================================

public int getIntake() {
    return intake;
}

public void setIntake(int intake) {
    this.intake = intake;
}


// ================================
// AVAILABLE SEATS
// ================================

public int getSeatsAvailable() {
    return seatsAvailable;
}

public void setSeatsAvailable(int seatsAvailable) {
    this.seatsAvailable = seatsAvailable;
}


// ================================
// COLLEGE CUTOFF
// ================================

public double getCutoff() {
    return cutoff;
}

public void setCutoff(double cutoff) {
    this.cutoff = cutoff;
}


@Override
public String toString() {
    return collegeID
            + " - "
            + collegeName
            + " - "
            + branch
            + " - Cutoff: "
            + cutoff;
}


}
