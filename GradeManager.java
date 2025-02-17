import java.io.*;
import java.util.*;

public class GradeManager {
    private static final String FILE_NAME = "grades.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> grades = loadGrades();

        while (true) {
            System.out.println("\nStudent Grade Manager");
            System.out.println("1. Add Student Grade");
            System.out.println("2. View Grades");
            System.out.println("3. Update Grade");
            System.out.println("4. Delete Grade");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    grades.add(name + " - " + grade);
                    saveGrades(grades);
                    System.out.println("Grade added successfully!");
                    break;
                case 2:
                    viewGrades(grades);
                    break;
                case 3:
                    viewGrades(grades);
                    System.out.print("Enter student number to update: ");
                    int updateIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (updateIndex > 0 && updateIndex <= grades.size()) {
                        System.out.print("Enter new grade: ");
                        String newGrade = scanner.nextLine();
                        String[] studentData = grades.get(updateIndex - 1).split(" - ");
                        grades.set(updateIndex - 1, studentData[0] + " - " + newGrade);
                        saveGrades(grades);
                        System.out.println("Grade updated successfully!");
                    } else {
                        System.out.println("Invalid selection!");
                    }
                    break;
                case 4:
                    viewGrades(grades);
                    System.out.print("Enter student number to delete: ");
                    int deleteIndex = scanner.nextInt();
                    if (deleteIndex > 0 && deleteIndex <= grades.size()) {
                        grades.remove(deleteIndex - 1);
                        saveGrades(grades);
                        System.out.println("Grade deleted successfully!");
                    } else {
                        System.out.println("Invalid selection!");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static List<String> loadGrades() {
        List<String> grades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                grades.add(line);
            }
        } catch (IOException e) {
            System.out.println("No existing grades found.");
        }
        return grades;
    }

    private static void saveGrades(List<String> grades) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String grade : grades) {
                writer.write(grade);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving grades.");
        }
    }

    private static void viewGrades(List<String> grades) {
        if (grades.isEmpty()) {
            System.out.println("No grades found.");
        } else {
            for (int i = 0; i < grades.size(); i++) {
                System.out.println((i + 1) + ". " + grades.get(i));
            }
        }
    }
}
