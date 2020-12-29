package test;

import model.comparators.LecturerComparatorByBirthday;
import model.comparators.LecturerComparatorByExperience;
import model.comparators.LecturerComparatorByName;
import model.comparators.LecturerComparatorBySalary;
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
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Registering HR Manager");
                    HrManagerService.register();
                    break;
                case 2:
                    System.out.println("------------Login------------");
                    if (HrManagerService.login()) {
                        openInnerMenu();
                    }
                    break;
                case 3:
                    System.out.println("Exiting Menu");
                    isMenuActive = false;
                    break;
                default:
                    System.out.println("Wrong value chosen, please try again!");
                    break;
            }
        }
    }

    private static void openInnerMenu() {
        boolean isMenuActive = true;
        while (isMenuActive) {
            System.out.println("------------MENU------------");
            System.out.println("1. Create Lecturer");
            System.out.println("2. Print all Lecturers");
            System.out.println("3. Search Lecturer(s)");
            System.out.println("4. Sort Lecturers");
            System.out.println("5. Get Lecturer Bonus and Tax Info");
            System.out.println("6. Make Lecturers Bonus and Tax Report");
            System.out.println("7. Log out");
            System.out.print("Your input: ");
            int choice = scanner.nextInt();
            int innerChoice;
            switch (choice) {
                case 1:
                    System.out.println("--Creating Lecturer--");
                    LecturerService.createLecturerAndSave();
                    break;
                case 2:
                    System.out.println("--Printing Lecturers--");
                    LecturerService.printAllLecturers();
                    break;
                case 3:
                    System.out.println("1. By Passport ID  2. By Course  3. By Salary Greater Than  4. By Name " +
                            "5. Go Back");
                    System.out.print("Your input: ");
                    innerChoice = scanner.nextInt();
                    switch (innerChoice) {
                        case 1:
                            LecturerService.findLecturerByPassportIdAndPrint();
                            break;
                        case 2:
                            LecturerService.findLecturersByCourseAndPrint();
                            break;
                        case 3:
                            LecturerService.findLecturersBySalaryGreaterThan();
                            break;
                        case 4:
                            LecturerService.findLecturersByNameAndPrint();
                            break;
                        case 5:
                            System.out.println("Returning");
                            break;
                        default:
                            System.out.println("Wrong Value Entered!");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("1. By Experience 2. By Birthday 3. By Name 4. By Salary 5. Go Back");
                    System.out.print("Your input: ");
                    innerChoice = scanner.nextInt();
                    int lastChoice;
                    switch (innerChoice) {
                        case 1:
                            System.out.println("1. Ascending Order 2. Descending Order 3. Go Back");
                            System.out.print("Your input: ");
                            lastChoice = scanner.nextInt();
                            switch (lastChoice) {
                                case 1:
                                    LecturerService.sortLecturersBy(new LecturerComparatorByExperience());
                                    break;
                                case 2:
                                    LecturerService.sortLecturersBy(new LecturerComparatorByExperience().reversed());
                                    break;
                                case 3:
                                    System.out.println("Returning");
                                    break;
                                default:
                                    System.out.println("Wrong Value Entered!");
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("1. Ascending Order 2. Descending Order 3. Go Back");
                            System.out.print("Your input: ");
                            lastChoice = scanner.nextInt();
                            switch (lastChoice) {
                                case 1:
                                    LecturerService.sortLecturersBy(new LecturerComparatorByBirthday());
                                    break;
                                case 2:
                                    LecturerService.sortLecturersBy(new LecturerComparatorByBirthday().reversed());
                                    break;
                                case 3:
                                    System.out.println("Returning");
                                    break;
                                default:
                                    System.out.println("Wrong Value Entered!");
                                    break;
                            }
                            break;
                        case 3:
                            System.out.println("1. Ascending Order 2. Descending Order 3. Go Back");
                            System.out.print("Your input: ");
                            lastChoice = scanner.nextInt();
                            switch (lastChoice) {
                                case 1:
                                    LecturerService.sortLecturersBy(new LecturerComparatorByName());
                                    break;
                                case 2:
                                    LecturerService.sortLecturersBy(new LecturerComparatorByName().reversed());
                                    break;
                                case 3:
                                    System.out.println("Returning");
                                    break;
                                default:
                                    System.out.println("Wrong Value Entered!");
                                    break;
                            }
                            break;
                        case 4:
                            System.out.println("1. Ascending Order 2. Descending Order 3. Go Back");
                            System.out.print("Your input: ");
                            lastChoice = scanner.nextInt();
                            switch (lastChoice) {
                                case 1:
                                    LecturerService.sortLecturersBy(new LecturerComparatorBySalary());
                                    break;
                                case 2:
                                    LecturerService.sortLecturersBy(new LecturerComparatorBySalary().reversed());
                                    break;
                                case 3:
                                    System.out.println("Returning");
                                    break;
                                default:
                                    System.out.println("Wrong Value Entered!");
                                    break;
                            }
                            break;
                        case 5:
                            System.out.println("Returning");
                            break;
                        default:
                            System.out.println("You have chosen wrong value");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Bonus and Tax Info");
                    LecturerService.printLecturerBonusAndTax();
                    break;
                case 6:
                    System.out.println("Financial Report");
                    LecturerService.printLecturersFinancialReport();
                    break;
                case 7:
                    System.out.println("Logging out");
                    isMenuActive = false;
                    break;
                default:
                    System.out.println("Wrong value, please try again!");
                    break;
            }
        }
    }
}