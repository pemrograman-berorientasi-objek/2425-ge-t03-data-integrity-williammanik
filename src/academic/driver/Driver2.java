package academic.driver;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> invalidMessages = new ArrayList<>(); // To store invalid messages

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }

            String[] segments = input.split("#");
            if (segments.length > 1) {
                String command = segments[0];

                try {
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
                                    throw new IllegalArgumentException("Course with code " + courseCode + " already exists.");
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
                                    throw new IllegalArgumentException("Student with ID " + studentId + " already exists.");
                                }

                                Student student = new Student(studentId, name, enrollmentYear, major);
                                students.add(student);
                            }
                            break;

                        case "enrollment-add":
                            if (segments.length == 5) {
                                String courseCode = segments[1];
                                String studentId = segments[2];
                                String academicYear = segments[3];
                                String semester = segments[4];

                                // Check if course and student exist before enrolling
                                boolean courseExists = courses.stream().anyMatch(c -> c.getCode().equals(courseCode));
                                boolean studentExists = students.stream().anyMatch(s -> s.getStudentId().equals(studentId));

                                // Collect invalid messages only once for the same student or course
                                if (!courseExists && !invalidMessages.contains("invalid course|" + courseCode)) {
                                    invalidMessages.add("invalid course|" + courseCode);
                                }
                                if (!studentExists && !invalidMessages.contains("invalid student|" + studentId)) {
                                    invalidMessages.add("invalid student|" + studentId);
                                }

                                // Only add enrollment if both course and student exist
                                if (courseExists && studentExists) {
                                    Enrollment enrollment = new Enrollment(courseCode, studentId, academicYear, semester);
                                    enrollments.add(enrollment);
                                }
                            }
                            break;

                        default:
                            System.out.println("Invalid command: " + command);
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // Print invalid messages first
        for (String message : invalidMessages) {
            System.out.println(message);
        }

        // Sort courses by courseCode (ascending order)
        Collections.sort(courses, Comparator.comparing(Course::getCode));

        // Sort students by studentId (descending order)
        Collections.sort(students, (s1, s2) -> s2.getStudentId().compareTo(s1.getStudentId()));

        // Sort enrollments by academicYear (descending), then courseId and studentId (ascending)
        Collections.sort(enrollments, Comparator.comparing(Enrollment::getAcademicYear).reversed()
                .thenComparing(Enrollment::getCourseId).thenComparing(Enrollment::getStudentId));

        // Print all courses
        for (Course course : courses) {
            System.out.println(course.getCode() + "|" + course.getName() + "|" + course.getCredit() + "|" + course.getGrade());
        }

        // Print all students
        for (Student student : students) {
            System.out.println(student.getStudentId() + "|" + student.getName() + "|" + student.getEnrollmentYear() + "|" + student.getMajor());
        }

        // Print all enrollments
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getCourseId() + "|" + enrollment.getStudentId() + "|" + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|None");
        }

        scanner.close();
    }
}
