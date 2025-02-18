package academic.model;

/**
 * @author 12S21018_Williammanik
 
 */
public class Course {

    private String code;
    private String name;
    private int credit;
    private String grade;

    // Constructor
    public Course(String code, String name, int credit, String grade) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.grade = grade;
    }

    // Getter methods
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return code + "|" + name + "|" + credit + "|" + grade;
    }

}