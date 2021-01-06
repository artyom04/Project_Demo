package model.comparators;

import model.Employee;

import java.util.Comparator;

public class EmployeeComparatorByBirthday implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o2.getBirthday().compareTo(o1.getBirthday());
    }
}