package com.palle.controller;



import java.util.List;
import java.util.Scanner;

import com.palle.Student;
import com.palle.exception.DuplicateStudentException;
import com.palle.exception.InvalidStudentDataException;
import com.palle.exception.StudentNotFoundException;
import com.palle.service.StudentService;


public class StudentController {

    private Scanner sc;
    private StudentService service;

    public StudentController() {
        sc = new Scanner(System.in);
        service = new StudentService();
    }

    // Main menu
    public void start() {

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        displayStudents();
                        break;

                    case 3:
                        searchStudent();
                        break;

                    case 4:
                        updateStudent();
                        break;

                    case 5:
                        deleteStudent();
                        break;

                    case 6:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid menu choice!");
                }

            } catch (DuplicateStudentException |
                     StudentNotFoundException |
                     InvalidStudentDataException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (IllegalArgumentException e) {

                System.out.println("Invalid input: " + e.getMessage());
            }

        } while (choice != 6);
    }

    // Read integer safely
    private int readInt(String message) {

        System.out.print(message);

        String input = sc.nextLine();

        try {
            return Integer.parseInt(input.trim());
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Please enter a valid integer."
            );
        }
    }

    // Read decimal safely
    private double readDouble(String message) {

        System.out.print(message);

        String input = sc.nextLine();

        try {
            return Double.parseDouble(input.trim());
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Please enter a valid number."
            );
        }
    }

    // 1. Add Student
    private void addStudent()
            throws DuplicateStudentException,
                   InvalidStudentDataException {

        System.out.println("\n--- ADD STUDENT ---");

        int id = readInt("Enter Student ID: ");

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int age = readInt("Enter Age: ");

        double marks = readDouble("Enter Marks: ");

        Student student = new Student(
            id, name, age, marks
        );

        service.addStudent(student);
    }

    // 2. Display All Students
    private void displayStudents() {

        System.out.println("\n--- ALL STUDENTS ---");

        List<Student> students = service.getAllStudents();

        if (students.isEmpty()) {

            System.out.println("No student records available.");
            return;
        }

        for (Student student : students) {

            System.out.println("-----------------------");
            System.out.println(student);
        }
    }

    // 3. Search Student
    private void searchStudent()
            throws StudentNotFoundException {

        System.out.println("\n--- SEARCH STUDENT ---");

        int id = readInt("Enter Student ID: ");

        Student student = service.searchStudent(id);

        System.out.println("Student Found!");
        System.out.println(student);
    }

    // 4. Update Student
    private void updateStudent()
            throws StudentNotFoundException,
                   InvalidStudentDataException {

        System.out.println("\n--- UPDATE STUDENT ---");

        int id = readInt("Enter Student ID to update: ");

        // Check whether student exists
        service.searchStudent(id);

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        int age = readInt("Enter New Age: ");

        double marks = readDouble("Enter New Marks: ");

        Student student = new Student(
            id, name, age, marks
        );

        service.updateStudent(id, student);
    }

    // 5. Delete Student
    private void deleteStudent()
            throws StudentNotFoundException {

        System.out.println("\n--- DELETE STUDENT ---");

        int id = readInt("Enter Student ID to delete: ");

        service.deleteStudent(id);
    }
}

