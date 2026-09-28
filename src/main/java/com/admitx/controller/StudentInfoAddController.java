package com.admitx.controller;

import com.admitx.dao.StudentInfoDAO;
import com.admitx.model.Student;

public class StudentInfoAddController {

    private final StudentInfoDAO studentInfoDAO =
            new StudentInfoDAO();

    public boolean addStudentInfo(
            String name,
            String fname,
            String mname,
            String gender,
            String dbirth,
            String nation,
            String adhar,
            String category,
            String religion,
            String caste,
            String minority,
            String pwd,
            String defence,
            String tfws,
            String ews
    ) {

        Student student =
                new Student(
                        name,
                        fname,
                        mname,
                        gender,
                        dbirth,
                        nation,
                        adhar,
                        category,
                        religion,
                        caste,
                        minority,
                        pwd,
                        defence,
                        tfws,
                        ews
                );

        student.setEmail(
                Student.getInstance()
                        .getEmail()
        );

        boolean saved = studentInfoDAO
                .saveStudentInfo(
                        student
                );

        if (saved) {
            Student current = Student.getInstance();
            current.setCandidateName(name);
            current.setFatherName(fname);
            current.setMotherName(mname);
            current.setGender(gender);
            current.setDob(dbirth);
            current.setNationality(nation);
            current.setAadhaar(adhar);
            current.setCategory(category);
            current.setReligion(religion);
            current.setCaste(caste);
            current.setMinority(minority);
            current.setPwd(pwd);
            current.setDefence(defence);
            current.setTfws(tfws);
            current.setEws(ews);
        }

        return saved;
    }

    public boolean saveApplicationSection() {
        return studentInfoDAO.saveApplicationSection(
                Student.getInstance()
        );
    }

    public boolean registrationDetails(
            String name,
            String email,
            String mobileno,
            String password
    ) {

        Student regStudent =
                new Student(
                        name,
                        email,
                        mobileno
                );

        return studentInfoDAO
                .registrationDetails(
                        regStudent,
                        password
                );
    }

    public boolean loginStudent(
            String email,
            String password
    ) {

        return studentInfoDAO
                .loginStudent(
                        email,
                        password
                );
    }

    public Student getStudentProfile(
            String email
    ) {

        return studentInfoDAO
                .getStudentProfile(
                        email
                );
    }

    public void saveSignUpInfo(String username,String email,String mobileno){
        Student s = new Student(username,email,mobileno);

        studentInfoDAO.saveSignUpInfo(s); 
        
    }

    public boolean changePassword(
            String email,
            String currentPassword,
            String newPassword
    ) {

        return studentInfoDAO
                .changePassword(
                        email,
                        currentPassword,
                        newPassword
                );
    }
}