package academic.driver;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver1 {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }

            String[] segments = input.split("#");
            if (segments.length > 1) {
                String command = segments[0];

                switch (command) {
                    case "course-add":
                        if (segments.length == 5) {
                            String courseCode = segments[1]; // Rename to match 'code' in Course
                            String courseName = segments[2];
                            int credit = Integer.parseInt(segments[3]);
                            String grade = segments[4];

                            // Check for duplication in course by comparing the course code
                            boolean courseExists = courses.stream().anyMatch(c -> c.getCode().equals(courseCode));
                            if (courseExists) {
                                System.exit(1); // Terminate the program immediately if duplicate found
                            }
                            Course course = new Course(courseCode, courseName, credit, grade);
                            courses.add(course);
                        }
                        break;

                    case "student-add":
                        if (segments.length == 5) {
                            String studentId = segments[1];
                            String name = segments[2];
                            int enrollmentYear = Integer.parseInt(segments[3]);
                            String major = segments[4];

                            // Check for duplication in student
                            boolean studentExists = students.stream().anyMatch(s -> s.getStudentId().equals(studentId));
                            if (studentExists) {
                                System.exit(1); // Terminate the program immediately if duplicate found
                            }
                            Student student = new Student(studentId, name, enrollmentYear, major);
                            students.add(student);
                        }
                        break;

                    case "enrollment-add":
                        if (segments.length == 5) {
                            String courseId = segments[1];
                            String studentId = segments[2];
                            String academicYear = segments[3];
                            String semester = segments[4];

                            // Check for duplication in enrollment
                            boolean enrollmentExists = enrollments.stream().anyMatch(e -> e.getCourseId().equals(courseId) && e.getStudentId().equals(studentId));
                            if (enrollmentExists) {
                                System.exit(1); // Terminate the program immediately if duplicate found
                            }
                            Enrollment enrollment = new Enrollment(courseId, studentId, academicYear, semester);
                            enrollments.add(enrollment);
                        }
                        break;

                    default:
                        System.out.println("Invalid command: " + command);
                        break;
                }
            }
        }

        // Print all courses
        for (Course course : courses) {
            System.out.println(course);
        }

        // Print all students
        for (Student student : students) {
            System.out.println(student);
        }

        // Print all enrollments
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

        scanner.close();
    }
}
