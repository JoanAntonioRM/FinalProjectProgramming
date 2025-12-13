package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;
    private static int nextId = 1;

    public Student(String studentId, String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    /**
     * Registers a course for the student
     * @param course the course to register
     * @return true if successfully registered, false if already registered
     */
    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.add(course);
        course.registerStudent(this);

        for (Assignment assignment : course.getAssignments()) {
            assignment.getScores().add(null);
        }

        return true;
    }

    /**
     * Drops a course for the student
     * @param course the course to drop
     * @return true if successfully dropped, false if not registered
     */
    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false;
        }

        int studentIndex = course.getRegisteredStudents().indexOf(this);
        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);

        for (Assignment assignment : course.getAssignments()) {
            if (studentIndex >= 0 && studentIndex < assignment.getScores().size()) {
                assignment.getScores().remove(studentIndex);
            }
        }

        return true;
    }

    public String toSimplifiedString() {
        String deptName = (department != null) ? department.getDepartmentName() : "N/A";
        return String.format("%s - %s (%s)", studentId, studentName, deptName);
    }

    @Override
    public String toString() {
        String result = "";
        result += String.format("Student ID: %s\n", studentId);
        result += String.format("Name: %s\n", studentName);
        result += String.format("Gender: %s\n", gender);
        result += String.format("Address: %s\n", address);
        result += String.format("Department: %s\n", department != null ? department.getDepartmentName() : "N/A");
        result += "Registered Courses:\n";

        for (Course course : registeredCourses) {
            result += String.format("  - %s\n", course.toSimplifiedString());
        }

        return result;
    }

    public void setStudentName(String studentName) {
        this.studentName = Util.toTitleCase(studentName);
    }
}
