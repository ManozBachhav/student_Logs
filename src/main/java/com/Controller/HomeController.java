package com.Controller;

import com.Service.StudentServiceIMPL;
import com.Service.StudentServices;

public class HomeController {
    public static  void main(String[] args){

        StudentServices sas=new StudentServiceIMPL();

        sas.getAllStudents();
//        sas.addStudentWithSubjects();
//        sas.deleteSpecificSubjectFromSpecificStudentUsingSID();
//        sas.getStudentOnly();
//        sas.getSubjectsOnly();
//        sas.deleteStudentOnlyUsingSID();
//        sas.updateSpecificSubjectUsingStudentID();
//        sas.updateStudentDetailsUsingSID();

    }
}
