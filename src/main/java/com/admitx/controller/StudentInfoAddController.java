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

    public void addStudentAddress(
        String paddress,
        String pstate,
        String ppincode,
        String pdistrict,
        String ptaluka,
        String caddress,
        String cstate,
        String cpincode,
        String cdistrict,
        String ctaluka
    ){
        Student student = new Student(
                        paddress,
                        pstate,
                        pdistrict,
                        ppincode,
                        ptaluka,
                        caddress,
                        cdistrict,
                        cpincode,
                        cstate,
                        ctaluka
        );

        studentInfoDAO.saveStudentAddress(student);

        

    }

    
}

