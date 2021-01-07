package service;

import model.AdministrativeEmployee;
import model.Employee;

import java.util.Comparator;
import java.util.LinkedHashSet;

/**
 * {@code AdministrativeEmployeeService} class which extends {@code EmployeeService} class;
 * Contains functions which manipulate with {@code AdministrativeEmployee} class objects
 *
 * @author Artyom
 */
public class AdministrativeEmployeeService extends EmployeeService {

    /**
     * Creates {@code AdministrativeEmployee} instance
     *
     * @return {@code AdministrativeEmployee} object
     */
    private static AdministrativeEmployee createAdministrativeEmployee() {
        AdministrativeEmployee administrativeEmployee = new AdministrativeEmployee(createEmployee());
        boolean indicator = true;
        while (indicator) {
            System.out.print("Please Choose Role: 1. Manager 2. Operator 3. Assistant: ");
            String choice = scanner.next();
            if (administrativeEmployee.setRoleByValue(choice)) {
                indicator = false;
            } else {
                System.out.println("You have chosen wrong value! Please try again");
            }
        }
        return administrativeEmployee;
    }

    /**
     * Creates {@code AdministrativeEmployee} instance and saves to file
     */
    public static void createAdministrativeEmployeeAndSave() {
        createEmployeeAndSave(createAdministrativeEmployee(), "src/files/administrative_employee.txt");
    }

    /**
     * Reads from file and creates LinkedHashSet of {@code AdministrativeEmployee}s
     *
     * @return LinkedHashSet of {@code AdministrativeEmployee}s
     */
    public static LinkedHashSet<AdministrativeEmployee> getAdminEmployeesFromFile() {
        LinkedHashSet<AdministrativeEmployee> administrativeEmployeeLinkedHashSet = new LinkedHashSet<>();
        try {
            String[] adminEmployeeInformation = FileService.read("src/files/administrative_employee.txt");
            for (int i = 1; i < adminEmployeeInformation.length; i++) {
                administrativeEmployeeLinkedHashSet.add(new AdministrativeEmployee(adminEmployeeInformation[i]));
            }
        } catch (Exception e) {
            System.out.println("Can't Read/ Invalid Data");
            System.out.println(e.toString());
        }
        return administrativeEmployeeLinkedHashSet;
    }

    /**
     * Prints all {@code AdministrativeEmployee}s
     */
    public static void printAllAdministrativeEmployees() {
        printEmployees(getAdminEmployeesFromFile());
    }

    /**
     * Finds {@code AdministrativeEmployee} by Passport ID and Prints
     */
    public static void findAdministrativeEmployeeByPassportIdAndPrint() {
        findEmployeeByPassportIdAndPrint(getAdminEmployeesFromFile());
    }

    /**
     * Finds {@code AdministrativeEmployee} by Salary Greater Than Given Value and Prints
     */
    public static void findAdministrativeEmployeesBySalaryGreaterThan() {
        findEmployeeBySalaryGreaterThan(getAdminEmployeesFromFile());
    }

    /**
     * Finds {@code AdministrativeEmployee} by Full Name and Prints
     */
    public static void findAdministrativeEmployeesByNameAndPrint() {
        findEmployeeByNameAndPrint(getAdminEmployeesFromFile());
    }

    /**
     * Sorts {@code AdministrativeEmployee}s by given Comparator
     *
     * @param comparator parameter {@code Comparator}
     * @param <T>        type of {@code Comparator}
     */
    public static <T extends Comparator<Employee>> void sortAdministrativeEmployeesBy(T comparator) {
        sortEmployeeBy(comparator, getAdminEmployeesFromFile());
    }

    /**
     * Prints {@code AdministrativeEmployee}'s Bonus and Tax Amount Information
     */
    public static void printAdministrativeEmployeeBonusAndTax() {
        printEmployeeBonusAndTax(getAdminEmployeesFromFile());
    }

    /**
     * Prints {@code AdministrativeEmployee}s Financial Report
     */
    public static void printAdministrativeEmployeesFinancialReport() {
        printFinancialReport(getAdminEmployeesFromFile());
    }
}