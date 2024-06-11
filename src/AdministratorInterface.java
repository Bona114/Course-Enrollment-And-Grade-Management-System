import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class AdministratorInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Administrator Interface");
        start();
    }

    private static void start() {
        System.out.println("\n(A:Add_New_Course B:Enroll_Student C:Assign_Grades D:Calculate_Overall_Grades E:Exit)");

        String input = scanner.nextLine().toUpperCase();

        switch (input) {
            case "A":
                addCourse();
                break;

            case "B":
                System.out.println("New Student? Y/N");
                String newStudent = scanner.nextLine().toUpperCase();

                if (newStudent.equals("Y")) {
                    enrollNewStudent();

                } else if (newStudent.equals("N")) {
                    enrollExistingStudent();

                } else {
                    System.out.println("Error: Unexpected Input");
                    start();
                }
                break;

            case "C":
                assignGrades();
                break;
            case "D":
                calculateOverallGrades();
                break;
            case "E":
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Error: Unexpected input");
                start();
                break;
        }
    }

    public static void addCourse() {
        System.out.println("Enter Course Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Course Code:");
        int code;
        try {
            code = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input for Course Code. Please enter a valid integer.");
            start();
            return;
        }

        System.out.println("Enter Maximum Capacity:");
        int maxCapacity;
        try {
            maxCapacity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input for Maximum Capacity. Please enter a valid integer.");
            start();
            return;
        }

        CourseManagement.addCourse(name, code, maxCapacity);

        System.out.println("Successfully added " + name + "\nCourse code: " + code);
        start();
    }

    public static void enrollNewStudent() {
        System.out.println("Enter Student Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Student ID:");
        int ID = scanner.nextInt();
        scanner.nextLine();

        boolean studentExists = false;
        for (Student existingStudent : CourseManagement.getStudentList()) {
            if (ID == existingStudent.getID()) {
                System.out.println("Error: ID already exists");
                studentExists = true;
                break;
            }
        }
        if (studentExists) return;

        Student student = new Student(name, ID);

        while (true) {
            System.out.println("Enter Course Code:");
            int code = scanner.nextInt();
            scanner.nextLine();

            boolean courseExists = Arrays.stream(CourseManagement.getCourseList()).anyMatch(course -> course.getCourseCode() == code);

            if (courseExists) {
                Optional<Course> courseOpt = Arrays.stream(CourseManagement.getCourseList()).filter(course -> course.getCourseCode() == code).findFirst();

                if (courseOpt.isPresent()) {
                    Course course = courseOpt.get();
                    CourseManagement.enrollStudent(student, course);
                    System.out.println("Successfully enrolled " + student.getName() + " to " + course.getName());
                    break;
                }
            } else {
                System.out.println("Error: Course Code " + code + " doesn't exist");
            }
        }
        start();
    }

    private static void enrollExistingStudent() {
        System.out.println("Enter Student ID:");
        int ID = scanner.nextInt();
        scanner.nextLine();
        Student student = getStudent(ID);

        if (student == null) {
            System.out.println("Error: ID doesn't exist");
            enrollExistingStudent();

        } else {
            System.out.println("Enter Course Code:");
            int code = scanner.nextInt();
            scanner.nextLine();
            Course course = getCourse(code);

            if (course != null) {
                System.out.println("Error: Course Code " + code + " doesn't exist");
                enrollExistingStudent();

            } else {
                CourseManagement.enrollStudent(student, course);
                System.out.println("Successfully enrolled " + student.getName() + " to " + course.getName());
                System.out.println("Student ID:" + ID);
                start();
            }
        }
    }

    private static void assignGrades() {
        System.out.println("Enter Student ID:");
        int ID = scanner.nextInt();
        scanner.nextLine();
        Student student = getStudent(ID);

        if (student == null) {
            System.out.println("Error: ID doesn't exist");
            assignGrades();

        } else {
            System.out.println("Enter Course Code:");
            int code = scanner.nextInt();
            scanner.nextLine();
            Course course = getCourse(code);

            if (course == null) {
                System.out.println("Error: Course code" + code + " doesn't exist");
                assignGrades();

            } else {
                System.out.println("Enter Grade (1-100):");
                int grade = scanner.nextInt();
                scanner.nextLine();

                CourseManagement.assignGrade(student, course, grade);
                System.out.println(
                        "Successfully added " + grade + " to " + student.getName() + " in " + course.getName());
                start();
            }
        }
    }

    private static void calculateOverallGrades() {
        System.out.println("Enter Student ID:");
        int ID = scanner.nextInt();
        scanner.nextLine();
        Student student = getStudent(ID);

        if (student == null) {
            System.out.println("Error: ID doesn't exist");
            assignGrades();

        } else {
            CourseManagement.calculateOverallGrade(student);
            System.out.println("Successfully calculated Overall Grade");
            System.out.println(student.getName() +
                    "'s overall grade is " + CourseManagement.getStudentOverallGrade(student));
            start();
        }
    }

    private static Student getStudent(int ID) {
        for (Student student : CourseManagement.getStudentList()) {
            if (ID == student.getID()) {
                return student;
            }
        }
        return null;
    }

    private static Course getCourse(int code) {
        for (Course course : CourseManagement.getCourseList()) {
            if (code == course.getCourseCode()) {
                return course;
            }
        }
        return null;
    }
}
