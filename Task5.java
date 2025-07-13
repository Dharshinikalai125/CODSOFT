import java.io.*;
import java.util.*;

// Student class with Serializable for file storage
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private String email;

    public Student(String name, int rollNumber, String grade, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade + ", Email: " + email;
    }
}

// Main Student Management System class
public class Task5{
    private static Map<Integer, Student> students = new HashMap<>();
    private static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        loadFromFile();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine().trim();
            if (!input.matches("[1-5]")) {
                System.out.println("Invalid input. Enter a number between 1 and 5.");
                continue;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    removeStudent(sc);
                    break;
                case 3:
                    searchStudent(sc);
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    saveToFile();
                    sc.close();
                    return;
            }
        }
    }

    // Add a new student
    private static void addStudent(Scanner sc) {
        try {
            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter Roll Number: ");
            int rollNumber = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter Grade: ");
            String grade = sc.nextLine().trim();

            System.out.print("Enter Email: ");
            String email = sc.nextLine().trim();

            if (name.isEmpty() || grade.isEmpty() || email.isEmpty()) {
                System.out.println("Error: Fields cannot be empty.");
                return;
            }

            if (students.containsKey(rollNumber)) {
                System.out.println("A student with this roll number already exists.");
                return;
            }

            Student s = new Student(name, rollNumber, grade, email);
            students.put(rollNumber, s);
            saveToFile();
            System.out.println("Student added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number format.");
        }
    }

    // Remove a student
    private static void removeStudent(Scanner sc) {
        try {
            System.out.print("Enter Roll Number to remove: ");
            int rollNumber = Integer.parseInt(sc.nextLine().trim());

            if (students.remove(rollNumber) != null) {
                saveToFile();
                System.out.println("Student removed successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number format.");
        }
    }

    // Search a student
    private static void searchStudent(Scanner sc) {
        try {
            System.out.print("Enter Roll Number to search: ");
            int rollNumber = Integer.parseInt(sc.nextLine().trim());

            Student s = students.get(rollNumber);
            if (s != null) {
                System.out.println("Student Found: " + s);
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number format.");
        }
    }

    // Display all students
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\nList of Students:");
        students.values().forEach(System.out::println);
    }

    // Save student data to file
    private static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load student data from file
    @SuppressWarnings("unchecked")
    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (Map<Integer, Student>) in.readObject();
        } catch (Exception e) {
            System.out.println("Error loading data. Starting with an empty list.");
        }
    }
}

/*OUTPUT:

===== Student Management System =====
1. Add Student
2. Remove Student
3. Search Student
4. Display All Students
5. Exit
Enter your choice: 1
Enter Name: abu
Enter Roll Number: 101
Enter Grade: a
Enter Email: abu@gmail.com
A student with this roll number already exists.*/
