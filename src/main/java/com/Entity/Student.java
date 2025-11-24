package com.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String sname;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Subject>listsub=new ArrayList<Subject>();

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public List<Subject> getListsub() {
        return listsub;
    }

    public void setListsub(List<Subject> listsub) {
        this.listsub = listsub;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", listsub=" + listsub +
                '}';
    }
}
