package com.admitx.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Student {

    private static final Student instance = new Student();

    private String username;
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    private String email;
    private String mobileno;


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileno() {
        return mobileno;
    }

    private String candidateName;
    private String fatherName;
    private String motherName;
    private String gender;
    private String dob;
    private String nationality;
    private String aadhaar;
    private String category;
    private String religion;
    private String caste;
    private String minority;
    private String pwd;
    private String defence;
    private String tfws;
    private String ews;

    private String permanentAddress;
    private String correspondenceAddress;
    private String state;
    private String district;
    private String taluka;
    private String pinCode;

    private String sscDetails;
    private String hscDetails;
    private String diplomaDetails;
    private String pcmMarks;
    private String cetPercentile;
    private String jeePercentile;
    private String yearOfPassing;

    private String homeUniversity;
    private String candidateType;
    private String maharashtraType;
    private String domicileStatus;

    private String validityCertificate;
    private String ncl;
    private String income;
    private String orphan;

    private final Map<String, String> uploadedDocumentUrls = new HashMap<>();

    private final Map<String, File> uploadedDocuments = new HashMap<>();

    public Student() {
    }

    public Student(
        String candidateName,
        String fatherName,
        String motherName,
        String gender,
        String dob,
        String  nationality,
        String aadhaar,
        String category ,
        String religion,
        String  caste,
        String minority,
        String pwd,
        String defence,
        String tfws,
        String ews

    ){
        this.candidateName = candidateName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.gender = gender;
        this.dob = dob;
        this.nationality = nationality;
        this.aadhaar = aadhaar;
        this.category = category;
        this.religion = religion;
        this.caste = caste;
        this.minority = minority;
        this.pwd = pwd;
        this.defence = defence;
        this.tfws = tfws;
        this.ews = ews;

    }

    public Student(String name,String email,String mobileno){
        
        this.username = name;
        this.email = email;
        this.mobileno = mobileno;
        

    }

    public static Student getInstance() {
        return instance;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getMinority() {
        return minority;
    }

    public void setMinority(String minority) {
        this.minority = minority;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDefence() {
        return defence;
    }

    public void setDefence(String defence) {
        this.defence = defence;
    }

    public String getTfws() {
        return tfws;
    }

    public void setTfws(String tfws) {
        this.tfws = tfws;
    }

    public String getEws() {
        return ews;
    }

    public void setEws(String ews) {
        this.ews = ews;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getSscDetails() {
        return sscDetails;
    }

    public void setSscDetails(String sscDetails) {
        this.sscDetails = sscDetails;
    }

    public String getHscDetails() {
        return hscDetails;
    }

    public void setHscDetails(String hscDetails) {
        this.hscDetails = hscDetails;
    }

    public String getDiplomaDetails() {
        return diplomaDetails;
    }

    public void setDiplomaDetails(String diplomaDetails) {
        this.diplomaDetails = diplomaDetails;
    }

    public String getPcmMarks() {
        return pcmMarks;
    }

    public void setPcmMarks(String pcmMarks) {
        this.pcmMarks = pcmMarks;
    }

    public String getCetPercentile() {
        return cetPercentile;
    }

    public void setCetPercentile(String cetPercentile) {
        this.cetPercentile = cetPercentile;
    }

    public String getJeePercentile() {
        return jeePercentile;
    }

    public void setJeePercentile(String jeePercentile) {
        this.jeePercentile = jeePercentile;
    }

    public String getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public String getHomeUniversity() {
        return homeUniversity;
    }

    public void setHomeUniversity(String homeUniversity) {
        this.homeUniversity = homeUniversity;
    }

    public String getCandidateType() {
        return candidateType;
    }

    public void setCandidateType(String candidateType) {
        this.candidateType = candidateType;
    }

    public String getMaharashtraType() {
        return maharashtraType;
    }

    public void setMaharashtraType(String maharashtraType) {
        this.maharashtraType = maharashtraType;
    }

    public String getDomicileStatus() {
        return domicileStatus;
    }

    public void setDomicileStatus(String domicileStatus) {
        this.domicileStatus = domicileStatus;
    }

    public String getValidityCertificate() {
        return validityCertificate;
    }

    public void setValidityCertificate(String validityCertificate) {
        this.validityCertificate = validityCertificate;
    }

    public String getNcl() {
        return ncl;
    }

    public void setNcl(String ncl) {
        this.ncl = ncl;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getOrphan() {
        return orphan;
    }

    public void setOrphan(String orphan) {
        this.orphan = orphan;
    }

    public Map<String, File> getUploadedDocuments() {
        return uploadedDocuments;
    }

    public Map<String, String> getUploadedDocumentUrls() {
        return uploadedDocumentUrls;
    }
}