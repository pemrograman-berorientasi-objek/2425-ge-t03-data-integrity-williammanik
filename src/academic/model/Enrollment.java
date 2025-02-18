package academic.model;

/**
 * @author 12S21018_Williammanik
 
 */
public class Enrollment {
    private String courseId;
    private String studentId;
    private String academicYear;
    private String semester;
    private String grade;

    // Constructor
    public Enrollment(String courseId, String studentId, String academicYear, String semester) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = "None";  // Default grade is "None"
    }

    // Getter methods
    public String getCourseId() {
        return courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;
    }

    // Setter for grade
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return courseId + "|" + studentId + "|" + academicYear + "|" + semester + "|" + grade;
    }
}