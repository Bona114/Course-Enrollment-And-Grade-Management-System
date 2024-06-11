import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    private String name;
    private int ID;
    private ArrayList<Course> enrolledCourses = new ArrayList<>();
    public HashMap<Course, Integer> courseGrades = new HashMap<>();

    public Student(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int newID) {
        this.ID = newID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return this.enrolledCourses;
    }

    public void enrollCourse(Course newCourse) {
        if (!this.enrolledCourses.contains(newCourse)) {
            this.enrolledCourses.add(newCourse);
            this.courseGrades.put(newCourse, 0);
            CourseManagement.updateStudentCourses(this, enrolledCourses);
            Course.enrolledStudentsCount++;
        }
    }

    public void assignGrade(Course course, int grade) {
        this.courseGrades.put(course, grade);
    }
}
