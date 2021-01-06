package model.comparators;

import model.Employee;

import java.util.Comparator;

public class EmployeeComparatorByExperience implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o1.getExperience(), o2.getExperience());
    }
}