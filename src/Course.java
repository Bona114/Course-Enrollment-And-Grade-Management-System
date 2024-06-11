import java.util.ArrayList;
import java.util.HashMap;

public class Course {
    private String name;
    private int courseCode;
    private int maximumCapacity;
    private ArrayList<Student> enrolledStudents = new ArrayList<>();
    private HashMap<Student, Integer> studentGrades = new HashMap<>();
    public static int enrolledStudentsCount;

    public Course(String name, int courseCode, int maximumCapacity) {
        this.name = name;
        this.courseCode = courseCode;
        this.maximumCapacity = maximumCapacity;
    }

    public int getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(int newCode) {
        this.courseCode = newCode;
    }

    public int getMaximumCapacity() {
        return this.maximumCapacity;
    }

    public void setMaximumCapacity(int newCapacity) {
        this.maximumCapacity = newCapacity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return this.enrolledStudents;
    }

    public void enrollStudent(Student student) {
        if (this.enrolledStudents.size() < this.maximumCapacity) {
            this.enrolledStudents.add(student);
            this.studentGrades.put(student, 0);
        } else {
            System.out.println("Error: Course is at maximum capacity");
        }
    }
}
