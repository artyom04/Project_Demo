package model.comparators;

import model.Employee;

import java.util.Comparator;

public class EmployeeComparatorByName implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1.getFirstName().compareTo(o2.getFirstName()) == 0) {
            return o1.getLastName().compareTo(o2.getLastName());
        } else {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    }
}