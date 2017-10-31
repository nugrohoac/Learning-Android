package com.example.nac017.crud_sqlite.Model;

/**
 * Created by nac017 on 28/10/17.
 */

public class StudentModel {
    private Integer id, marks;
    private String name, surname;

    public StudentModel() {
        this.id = id;
        this.marks = marks;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
