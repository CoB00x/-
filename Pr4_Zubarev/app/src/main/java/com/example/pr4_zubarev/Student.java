package com.example.pr4_zubarev;

import android.widget.EditText;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Student implements Serializable {
    private String Name;
    private String Surname;
    private String Subject;

    private Time time;

    private Date date;

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getName() {
        return Name;
    }

    public String getSubject() {
        return Subject;
    }

    public String getSurname() {
        return Surname;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
