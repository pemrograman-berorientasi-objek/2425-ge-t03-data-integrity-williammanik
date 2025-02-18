package academic.model;

/**
 * @author 12S21018_Williammanik
 
 */
public class Student {
    private String studentId;
    private String name;
    private int enrollmentYear;
    private String major;

    // Constructor
    public Student(String studentId, String name, int enrollmentYear, String major) {
        this.studentId = studentId;
        this.name = name;
        this.enrollmentYear = enrollmentYear;
        this.major = major;
    }

    // Getter methods
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return studentId + "|" + name + "|" + enrollmentYear + "|" + major;
    }
}