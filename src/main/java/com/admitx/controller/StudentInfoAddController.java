package com.admitx.controller;

import com.admitx.dao.StudentInfoDAO;
import com.admitx.model.Student;

public class StudentInfoAddController {

    StudentInfoDAO studentInfoDAO = new StudentInfoDAO();

    public void addStudentInfo(
        String name, 
        String fname,
        String mname,
        String gende,
        String dbirth,
        String nation,
        String adhar,
        String cate,
        String reli,
        String cast,
        String minor,
        String pwdd,
        String defen,
        String tf,
        String ew
    ){
        Student student = new Student(
            name, 
            fname,
            mname,
            gende,
            dbirth,
            nation,
            adhar,
            cate,
            reli,
            cast,
            minor,
            pwdd,
            defen,
            tf,
            ew
        );

        studentInfoDAO.saveStudentInfo(student);

    }

    public void registrationDetails(String name, String email,String mobileno){

        Student regStudent = new Student(name,email,mobileno);

        studentInfoDAO.registrationDetails(regStudent);

    }

    
}

