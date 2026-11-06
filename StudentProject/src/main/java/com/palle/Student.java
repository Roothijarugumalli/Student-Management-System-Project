package com.palle;

public class Student {

    private int id;
    private String name;
    private int age;
    private double marks;

    // Default constructor
    public Student() {
        super();
    }

    // Parameterized constructor
    public Student(int id, String name, int age, double marks) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Grade calculation
    public String calculateGrade() {

        if (marks >= 90) {
            return "A+";
        } 
        else if (marks >= 80) {
            return "A";
        } 
        else if (marks >= 70) {
            return "B";
        } 
        else if (marks >= 60) {
            return "C";
        } 
        else if (marks >= 50) {
            return "D";
        } 
        else {
            return "F";
        }
    }

    // Display student details
    @Override
    public String toString() {

        return "Student ID: " + id +
               "\nName: " + name +
               "\nAge: " + age +
               "\nMarks: " + marks +
               "\nGrade: " + calculateGrade();
    }
}
