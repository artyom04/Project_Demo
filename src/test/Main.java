package test;

import model.comparators.EmployeeComparatorByBirthday;
import model.comparators.EmployeeComparatorByExperience;
import model.comparators.EmployeeComparatorByName;
import model.comparators.EmployeeComparatorBySalary;
import service.AdministrativeEmployeeService;
import service.HrManagerService;
import service.LecturerService;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isMenuActive = true;
        while (isMenuActive) {
            System.out.println("------------MENU------------");
            System.out.println("1. Register HR Manager");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Your input: ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("Registering HR Manager");
                    HrManagerService.register();
                    break;
                case "2":
                    System.out.println("------------Login------------");
                    if (HrManagerService.login()) {
                        openInnerMenu();
                    }
                    break;
                case "3":
                    System.out.println("Exiting Menu");
                    isMenuActive = false;
                    break;
                default:
                    System.out.println("Wrong value chosen, please try again!");
                    break;
            }
        }
    }

    /**
     * Main Inner Menu
     */
    private static void openInnerMenu() {
        boolean isMenuActive = true;
        while (isMenuActive) {
            System.out.println("------------MENU------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Print Employees");
            System.out.println("3. Search Employee(s)");
            System.out.println("4. Sort Employees");
            System.out.println("5. Get Employee Bonus and Tax Info");
            System.out.println("6. Make Employee Bonus and Tax Report");
            System.out.println("7. Log out");
            System.out.print("Your input: ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    createInnerChoice();
                    break;
                case "2":
                    printInnerChoice();
                    break;
                case "3":
                    searchInnerChoice();
                    break;
                case "4":
                    sortInnerChoice();
                    break;
                case "5":
                    bonusAndTaxInnerChoice();
                    break;
                case "6":
                    financialReportInnerChoice();
                    break;
                case "7":
                    System.out.println("Logging out");
                    isMenuActive = false;
                    break;
                default:
                    System.out.println("Wrong value, please try again!");
                    break;
            }
        }
    }

    /**
     * Inner Menu for Creating Employees
     */
    private static void createInnerChoice() {
        System.out.println("1. Create Lecturer 2. Create Administrative Employee 3. Go To Menu");
        System.out.print("Your input: ");
        String innerChoice = scanner.next();
        switch (innerChoice) {
            case "1":
                LecturerService.createLecturerAndSave();
                break;
            case "2":
                AdministrativeEmployeeService.createAdministrativeEmployeeAndSave();
                break;
            case "3":
                System.out.println("Returning");
                break;
            default:
                System.out.println("Wrong Value Entered!");
                break;
        }
    }

    /**
     * Inner Menu for Printing Employees
     */
    private static void printInnerChoice() {
        System.out.println("1. Print Lecturers 2. Print Administrative Employees 3. Print Both 4. Go To Menu");
        System.out.print("Your input: ");
        String innerChoice = scanner.next();
        switch (innerChoice) {
            case "1":
                LecturerService.printAllLecturers();
                break;
            case "2":
                AdministrativeEmployeeService.printAllAdministrativeEmployees();
                break;
            case "3":
                LecturerService.printAllLecturers();
                AdministrativeEmployeeService.printAllAdministrativeEmployees();
                break;
            case "4":
                System.out.println("Returning");
                break;
            default:
                System.out.println("Wrong Value Entered!");
                break;
        }
    }

    /**
     * Inner Menu for Searching Employees
     */
    private static void searchInnerChoice() {
        System.out.println("1. Search Lecturers 2. Search Administrative Employees 3. Go To Menu");
        System.out.print("Your input: ");
        String innerChoice = scanner.next();
        switch (innerChoice) {
            case "1":
                System.out.println("1. By Passport ID  2. By Course  3. By Salary Greater Than  4. By Name " +
                        "5. Go To Menu");
                System.out.print("Your input: ");
                innerChoice = scanner.next();
                switch (innerChoice) {
                    case "1":
                        LecturerService.findLecturerByPassportIdAndPrint();
                        break;
                    case "2":
                        LecturerService.findLecturersByCourseAndPrint();
                        break;
                    case "3":
                        LecturerService.findLecturersBySalaryGreaterThan();
                        break;
                    case "4":
                        LecturerService.findLecturersByNameAndPrint();
                        break;
                    case "5":
                        System.out.println("Returning");
                        break;
                    default:
                        System.out.println("Wrong Value Entered!");
                        break;
                }
                break;
            case "2":
                System.out.println("1. By Passport ID  2. By Salary Greater Than  3. By Name " +
                        "4. Go To Menu");
                System.out.print("Your input: ");
                innerChoice = scanner.next();
                switch (innerChoice) {
                    case "1":
                        AdministrativeEmployeeService.findAdministrativeEmployeeByPassportIdAndPrint();
                        break;
                    case "2":
                        AdministrativeEmployeeService.findAdministrativeEmployeesBySalaryGreaterThan();
                        break;
                    case "3":
                        AdministrativeEmployeeService.findAdministrativeEmployeesByNameAndPrint();
                        break;
                    case "4":
                        System.out.println("Returning");
                        break;
                    default:
                        System.out.println("Wrong Value Entered!");
                        break;
                }
                break;
            case "3":
                System.out.println("Returning");
                break;
            default:
                System.out.println("Wrong Value Entered!");
                break;
        }
    }

    /**
     * Inner Menu for Sorting Employees
     */
    private static void sortInnerChoice() {
        System.out.println("1. Sort Lecturers 2. Sort Administrative Employees 3. Go To Menu");
        System.out.print("Your input: ");
        String innerChoice = scanner.next();
        String lastChoice;
        switch (innerChoice) {
            case "1":
                System.out.println("1. By Experience 2. By Birthday 3. By Name 4. By Salary 5. Go To Menu");
                System.out.print("Your input: ");
                innerChoice = scanner.next();
                switch (innerChoice) {
                    case "1":
                        System.out.println("1. Ascending Order 2. Descending Order 3. Go To Menu");
                        System.out.print("Your input: ");
                        lastChoice = scanner.next();
                        switch (lastChoice) {
                            case "1":
                                LecturerService.sortLecturersBy(new EmployeeComparatorByExperience());
                                break;
                            case "2":
                                LecturerService.sortLecturersBy(new EmployeeComparatorByExperience().reversed());
                                break;
                            case "3":
                                System.out.println("Returning");
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        break;
                    case "2":
                        System.out.println("1. Ascending Order 2. Descending Order 3. Go To Menu");
                        System.out.print("Your input: ");
                        lastChoice = scanner.next();
                        switch (lastChoice) {
                            case "1":
                                LecturerService.sortLecturersBy(new EmployeeComparatorByBirthday());
                                break;
                            case "2":
                                LecturerService.sortLecturersBy(new EmployeeComparatorByBirthday().reversed());
                                break;
                            case "3":
                                System.out.println("Returning");
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        break;
                    case "3":
                        System.out.println("1. Ascending Order 2. Descending Order 3. Go To Menu");
                        System.out.print("Your input: ");
                        lastChoice = scanner.next();
                        switch (lastChoice) {
                            case "1":
                                LecturerService.sortLecturersBy(new EmployeeComparatorByName());
                                break;
                            case "2":
                                LecturerService.sortLecturersBy(new EmployeeComparatorByName().reversed());
                                break;
                            case "3":
                                System.out.println("Returning");
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        break;
                    case "4":
                        System.out.println("1. Ascending Order 2. Descending Order 3. Go To Menu");
                        System.out.print("Your input: ");
                        lastChoice = scanner.next();
                        switch (lastChoice) {
                            case "1":
                                LecturerService.sortLecturersBy(new EmployeeComparatorBySalary());
                                break;
                            case "2":
                                LecturerService.sortLecturersBy(new EmployeeComparatorBySalary().reversed());
                                break;
                            case "3":
                                System.out.println("Returning");
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        break;
                    case "5":
                        System.out.println("Returning");
                        break;
                    default:
                        System.out.println("You have chosen wrong value");
                        break;
                }
                break;
            case "2":
                System.out.println("1. By Experience 2. By Birthday 3. By Name 4. By Salary 5. Go To Menu");
                System.out.print("Your input: ");
                innerChoice = scanner.next();
                switch (innerChoice) {
                    case "1":
                        System.out.println("1. Ascending Order 2. Descending Order 3. Go To Menu");
                        System.out.print("Your input: ");
                        lastChoice = scanner.next();
                        switch (lastChoice) {
                            case "1":
                                AdministrativeEmployeeService.sortAdministrativeEmployeesBy(new EmployeeComparatorByExperience());
                                break;
                            case "2":
                                AdministrativeEmployeeService.sortAdministrativeEmployeesBy(new EmployeeComparatorByExperience().reversed());
                                break;
                            case "3":
                                System.out.println("Returning");
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        break;
                    case "2":
                        System.out.println("1. Ascending Order 2. Descending Order 3. Go To Menu");
                        System.out.print("Your input: ");
                        lastChoice = scanner.next();
                        switch (lastChoice) {
                            case "1":
                                AdministrativeEmployeeService.sortAdministrativeEmployeesBy(new EmployeeComparatorByBirthday());
                                break;
                            case "2":
                                AdministrativeEmployeeService.sortAdministrativeEmployeesBy(new EmployeeComparatorByBirthday().reversed());
                                break;
                            case "3":
                                System.out.println("Returning");
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        break;
                    case "3":
                        System.out.println("1. Ascending Order 2. Descending Order 3. Go To Menu");
                        System.out.print("Your input: ");
                        lastChoice = scanner.next();
                        switch (lastChoice) {
                            case "1":
                                AdministrativeEmployeeService.sortAdministrativeEmployeesBy(new EmployeeComparatorByName());
                                break;
                            case "2":
                                AdministrativeEmployeeService.sortAdministrativeEmployeesBy(new EmployeeComparatorByName().reversed());
                                break;
                            case "3":
                                System.out.println("Returning");
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        break;
                    case "4":
                        System.out.println("1. Ascending Order 2. Descending Order 3. Go To Menu");
                        System.out.print("Your input: ");
                        lastChoice = scanner.next();
                        switch (lastChoice) {
                            case "1":
                                AdministrativeEmployeeService.sortAdministrativeEmployeesBy(new EmployeeComparatorBySalary());
                                break;
                            case "2":
                                AdministrativeEmployeeService.sortAdministrativeEmployeesBy(new EmployeeComparatorBySalary().reversed());
                                break;
                            case "3":
                                System.out.println("Returning");
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        break;
                    case "5":
                        System.out.println("Returning");
                        break;
                    default:
                        System.out.println("You have chosen wrong value");
                        break;
                }
                break;
            case "3":
                System.out.println("Returning");
                break;
            default:
                System.out.println("Wrong Value Entered!");
                break;
        }
    }

    /**
     * Inner Menu for Employees Bonus and Tax Amount
     */
    private static void bonusAndTaxInnerChoice() {
        System.out.println("1. For Lecturers 2. For Administrative Employee 3. Go To Menu");
        System.out.print("Your input: ");
        String innerChoice = scanner.next();
        switch (innerChoice) {
            case "1":
                System.out.println("Bonus and Tax Info");
                LecturerService.printLecturerBonusAndTax();
                break;
            case "2":
                System.out.println("Bonus and Tax Info");
                AdministrativeEmployeeService.printAdministrativeEmployeeBonusAndTax();
                break;
            case "3":
                System.out.println("Returning");
                break;
            default:
                System.out.println("Wrong Value Entered!");
                break;
        }
    }

    /**
     * Inner Menu for Employees Financial Report
     */
    private static void financialReportInnerChoice() {
        System.out.println("1. For Lecturers 2. For Administrative Employee 3. Go To Menu");
        System.out.print("Your input: ");
        String innerChoice = scanner.next();
        switch (innerChoice) {
            case "1":
                System.out.println("Financial Report - Lecturers");
                LecturerService.printLecturersFinancialReport();
                break;
            case "2":
                System.out.println("Financial Report - Administrative Employees");
                AdministrativeEmployeeService.printAdministrativeEmployeesFinancialReport();
                break;
            case "3":
                System.out.println("Returning");
                break;
            default:
                System.out.println("Wrong Value Entered!");
                break;
        }
    }
}