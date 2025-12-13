package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        String deptId = (department != null) ? department.getDepartmentId() : "D00";
        this.courseId = String.format("C-%s-%02d", deptId, nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
    }

    /**
     * Checks if the sum of assignment weights equals 100
     * @return true if weights sum to 100, false if not
     */
    public boolean isAssignmentWeightValid() {
        double sum = 0;
        for (Assignment assignment : assignments) {
            sum += assignment.getWeight();
        }
        return Math.abs(sum - 100.0) < 0.001;
    }

    /**
     * Registers a student for the course
     * @param student the student to register
     * @return true if successfully registered
     */
    public boolean registerStudent(Student student) {
        if (!registeredStudents.contains(student)) {
            registeredStudents.add(student);

            for (Assignment assignment : assignments) {
                assignment.getScores().add(null);
            }
        }
        return true;
    }

    /**
     * Calculates the weighted average score for each student
     * @return array of the final scores for all students
     */
    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];

        for (int i = 0; i < registeredStudents.size(); i++) {
            double weightedSum = 0;

            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                if (score != null) {
                    weightedSum += score * (assignment.getWeight() / 100.0);
                }
            }

            averages[i] = (int) Math.round(weightedSum);
        }

        return averages;
    }

    /**
     * Adds a new assignment to the course
     * @param assignmentName the name of the assignment
     * @param weight the weight of the assignment
     * @param maxScore the maximum score
     * @return true if successfully added
     */
    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        Assignment assignment = new Assignment(assignmentName, weight);

        for (int i = 0; i < registeredStudents.size(); i++){
            assignment.getScores().add(null);
        }

        assignments.add(assignment);
        return true;
    }

    public void generateScores() {
        for (Assignment assignment : assignments) {
            assignment.generateRandomScore();
        }
    }

    public void displayScores() {
        System.out.printf("Course: %s (%s)\n", courseName, courseId);

        System.out.printf("%20s", "");
        for (Assignment assignment : assignments) {
            System.out.printf("%15s", assignment.getAssignmentName());
        }
        System.out.printf("%15s\n", "Final Score");

        int[] finalScores = calcStudentsAverage();
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            System.out.printf("%20s", student.getStudentName());

            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                if (score != null) {
                    System.out.printf("%15d", score);
                } else {
                    System.out.printf("%15s", "N/A");
                }
            }

            System.out.printf("%15d\n", finalScores[i]);
        }

        System.out.printf("%20s", "Average");
        for (Assignment assignment : assignments) {
            System.out.printf("%15.0f", assignment.calcAssignmentAvg());
        }
        System.out.println();
    }

    public String toSimplifiedString() {
        String deptName = (department != null) ? department.getDepartmentName() : "N/A";
        return String.format("%s - %s (%.1f credits, %s)", courseId, courseName, credits, deptName);
    }

    public String toString() {
        String result = "";
        result += String.format("Course ID: %s\n", courseId);
        result += String.format("Course Name: %s\n", courseName);
        result += String.format("Credits: %.1f\n", credits);
        result += String.format("Department: %s\n", department != null ? department.getDepartmentName() : "N/A");
        result += "Assignments:\n";

        for (Assignment assignment : assignments) {
            result += String.format("  - %s\n", assignment.toString());
        }

        result += "Registered Students:\n";
        for (Student student : registeredStudents) {
            result += String.format("  - %s\n", student.toSimplifiedString());
        }

        result += String.format("Assignment weights valid: %s\n", isAssignmentWeightValid());

        return result;
    }

    public void setCourseName(String courseName) {
        this.courseName = Util.toTitleCase(courseName);
    }
}
