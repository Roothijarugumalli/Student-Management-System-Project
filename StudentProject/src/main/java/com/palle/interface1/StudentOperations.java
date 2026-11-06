package com.palle.interface1;

import java.util.List;

import com.palle.Student;
import com.palle.exception.DuplicateStudentException;
import com.palle.exception.InvalidStudentDataException;
import com.palle.exception.StudentNotFoundException;


public interface StudentOperations {

    void addStudent(Student student)
            throws DuplicateStudentException,
                   InvalidStudentDataException;

    List<Student> getAllStudents();

    Student searchStudent(int id)
            throws StudentNotFoundException;

    void updateStudent(int id, Student student)
            throws StudentNotFoundException,
                   InvalidStudentDataException;

    void deleteStudent(int id)
            throws StudentNotFoundException;
}
