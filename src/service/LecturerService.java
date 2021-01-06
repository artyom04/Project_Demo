package service;

import model.Employee;
import model.Lecturer;
import model.exceptions.InvalidInputException;

import java.util.Comparator;
import java.util.LinkedHashSet;

public class LecturerService extends EmployeeService {

    private static Lecturer createLecturer() {
        Lecturer lecturer = new Lecturer(EmployeeService.createEmployee());
        boolean indicator = true;
        while (indicator) {
            System.out.print("Please Choose Academic Degree: 1. Associate 2. Bachelors 3. Masters 4. Doctoral: ");
            String choice = scanner.next();
            if (lecturer.setAcademicDegreeByValue(choice)) {
                indicator = false;
            } else {
                System.out.println("You have chosen wrong value! Please try again");
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Please Enter Taught Course: ");
            scanner.useDelimiter("\n");
            String course = scanner.next();
            try {
                lecturer.setTaughtCourse(course.trim());
                indicator = false;
            } catch (InvalidInputException e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());
            }
        }
        return lecturer;
    }

    public static void createLecturerAndSave() {
        createEmployeeAndSave(createLecturer(), "src/files/lecturer.txt");
    }

    public static LinkedHashSet<Lecturer> getLecturersSetFromFile() {
        LinkedHashSet<Lecturer> lecturerLinkedHashSet = new LinkedHashSet<>();
        try {
            String[] lecturerInformation = FileService.read("src/files/lecturer.txt");
            for (int i = 1; i < lecturerInformation.length; i++) {
                lecturerLinkedHashSet.add(new Lecturer(lecturerInformation[i]));
            }
        } catch (Exception e) {
            System.out.println("Can't Read/ Invalid Data");
            System.out.println(e.toString());
        }
        return lecturerLinkedHashSet;
    }

    public static void printAllLecturers() {
        printEmployees(getLecturersSetFromFile());
    }

    public static void findLecturerByPassportIdAndPrint() {
        findEmployeeByPassportIdAndPrint(getLecturersSetFromFile());
    }

    public static void findLecturersByCourseAndPrint() {
        System.out.print("Enter the course: ");
        scanner.useDelimiter("\n");
        String course = scanner.next();
        boolean indicator = true;
        try {
            LinkedHashSet<Lecturer> lecturerLinkedHashSet = getLecturersSetFromFile();
            if (lecturerLinkedHashSet.isEmpty()) {
                System.out.println("Empty!");
            } else {
                for (Lecturer lecturer : lecturerLinkedHashSet) {
                    if ((lecturer.getTaughtCourse()).equalsIgnoreCase(course.trim())) {
                        lecturer.printEmployee();
                        indicator = false;
                    }
                }
                if (indicator) {
                    System.out.println("No one with such Course");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }
    }

    public static void findLecturersBySalaryGreaterThan() {
        findEmployeeBySalaryGreaterThan(getLecturersSetFromFile());
    }

    public static void findLecturersByNameAndPrint() {
        findEmployeeByNameAndPrint(getLecturersSetFromFile());
    }

    public static <T extends Comparator<Employee>> void sortLecturersBy(T comparator) {
        sortEmployeeBy(comparator, getLecturersSetFromFile());
    }

    public static void printLecturerBonusAndTax() {
        printEmployeeBonusAndTax(getLecturersSetFromFile());

    }

    public static void printLecturersFinancialReport() {
        printFinancialReport(getLecturersSetFromFile());
    }
}