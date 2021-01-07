package service;

import model.Employee;
import model.Lecturer;
import model.exceptions.InvalidInputException;

import java.util.Comparator;
import java.util.LinkedHashSet;

/**
 * {@code LecturerService} class which extends {@code EmployeeService} class;
 * Contains functions which manipulate with {@code Lecturer} class objects
 *
 * @author Artyom
 */
public class LecturerService extends EmployeeService {

    /**
     * Creates {@code Lecturer} instance
     *
     * @return {@code Lecturer} object
     */
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

    /**
     * Creates {@code Lecturer} instance and saves to file
     */
    public static void createLecturerAndSave() {
        createEmployeeAndSave(createLecturer(), "src/files/lecturer.txt");
    }

    /**
     * Reads from file and creates LinkedHashSet of {@code Lecturer}s
     *
     * @return LinkedHashSet of {@code Lecturer}s
     */
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

    /**
     * Prints all {@code Lecturer}s
     */
    public static void printAllLecturers() {
        printEmployees(getLecturersSetFromFile());
    }

    /**
     * Finds {@code Lecturer} by Passport ID and Prints
     */
    public static void findLecturerByPassportIdAndPrint() {
        findEmployeeByPassportIdAndPrint(getLecturersSetFromFile());
    }

    /**
     * Finds {@code Lecturer} by Taught Course Name and Prints
     */
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

    /**
     * Finds {@code Lecturer} by Salary Greater Than Given Value and Prints
     */
    public static void findLecturersBySalaryGreaterThan() {
        findEmployeeBySalaryGreaterThan(getLecturersSetFromFile());
    }

    /**
     * Finds {@code Lecturer} by Full Name and Prints
     */
    public static void findLecturersByNameAndPrint() {
        findEmployeeByNameAndPrint(getLecturersSetFromFile());
    }

    /**
     * Sorts {@code Lecturer}s by given Comparator
     *
     * @param comparator parameter {@code Comparator}
     * @param <T>        type of {@code Comparator}
     */
    public static <T extends Comparator<Employee>> void sortLecturersBy(T comparator) {
        sortEmployeeBy(comparator, getLecturersSetFromFile());
    }

    /**
     * Prints {@code Lecturer}'s Bonus and Tax Amount Information
     */
    public static void printLecturerBonusAndTax() {
        printEmployeeBonusAndTax(getLecturersSetFromFile());
    }

    /**
     * Prints {@code Lecturer}s Financial Report
     */
    public static void printLecturersFinancialReport() {
        printFinancialReport(getLecturersSetFromFile());
    }
}