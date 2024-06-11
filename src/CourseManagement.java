import java.util.ArrayList;
import java.util.HashMap;

public class CourseManagement {
    private static final ArrayList<Course> courses = new ArrayList<>();
    private static final HashMap<Student, ArrayList<Course>> studentCourses = new HashMap<>();
    private static final HashMap<Student, Integer> studentGrades = new HashMap<>();

    public static void updateStudentCourses(Student student, ArrayList<Course> courses) {
        studentCourses.put(student, courses);
    }

    public static void addCourse(String name, int code, int maximumCapacity) {
        Course course = new Course(name, code, maximumCapacity);
        courses.add(course);
    }

    public static void enrollStudent(Student student, Course course) {
        student.enrollCourse(course);
    }

    public static void assignGrade(Student student, Course course, int grade) {
        student.assignGrade(course, grade);
    }

    public static void calculateOverallGrade(Student student) {
        if (student.courseGrades.isEmpty()) {
            studentGrades.put(student, 0);
        } else {
            int sum = 0;
            for (int grade : student.courseGrades.values()) {
                sum += grade;
            }
            studentGrades.put(student, sum / student.courseGrades.size());
        }
    }

    public ArrayList<Course> getStudentCourses(Student student) {
        return studentCourses.get(student);
    }

    public static Student[] getStudentList() {
        return studentCourses.keySet().toArray(new Student[0]);
    }

    public static Course[] getCourseList() {
        return courses.toArray(new Course[0]);
    }

    public static int getStudentOverallGrade(Student student) {
        return studentGrades.getOrDefault(student, 0);
    }
}
