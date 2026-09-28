package com.admitx.model;

public class CAPAllotment {

    private String studentEmail;

    private int round;

    private String college;
    private String branch;

    private String previousCollege;
    private String previousBranch;

    private int preferenceNumber;

    private String status;
    private String upgradeStatus;

    private String decision;

    private boolean published;

    public CAPAllotment() {
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPreviousCollege() {
        return previousCollege;
    }

    public void setPreviousCollege(String previousCollege) {
        this.previousCollege = previousCollege;
    }

    public String getPreviousBranch() {
        return previousBranch;
    }

    public void setPreviousBranch(String previousBranch) {
        this.previousBranch = previousBranch;
    }

    public int getPreferenceNumber() {
        return preferenceNumber;
    }

    public void setPreferenceNumber(int preferenceNumber) {
        this.preferenceNumber = preferenceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpgradeStatus() {
        return upgradeStatus;
    }

    public void setUpgradeStatus(String upgradeStatus) {
        this.upgradeStatus = upgradeStatus;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}