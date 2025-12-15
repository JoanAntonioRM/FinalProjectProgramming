import org.example.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    // Address.isPostalCodeValid() Tests

    @Test
    @DisplayName("isPostalCodeValid: Test 1 - valid postal code A1B2C3")
    void testIsPostalCodeValidTest1() {
        String input = "A1B2C3";
        boolean expected = true;
        boolean result = Address.isPostalCodeValid(input);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("isPostalCodeValid: Test 2 - invalid postal code 123ABC")
    void testIsPostalCodeValidTest2() {
        String input = "123ABC";
        boolean expected = false;
        boolean result = Address.isPostalCodeValid(input);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("isPostalCodeValid: Test 3 - invalid postal code wrong length")
    void testIsPostalCodeValidTest3() {
        String input = "A1B2C";
        boolean expected = false;
        boolean result = Address.isPostalCodeValid(input);
        assertEquals(expected, result);
    }

    // Department.isDepartmentNameValid() Tests

    @Test
    @DisplayName("isDepartmentNameValid: Test 1 - valid name with letters only")
    void testIsDepartmentNameValidTest1() {
        String input = "ComputerScience";
        boolean expected = true;
        boolean result = Department.isDepartmentNameValid(input);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("isDepartmentNameValid: Test 2 - valid name with letters and spaces")
    void testIsDepartmentNameValidTest2() {
        String input = "Computer Science";
        boolean expected = true;
        boolean result = Department.isDepartmentNameValid(input);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("isDepartmentNameValid: Test 3 - invalid name with numbers")
    void testIsDepartmentNameValidTest3() {
        String input = "Computer Science 101";
        boolean expected = false;
        boolean result = Department.isDepartmentNameValid(input);
        assertEquals(expected, result);
    }

    // Util.toTitleCase() Tests

    @Test
    @DisplayName("toTitleCase: Test 1 - single word lowercase")
    void testToTitleCaseTest1() {
        String input = "hello";
        String expected = "Hello";
        String result = Util.toTitleCase(input);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("toTitleCase: Test 2 - multiple words")
    void testToTitleCaseTest2() {
        String input = "yi wang";
        String expected = "Yi Wang";
        String result = Util.toTitleCase(input);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("toTitleCase: Test 3 - all uppercase words")
    void testToTitleCaseTest3() {
        String input = "COMPUTER SCIENCE";
        String expected = "Computer Science";
        String result = Util.toTitleCase(input);
        assertEquals(expected, result);
    }

    // Student.registerCourse() Tests

    @Test
    @DisplayName("registerCourse: Test 1 - successfully register a new course")
    void testRegisterCourseTest1() {
        Department department = new Department("Computer Science");
        Address address = new Address(123, "Main St", "Montreal", Province.QC, "H1A2B3");
        Student student = new Student("John Doe", Gender.MALE, address, department);
        Course course = new Course("Programming 1", 3.0, department);

        boolean expected = true;
        boolean result = student.registerCourse(course);
        assertEquals(expected, result);
        assertTrue(student.getRegisteredCourses().contains(course));
    }

    @Test
    @DisplayName("registerCourse: Test 2 - fail to register already registered course")
    void testRegisterCourseTest2() {
        Department department = new Department("Computer Science");
        Address address = new Address(123, "Main St", "Montreal", Province.QC, "H1A2B3");
        Student student = new Student("John Doe", Gender.MALE, address, department);
        Course course = new Course("Programming 1", 3.0, department);

        student.registerCourse(course);
        boolean expected = false;
        boolean result = student.registerCourse(course);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("registerCourse: Test 3 - register multiple courses")
    void testRegisterCourseTest3() {
        Department department = new Department("Computer Science");
        Address address = new Address(123, "Main St", "Montreal", Province.QC, "H1A2B3");
        Student student = new Student("John Doe", Gender.MALE, address, department);
        Course course1 = new Course("Programming 1", 3.0, department);
        Course course2 = new Course("Mathematics", 3.0, department);

        student.registerCourse(course1);
        boolean expected = true;
        boolean result = student.registerCourse(course2);
        assertEquals(expected, result);
        assertEquals(2, student.getRegisteredCourses().size());
    }

    // Student.dropCourse() Tests

    @Test
    @DisplayName("dropCourse: Test 1 - successfully drop a registered course")
    void testDropCourseTest1() {
        Department department = new Department("Computer Science");
        Address address = new Address(123, "Main St", "Montreal", Province.QC, "H1A2B3");
        Student student = new Student("John Doe", Gender.MALE, address, department);
        Course course = new Course("Programming 1", 3.0, department);

        student.registerCourse(course);
        boolean expected = true;
        boolean result = student.dropCourse(course);
        assertEquals(expected, result);
        assertFalse(student.getRegisteredCourses().contains(course));
    }

    @Test
    @DisplayName("dropCourse: Test 2 - fail to drop non-registered course")
    void testDropCourseTest2() {
        Department department = new Department("Computer Science");
        Address address = new Address(123, "Main St", "Montreal", Province.QC, "H1A2B3");
        Student student = new Student("John Doe", Gender.MALE, address, department);
        Course course = new Course("Programming 1", 3.0, department);

        boolean expected = false;
        boolean result = student.dropCourse(course);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("dropCourse: Test 3 - drop one of multiple registered courses")
    void testDropCourseTest3() {
        Department department = new Department("Computer Science");
        Address address = new Address(123, "Main St", "Montreal", Province.QC, "H1A2B3");
        Student student = new Student("John Doe", Gender.MALE, address, department);
        Course course1 = new Course("Programming 1", 3.0, department);
        Course course2 = new Course("Mathematics", 3.0, department);

        student.registerCourse(course1);
        student.registerCourse(course2);
        boolean expected = true;
        boolean result = student.dropCourse(course1);
        assertEquals(expected, result);
        assertEquals(1, student.getRegisteredCourses().size());
    }

    // Course.isAssignmentWeightValid() Tests

    @Test
    @DisplayName("isAssignmentWeightValid: Test 1 - weights sum to 100")
    void testIsAssignmentWeightValidTest1() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);

        course.addAssignment("Assignment 1", 20, 100);
        course.addAssignment("Assignment 2", 30, 100);
        course.addAssignment("Exam", 50, 100);
        boolean expected = true;
        boolean result = course.isAssignmentWeightValid();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("isAssignmentWeightValid: Test 2 - weights do not sum to 100")
    void testIsAssignmentWeightValidTest2() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);

        course.addAssignment("Assignment 1", 20, 100);
        course.addAssignment("Assignment 2", 30, 100);
        boolean expected = false;
        boolean result = course.isAssignmentWeightValid();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("isAssignmentWeightValid: Test 3 - no assignments")
    void testIsAssignmentWeightValidTest3() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);

        boolean expected = false;
        boolean result = course.isAssignmentWeightValid();
        assertEquals(expected, result);
    }

    // Course.addAssignment() Tests

    @Test
    @DisplayName("addAssignment: Test 1 - add first assignment")
    void testAddAssignmentTest1() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);

        boolean expected = true;
        boolean result = course.addAssignment("Assignment 1", 20, 100);
        assertEquals(expected, result);
        assertEquals(1, course.getAssignments().size());
    }

    @Test
    @DisplayName("addAssignment: Test 2 - add multiple assignments")
    void testAddAssignmentTest2() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);

        course.addAssignment("Assignment 1", 20, 100);
        boolean expected = true;
        boolean result = course.addAssignment("Assignment 2", 30, 100);
        assertEquals(expected, result);
        assertEquals(2, course.getAssignments().size());
    }

    @Test
    @DisplayName("addAssignment: Test 3 - assignment added with correct weight")
    void testAddAssignmentTest3() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);

        course.addAssignment("Assignment 1", 25.5, 100);
        double expected = 25.5;
        double result = course.getAssignments().get(0).getWeight();
        assertEquals(expected, result, 0.001);
    }

    // Course.calcStudentsAverage() Tests

    @Test
    @DisplayName("calcStudentsAverage: Test 1 - calculate average for one student")
    void testCalcStudentsAverageTest1() {
        Department department = new Department("Computer Science");
        Address address = new Address(123, "Main St", "Montreal", Province.QC, "H1A2B3");
        Student student = new Student("John Doe", Gender.MALE, address, department);
        Course course = new Course("Programming 1", 3.0, department);

        student.registerCourse(course);
        course.addAssignment("Assignment 1", 50, 100);
        course.addAssignment("Assignment 2", 50, 100);
        course.getAssignments().get(0).getScores().set(0, 80);
        course.getAssignments().get(1).getScores().set(0, 90);

        int[] result = course.calcStudentsAverage();
        assertEquals(85, result[0]);
    }

    @Test
    @DisplayName("calcStudentsAverage: Test 2 - calculate average for multiple students")
    void testCalcStudentsAverageTest2() {
        Department department = new Department("Computer Science");
        Address address1 = new Address(123, "Main St", "Montreal", Province.QC, "H1A2B3");
        Address address2 = new Address(456, "Oak St", "Toronto", Province.ON, "M5V3A8");
        Student student1 = new Student("John Doe", Gender.MALE, address1, department);
        Student student2 = new Student("Jane Smith", Gender.FEMALE, address2, department);
        Course course = new Course("Programming 1", 3.0, department);

        student1.registerCourse(course);
        student2.registerCourse(course);
        course.addAssignment("Assignment 1", 60, 100);
        course.addAssignment("Assignment 2", 40, 100);
        course.getAssignments().get(0).getScores().set(0, 70);
        course.getAssignments().get(1).getScores().set(0, 80);
        course.getAssignments().get(0).getScores().set(1, 90);
        course.getAssignments().get(1).getScores().set(1, 100);

        int[] result = course.calcStudentsAverage();
        assertEquals(74, result[0]);
        assertEquals(94, result[1]);
    }

    @Test
    @DisplayName("calcStudentsAverage: Test 3 - no students registered")
    void testCalcStudentsAverageTest3() {
        Department department = new Department("Computer Science");
        Course course = new Course("Programming 1", 3.0, department);

        int[] result = course.calcStudentsAverage();
        assertEquals(0, result.length);
    }

    // Assignment.calcAssignmentAvg() Tests

    @Test
    @DisplayName("calcAssignmentAvg: Test 1 - average of multiple scores")
    void testCalcAssignmentAvgTest1() {
        Assignment assignment = new Assignment("Quiz 1", 20.0);
        assignment.getScores().add(80);
        assignment.getScores().add(90);
        assignment.getScores().add(70);

        double expected = 80.0;
        double result = assignment.calcAssignmentAvg();
        assertEquals(expected, result, 0.001);
    }

    @Test
    @DisplayName("calcAssignmentAvg: Test 2 - empty scores list")
    void testCalcAssignmentAvgTest2() {
        Assignment assignment = new Assignment("Quiz 1", 20.0);

        double expected = 0.0;
        double result = assignment.calcAssignmentAvg();
        assertEquals(expected, result, 0.001);
    }

    @Test
    @DisplayName("calcAssignmentAvg: Test 3 - single score")
    void testCalcAssignmentAvgTest3() {
        Assignment assignment = new Assignment("Quiz 1", 20.0);
        assignment.getScores().add(95);

        double expected = 95.0;
        double result = assignment.calcAssignmentAvg();
        assertEquals(expected, result, 0.001);
    }

    // Assignment.generateRandomScore() Tests

    @Test
    @DisplayName("generateRandomScore: Test 1 - scores generated for all students")
    void testGenerateRandomScoreTest1() {
        Assignment assignment = new Assignment("Quiz 1", 20.0);
        assignment.getScores().add(null);
        assignment.getScores().add(null);
        assignment.getScores().add(null);
        assignment.generateRandomScore();

        for (Integer score : assignment.getScores()) {
            assertNotNull(score);
            assertTrue(score >= 0 && score <= 100);
        }
    }

    @Test
    @DisplayName("generateRandomScore: Test 2 - scores in valid range")
    void testGenerateRandomScoreTest2() {
        Assignment assignment = new Assignment("Quiz 1", 20.0);
        for (int i = 0; i < 5; i++) {
            assignment.getScores().add(null);
        }
        assignment.generateRandomScore();

        for (Integer score : assignment.getScores()) {
            assertTrue(score >= 0 && score <= 100);
        }
    }

    @Test
    @DisplayName("generateRandomScore: Test 3 - no scores to generate")
    void testGenerateRandomScoreTest3() {
        Assignment assignment = new Assignment("Quiz 1", 20.0);
        assignment.generateRandomScore();

        assertEquals(0, assignment.getScores().size());
    }
}
