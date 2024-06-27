import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course
{
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;
    public Course(String courseCode, String title, String description, int capacity)
    {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
    }
    public String getCourseCode()
    {
        return courseCode;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDescription()
    {
        return description;
    }
    public int getCapacity()
    {
        return capacity;
    }
    public int getEnrolledStudents()
    {
        return enrolledStudents;
    }
    public boolean registerStudent()
    {
        if (enrolledStudents < capacity)
        {
            enrolledStudents++;
            return true;
        }
        return false;
    }
    public void dropStudent()
    {
        if (enrolledStudents > 0)
        {
            enrolledStudents--;
        }
    }
    @Override
    public String toString()
    {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nEnrolled Students: " + enrolledStudents + "\n";
    }
}

class Student
{
    private String studentID;
    private String name;
    private List<Course> registeredCourses;
    public Student(String studentID, String name)
    {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
    public String getStudentID()
    {
        return studentID;
    }
    public String getName()
    {
        return name;
    }
    public List<Course> getRegisteredCourses()
    {
        return registeredCourses;
    }
    public boolean registerCourse(Course course)
    {
        if (course.registerStudent())
        {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }
    public void dropCourse(Course course)
    {
        course.dropStudent();
        registeredCourses.remove(course);
    }
    @Override
    public String toString()
    {
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + registeredCourses.size() + "\n";
    }
}

public class CourseRegistrationSystem
{
    private List<Course> courses;
    private List<Student> students;
    public CourseRegistrationSystem()
    {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }
    public void addCourse(Course course)
    {
        courses.add(course);
    }
    public void addStudent(Student student)
    {
        students.add(student);
    }
    public void displayCourses()
    {
        System.out.println("Available Courses:");
        for (Course course : courses)
        {
            System.out.println(course);
        }
    }
    public void registerStudentForCourse(Student student, Course course)
    {
        if (student.registerCourse(course))
        {
            System.out.println(student.getName() + " registered successfully for course: " + course.getTitle());
        }
        else
        {
            System.out.println("Course registration failed. Course is full.");
        }
    }
    public void dropStudentFromCourse(Student student, Course course)
    {
        student.dropCourse(course);
        System.out.println(student.getName() + " dropped course: " + course.getTitle());
    }
    public static void main(String[] args)
    {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();
        Scanner scanner = new Scanner(System.in);
        Course c1 = new Course("CSE101", "Introduction to Programming", "Basic programming concepts", 30);
        Course c2 = new Course("MAT202", "Calculus II", "Advanced calculus topics", 25);
        Course c3 = new Course("ENG301", "English Literature", "Literary analysis and critique", 20);
        registrationSystem.addCourse(c1);
        registrationSystem.addCourse(c2);
        registrationSystem.addCourse(c3);
        Student s1 = new Student("1001", "Raj");
        Student s2 = new Student("1002", "Bikas");
        Student s3 = new Student("1003", "Daisy");
        Student s4 = new Student("1004", "Tanya");
        registrationSystem.addStudent(s1);
        registrationSystem.addStudent(s2);
        registrationSystem.addStudent(s3);
        registrationSystem.addStudent(s4);
        boolean exit = false;
        while (!exit)
        {
            System.out.println("\nMenu:");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register Student for Course");
            System.out.println("3. Drop Course for Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice)
            {
                case 1:
                    registrationSystem.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    Student student = registrationSystem.findStudent(studentID);
                    Course course = registrationSystem.findCourse(courseCode);
                    if (student != null && course != null)
                    {
                        registrationSystem.registerStudentForCourse(student, course);
                    }
                    else
                    {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    courseCode = scanner.nextLine();
                    student = registrationSystem.findStudent(studentID);
                    course = registrationSystem.findCourse(courseCode);
                    if (student != null && course != null)
                    {
                        registrationSystem.dropStudentFromCourse(student, course);
                    }
                    else
                    {
                        System.out.println("Student or course not found.");
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }
    public Student findStudent(String studentID)
    {
        for (Student student : students)
        {
            if (student.getStudentID().equals(studentID))
            {
                return student;
            }
        }
        return null;
    }
    public Course findCourse(String courseCode)
    {
        for (Course course : courses)
        {
            if (course.getCourseCode().equals(courseCode))
            {
                return course;
            }
        }
        return null;
    }
}