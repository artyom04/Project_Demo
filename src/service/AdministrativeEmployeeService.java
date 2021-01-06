package service;

import model.AdministrativeEmployee;
import model.Employee;

import java.util.Comparator;
import java.util.LinkedHashSet;

public class AdministrativeEmployeeService extends EmployeeService {
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

    public static void createAdministrativeEmployeeAndSave() {
        createEmployeeAndSave(createAdministrativeEmployee(), "src/files/administrative_employee.txt");
    }

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

    public static void printAllAdministrativeEmployees() {
        printEmployees(getAdminEmployeesFromFile());
    }

    public static void findAdministrativeEmployeeByPassportIdAndPrint() {
        findEmployeeByPassportIdAndPrint(getAdminEmployeesFromFile());
    }

    public static void findAdministrativeEmployeesBySalaryGreaterThan() {
        findEmployeeBySalaryGreaterThan(getAdminEmployeesFromFile());
    }

    public static void findAdministrativeEmployeesByNameAndPrint() {
        findEmployeeByNameAndPrint(getAdminEmployeesFromFile());
    }

    public static <T extends Comparator<Employee>> void sortAdministrativeEmployeesBy(T comparator) {
        sortEmployeeBy(comparator, getAdminEmployeesFromFile());
    }

    public static void printAdministrativeEmployeeBonusAndTax() {
        printEmployeeBonusAndTax(getAdminEmployeesFromFile());

    }

    public static void printAdministrativeEmployeesFinancialReport() {
        printFinancialReport(getAdminEmployeesFromFile());
    }

}
