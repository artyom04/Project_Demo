package service;

import model.Employee;
import model.FinancialReport;
import model.exceptions.InvalidInputException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class EmployeeService {
    protected static final Scanner scanner = new Scanner(System.in);

    public static Employee createEmployee() {
        Employee employee = new Employee();
        boolean indicator = true;
        while (indicator) {
            System.out.print("Enter the First Name: ");
            String firstName = scanner.next();
            try {
                employee.setFirstName(firstName.trim());
                indicator = false;
            } catch (InvalidInputException e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Last Name: ");
            String lastName = scanner.next();
            try {
                employee.setLastName(lastName.trim());
                indicator = false;
            } catch (InvalidInputException e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());
            }
        }
        indicator = true;
        while (indicator) {
            int yearOfBirth = 0;
            while (true) {
                try {
                    System.out.print("Enter the year of birth: (value in [1920, 2002]): ");
                    yearOfBirth = scanner.nextInt();
                    if (yearOfBirth < 1920 || yearOfBirth > 2002) {
                        throw new InvalidInputException("Value should be between 1920 and 2020", yearOfBirth);
                    }
                    break;
                } catch (InvalidInputException e) {
                    System.out.println("Please try again!");
                    System.out.println(e.toString());
                } catch (Exception e) {
                    System.out.print(e.toString());
                    System.out.println(": Should be number! Please try again");
                    scanner.next();
                }
            }
            int monthOfBirth = 0;
            while (true) {
                try {
                    System.out.print("Enter the month of birth: (value in [1,12]): ");
                    monthOfBirth = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.print(e.toString());
                    System.out.println(": Should be number! Please try again");
                    scanner.next();
                }
            }
            int dayOfBirth = 0;
            while (true) {
                try {
                    System.out.print("Enter the day of birth: (value in [1,31]): ");
                    dayOfBirth = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.print(e.toString());
                    System.out.println(": Should be number! Please try again");
                    scanner.next();
                }
            }
            try {
                LocalDate date = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
                employee.setBirthday(date);
                indicator = false;
            } catch (Exception e) {
                System.out.println("Please try again");
                System.out.println(e.toString());
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Passport ID (Ex. AP1234567): ");
            String id = scanner.next();
            try {
                employee.setIdentificationNumber(id.trim());
                indicator = false;
            } catch (Exception e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Tax Payer ID (Ex. AA123456): ");
            String id = scanner.next();
            try {
                employee.setTaxPayerID(id.trim());
                indicator = false;
            } catch (InvalidInputException e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());

            }
        }
        indicator = true;
        while (indicator) {
            try {
                System.out.print("Enter the Salary Amount: ");
                double salary = scanner.nextDouble();
                employee.setSalary(salary);
                indicator = false;
            } catch (InvalidInputException e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());
            } catch (Exception e) {
                System.out.print(e.toString());
                System.out.println(": Should be number! Please try again");
                scanner.next();
            }
        }
        indicator = true;
        while (indicator) {
            try {
                System.out.print("Enter the Experience: ");
                double experience = scanner.nextDouble();
                employee.setExperience(experience);
                indicator = false;
            } catch (InvalidInputException e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());
            } catch (Exception e) {
                System.out.print(e.toString());
                System.out.println(": Should be number! Please try again");
                scanner.next();
            }
        }
        return employee;
    }

    private static <T extends Employee> LinkedHashMap<String, FinancialReport>
    makeFinancialReportMap(LinkedHashSet<T> employees) {
        LinkedHashMap<String, FinancialReport> myFinancialReport = new LinkedHashMap<>();
        for (T employee : employees) {
            FinancialReport financialReport = new FinancialReport(employee, employee.getSalary(),
                    employee.calculateBonus(), employee.calculateTaxAmount());
            myFinancialReport.put(employee.getIdentificationNumber(), financialReport);
        }
        return myFinancialReport;
    }

    private static void printMap(LinkedHashMap<String, FinancialReport> myMap) {
        for (String key : myMap.keySet()) {
            System.out.println(myMap.get(key));
        }
    }

    public static <T extends Employee> void printFinancialReport(LinkedHashSet<T> employees) {
        System.out.println("Full Name, Passport ID, Salary, Bonus Amount, Tax Amount");
        printMap(makeFinancialReportMap(employees));
    }

    public static void createEmployeeAndSave(Employee employee, String path) {
        String data = "\n" + employee;
        try {
            FileService.write(path, data);
            Path p = Paths.get(path);
            String fileName = p.getFileName().toString();
            System.out.println("Information was successfully written to " + fileName + " file!");
            FileService.write("src/files/passport_id_database.txt", "\n" +
                    employee.getIdentificationNumber());
        } catch (IOException e) {
            System.out.println("File Not Found / Can't Write");
            System.out.println(e.toString());
        }
    }

    public static <T extends Employee> void printEmployees(LinkedHashSet<T> employeeLinkedHashSet) {
        if (employeeLinkedHashSet.isEmpty()) {
            System.out.println("Empty!");
        } else {
            for (T employee : employeeLinkedHashSet) {
                employee.printEmployee();
            }
        }
    }

    public static <T extends Employee> void
    findEmployeeByPassportIdAndPrint(LinkedHashSet<T> employeeLinkedHashSet) {
        System.out.print("Enter the Passport ID: ");
        String passportId = scanner.next();
        if (employeeLinkedHashSet.isEmpty()) {
            System.out.println("Empty!");
        } else {
            for (T employee : employeeLinkedHashSet) {
                if (employee.getIdentificationNumber().equals(passportId)) {
                    employee.printEmployee();
                    return;
                }
            }
            System.out.println("No one found with such Passport ID");
        }
    }

    public static <T extends Employee> void
    findEmployeeBySalaryGreaterThan(LinkedHashSet<T> employeeLinkedHashSet) {
        double salary = 0;
        while(true) {
            try {
                System.out.print("Enter the Amount: ");
                salary = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.print(e.toString());
                System.out.println(": Should be number, Please try again!");
                scanner.next();
            }
        }
        boolean indicator = true;
        if (employeeLinkedHashSet.isEmpty()) {
            System.out.println("Empty!");
        } else {
            for (T employee : employeeLinkedHashSet) {
                if (employee.getSalary() > salary) {
                    employee.printEmployee();
                    indicator = false;
                }
            }
            if (indicator) {
                System.out.println("No one with such parameter!");
            }
        }
    }

    public static <T extends Employee> void findEmployeeByNameAndPrint(LinkedHashSet<T> employeeLinkedHashSet) {
        System.out.print("Enter the first name: ");
        String firstName = scanner.next();
        System.out.print("Enter the last name: ");
        String lastName = scanner.next();
        boolean indicator = true;
        if (employeeLinkedHashSet.isEmpty()) {
            System.out.println("Empty!");
        } else {
            for (T employee : employeeLinkedHashSet) {
                if (employee.getFirstName().equalsIgnoreCase(firstName)) {
                    if (employee.getLastName().equalsIgnoreCase(lastName)) {
                        employee.printEmployee();
                        indicator = false;
                    }
                }
            }
            if (indicator) {
                System.out.println("No one with such parameter!");
            }
        }
    }

    public static <T extends Comparator<Employee>, E extends Employee>
    void sortEmployeeBy(T comparator, LinkedHashSet<E> employeeLinkedHashSet) {
        List<E> employeeList = new ArrayList<>(employeeLinkedHashSet);
        employeeList.sort(comparator);
        if (employeeList.isEmpty()) {
            System.out.println("Empty!");
        } else {
            for (E employee : employeeList) {
                employee.printEmployee();
            }
        }
    }

    public static <T extends Employee> void printEmployeeBonusAndTax(LinkedHashSet<T> employeeLinkedHashSet) {
        System.out.print("Enter the Passport ID: ");
        String passportId = scanner.next();
        T employee1 = null;
        if (employeeLinkedHashSet.isEmpty()) {
            System.out.println("Empty!");
        } else {
            for (T employee : employeeLinkedHashSet) {
                if (employee.getIdentificationNumber().equals(passportId)) {
                    employee1 = employee;
                }
            }
            printEmployeeBonusAndTaxAmount(employee1);
        }
    }

    private static <T extends Employee> void printEmployeeBonusAndTaxAmount(T employee) {
        if (employee == null) {
            System.out.println("No Information!");
        } else {
            String employeeData = employee.getFirstName() + " " + employee.getLastName() + ", Passport Id: " +
                    employee.getIdentificationNumber();
            if (employee.isEligibleForBonus()) {
                System.out.println(employeeData + ": Bonus is: " + employee.calculateBonus());
            } else {
                System.out.println("Not Eligible for Bonus!");
            }
            System.out.println(employeeData + ": Tax Amount is: " + employee.calculateTaxAmount());
        }
    }
}