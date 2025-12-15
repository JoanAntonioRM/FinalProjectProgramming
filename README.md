# Final Project Programming

**Author:** Joan Antonio Rodriguez Munoz  
**Student ID:** 2533309  
**Course:** Introduction to Programming

This is the repository for the final project in programming

## Classes and Methods

### Address
- **Fields:** `streetNo`, `street`, `city`, `province`, `postalCode`
- **Methods:** `isPostalCodeValid`, Constructor, `toString`, `equals`, getters, setters

### Department
- **Fields:** `departmentId`, `departmentName`, `nextId`
- **Methods:** `isDepartmentNameValid`, Constructor, `toString`, `equals`, getters, setter for `departmentName`

### Student
- **Fields:** `studentId`, `studentName`, `gender`, `address`, `department`, `registeredCourses`, `nextId`
- **Methods:** `registerCourse`, `dropCourse`, Constructor, `toSimplifiedString`, `toString`, `equals`, getters, setters

### Assignment
- **Fields:** `assignmentId`, `assignmentName`, `weight`, `scores`, `nextId`
- **Methods:** `calcAssignmentAvg`, `generateRandomScore`, `toString`, getters, setters

### Course
- **Fields:** `courseId`, `courseName`, `credits`, `department`, `assignments`, `registeredStudents`, `nextId`
- **Methods:** `isAssignmentWeightValid`, `registerStudent`, `calcStudentsAverage`, `addAssignment`, `generateScores`, `displayScores`, `toSimplifiedString`, `toString`, `equals`, getters, setters

### Province (Enum)
- **Values:** `AB`, `BC`, `MB`, `NB`, `NL`, `NS`, `ON`, `PE`, `QC`, `SK`

### Gender (Enum)
- **Values:** `MALE`, `FEMALE`

### Util
- **Methods:** `toTitleCase`

## Unit Testing

All user-defined methods have been tested with JUnit Jupiter.
- **Address:** `isPostalCodeValid`
- **Department:** `isDepartmentNameValid`
- **Student:** `registerCourse`, `dropCourse`
- **Assignment:** `calcAssignmentAvg`, `generateRandomScore`
- **Course:** `isAssignmentWeightValid`, `addAssignment`, `calcStudentsAverage`
- **Util:** `toTitleCase`

## Class Diagram

See `ClassDiagram.png` for the complete UML class diagram showing all classes, relationships, and methods.