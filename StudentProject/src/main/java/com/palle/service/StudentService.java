package com.palle.service;

import java.util.ArrayList;
import java.util.List;

import com.palle.Student;
import com.palle.exception.DuplicateStudentException;
import com.palle.exception.InvalidStudentDataException;
import com.palle.exception.StudentNotFoundException;
import com.palle.interface1.StudentOperations;


public class StudentService
        extends GenericService<Student>
        implements StudentOperations {

    public StudentService() {
        super();
    }

    // Validate student details
    private void validateStudent(Student student)
            throws InvalidStudentDataException {

        if (student == null) {
            throw new InvalidStudentDataException(
                "Student details cannot be null"
            );
        }

        if (student.getId() <= 0) {
            throw new InvalidStudentDataException(
                "Student ID must be greater than zero"
            );
        }

        if (student.getName() == null ||
            student.getName().trim().isEmpty()) {

            throw new InvalidStudentDataException(
                "Student name cannot be empty"
            );
        }

        if (student.getAge() < 1 ||
            student.getAge() > 100) {

            throw new InvalidStudentDataException(
                "Age must be between 1 and 100"
            );
        }

        if (student.getMarks() < 0 ||
            student.getMarks() > 100) {

            throw new InvalidStudentDataException(
                "Marks must be between 0 and 100"
            );
        }
    }

    // 1. Add Student
    @Override
    public void addStudent(Student student)
            throws DuplicateStudentException,
                   InvalidStudentDataException {

        validateStudent(student);

        if (exists(student.getId())) {

            throw new DuplicateStudentException(
                "Student ID already exists: " + student.getId()
            );
        }

        save(student.getId(), student);

        System.out.println("Student added successfully!");
    }

    // 2. Display All Students
    @Override
    public List<Student> getAllStudents() {

        return new ArrayList<>(findAll().values());
    }

    // 3. Search Student
    @Override
    public Student searchStudent(int id)
            throws StudentNotFoundException {

        Student student = findById(id);

        if (student == null) {

            throw new StudentNotFoundException(
                "Student not found with ID: " + id
            );
        }

        return student;
    }

    // 4. Update Student
    @Override
    public void updateStudent(int id, Student student)
            throws StudentNotFoundException,
                   InvalidStudentDataException {

        if (!exists(id)) {

            throw new StudentNotFoundException(
                "Cannot update. Student not found: " + id
            );
        }

        if (student == null) {
            throw new InvalidStudentDataException(
                "Student details cannot be null"
            );
        }

        // The ID should not change during update
        student.setId(id);

        validateStudent(student);

        save(id, student);

        System.out.println("Student updated successfully!");
    }

    // 5. Delete Student
    @Override
    public void deleteStudent(int id)
            throws StudentNotFoundException {

        if (!exists(id)) {

            throw new StudentNotFoundException(
                "Cannot delete. Student not found: " + id
            );
        }

        remove(id);

        System.out.println("Student deleted successfully!");
    }
}
