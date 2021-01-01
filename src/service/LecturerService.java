package service;

import model.Lecturer;
import model.exceptions.InvalidInputException;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LecturerService {
    private static final Scanner scanner = new Scanner(System.in);

    private static Lecturer createLecturer() {
        Lecturer lecturer = new Lecturer(EmployeeService.createEmployee());
        boolean indicator = true;
        while (indicator) {
            System.out.print("Please Choose Academic Degree: 1. Associate 2. Bachelors 3. Masters 4. Doctoral: ");
            int choice = scanner.nextInt();
            if (lecturer.setAcademicDegree(choice)) {
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
        Lecturer lecturer = createLecturer();
        String data = "\n" + lecturer;
        try {
            FileService.write("src/files/lecturer.txt", data);
            System.out.println("Information was successfully written to lecturer.txt file!");
            FileService.write("src/files/passport_id_database.txt", "\n" + lecturer.getIdentificationNumber());
        } catch (IOException e) {
            System.out.println("File Not Found / Can't Write");
            System.out.println(e.toString());
        }
    }

    private static LinkedHashSet<Lecturer> getLecturersSetFromFile() throws Exception {
        LinkedHashSet<Lecturer> lecturerLinkedHashSet = new LinkedHashSet<>();
        String[] lecturerInformation = FileService.read("src/files/lecturer.txt");
        for (int i = 1; i < lecturerInformation.length; i++) {
            lecturerLinkedHashSet.add(new Lecturer(lecturerInformation[i]));
        }
        return lecturerLinkedHashSet;
    }

    public static void printAllLecturers() {
        try {
            LinkedHashSet<Lecturer> lecturerLinkedHashSet = getLecturersSetFromFile();
            if (lecturerLinkedHashSet.isEmpty()) {
                System.out.println("No Lecturer!");
            } else {
                for (Lecturer lecturer : lecturerLinkedHashSet) {
                    printLecturer(lecturer);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }
    }

    private static void printLecturer(Lecturer lecturer) {
        System.out.println("Full Name: " + lecturer.getFirstName() + " " + lecturer.getLastName() +
                ", Birthday: " + lecturer.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Passport ID: " + lecturer.getIdentificationNumber() + ", Tax-Payer ID: " + lecturer.getTaxPayerID() +
                ", Salary: " + lecturer.getSalary() + ", Experience: " + lecturer.getExperience() +
                ", Academic Degree: " + lecturer.getAcademicDegree() + ", Taught Course: " + lecturer.getTaughtCourse());
    }

    public static void findLecturerByPassportIdAndPrint() {
        System.out.print("Enter the Passport ID: ");
        String passportId = scanner.next();
        try {
            LinkedHashSet<Lecturer> lecturerLinkedHashSet = getLecturersSetFromFile();
            if (lecturerLinkedHashSet.isEmpty()) {
                System.out.println("No Lecturer in Database");
            } else {
                for (Lecturer lecturer : lecturerLinkedHashSet) {
                    if (lecturer.getIdentificationNumber().equals(passportId)) {
                        printLecturer(lecturer);
                        return;
                    }
                }
                System.out.println("There are no Lecturer with such Passport ID");
            }
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }

    }

    public static void findLecturersByCourseAndPrint() {
        System.out.print("Enter the course: ");
        scanner.useDelimiter("\n");
        String course = scanner.next();
        boolean indicator = true;
        try {
            LinkedHashSet<Lecturer> lecturerLinkedHashSet = getLecturersSetFromFile();
            if (lecturerLinkedHashSet.isEmpty()) {
                System.out.println("No Lecturer in Database");
            } else {
                for (Lecturer lecturer : lecturerLinkedHashSet) {
                    if ((lecturer.getTaughtCourse()).equalsIgnoreCase(course.trim())) {
                        printLecturer(lecturer);
                        indicator = false;
                    }
                }
                if (indicator) {
                    System.out.println("There are no Lecturer with such Course");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }
    }

    public static void findLecturersBySalaryGreaterThan() {
        System.out.print("Enter the Amount: ");
        double salary = scanner.nextDouble();
        boolean indicator = true;
        try {
            LinkedHashSet<Lecturer> lecturerLinkedHashSet = getLecturersSetFromFile();
            if (lecturerLinkedHashSet.isEmpty()) {
                System.out.println("No Lecturer in Database");
            } else {
                for (Lecturer lecturer : lecturerLinkedHashSet) {
                    if (lecturer.getSalary() > salary) {
                        printLecturer(lecturer);
                        indicator = false;
                    }
                }
                if (indicator) {
                    System.out.println("There are no Lecturer with such parameter!");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }
    }

    public static void findLecturersByNameAndPrint() {
        System.out.print("Enter the first name: ");
        String firstName = scanner.next();
        System.out.print("Enter the last name: ");
        String lastName = scanner.next();
        boolean indicator = true;
        try {
            LinkedHashSet<Lecturer> lecturerLinkedHashSet = getLecturersSetFromFile();
            if (lecturerLinkedHashSet.isEmpty()) {
                System.out.println("No Lecturer in Database");
            } else {
                for (Lecturer lecturer : lecturerLinkedHashSet) {
                    if (lecturer.getFirstName().equalsIgnoreCase(firstName)) {
                        if (lecturer.getLastName().equalsIgnoreCase(lastName)) {
                            printLecturer(lecturer);
                            indicator = false;
                        }
                    }
                }
                if (indicator) {
                    System.out.println("There are no Lecturer with such parameter!");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }
    }

    public static <T extends Comparator<Lecturer>> void sortLecturersBy(T comparator) {
        try {
            LinkedHashSet<Lecturer> lecturerLinkedHashSet = getLecturersSetFromFile();
            List<Lecturer> lecturerList = new ArrayList<>(lecturerLinkedHashSet);
            lecturerList.sort(comparator);
//            Set<Lecturer> lecturerTreeSet = new TreeSet<>(comparator);
//            lecturerTreeSet.addAll(lecturerLinkedHashSet);
            if (lecturerList.isEmpty()) {
                System.out.println("No Lecturer!");
            } else {
                for (Lecturer lecturer : lecturerList) {
                    printLecturer(lecturer);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }
    }

    public static void printLecturerBonusAndTax() {
        System.out.print("Enter the Passport ID: ");
        String passportId = scanner.next();
        try {
            Lecturer lecturer1 = null;
            LinkedHashSet<Lecturer> lecturerLinkedHashSet = getLecturersSetFromFile();
            if (lecturerLinkedHashSet.isEmpty()) {
                System.out.println("No Lecturer in Database");
            } else {
                for (Lecturer lecturer : lecturerLinkedHashSet) {
                    if (lecturer.getIdentificationNumber().equals(passportId)) {
                        lecturer1 = lecturer;
                    }
                }
                EmployeeService.printEmployeeBonusAndTaxAmount(lecturer1);
            }
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }
    }

    public static void printLecturersFinancialReport() {
        try {
            EmployeeService.printFinancialReport(getLecturersSetFromFile());
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }
    }
}