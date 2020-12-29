package service;

import model.Employee;
import model.FinancialReport;
import model.exceptions.InvalidInputException;
import model.exceptions.InvalidValueException;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class EmployeeService {
    private static final Scanner scanner = new Scanner(System.in);

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
                e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            boolean flag = true;
            int yearOfBirth = 0;
            while (flag) {
                System.out.print("Enter the year of birth: ");
                yearOfBirth = scanner.nextInt();
                if (yearOfBirth > 1920 && yearOfBirth < 2002) {
                    flag = false;
                } else {
                    System.out.println("Wrong value. Value should be between 1920 and 2002");
                    System.out.println("Please try again");
                }
            }
            System.out.print("Enter the month of birth: (value in [1,12]): ");
            int monthOfBirth = scanner.nextInt();
            System.out.print("Enter the day of birth: (value in [1,31]): ");
            int dayOfBirth = scanner.nextInt();
            try {
                LocalDate date = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
                employee.setBirthday(date);
                indicator = false;
            } catch (Exception e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Passport ID: ");
            String id = scanner.next();
            try {
                employee.setIdentificationNumber(id.trim());
                indicator = false;
            } catch (Exception e) {
                System.out.println("Please try again!");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Tax Payer ID: ");
            String id = scanner.next();
            try {
                employee.setTaxPayerID(id.trim());
                indicator = false;
            } catch (InvalidInputException e) {
                System.out.println("Please try again!");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Salary Amount: ");
            double salary = scanner.nextDouble();
            try {
                employee.setSalary(salary);
                indicator = false;
            } catch (InvalidValueException e) {
                System.out.println("Please try again!");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Experience: ");
            double experience = scanner.nextDouble();
            try {
                employee.setExperience(experience);
                indicator = false;
            } catch (InvalidValueException e) {
                System.out.println("Please try again!");
                e.printStackTrace();
            }
        }
        return employee;
    }

    public static <T extends Employee> void printEmployeeBonusAndTaxAmount(T employee) {
        if (employee == null) {
            System.out.println("No such an Employee!");
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
}