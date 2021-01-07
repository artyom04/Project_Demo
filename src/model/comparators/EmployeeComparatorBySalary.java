package model.comparators;

import model.Employee;

import java.util.Comparator;

/**
 * {@code EmployeeComparatorBySalary} class, which compares two {@code Employee}s by their salaries
 *
 * @author Artyom
 */

public class EmployeeComparatorBySalary implements Comparator<Employee> {

    /**
     * @param o1 the first {@code Employee} to compare
     * @param o2 the second {@code Employee} to compare
     * @return the value {@code 0} if experience of {@code o1} is equal to the
     * salary of {@code o2}; a value less then {@code 0} if salary of
     * {@code o1} is less then the salary of {@code o2}; a value greater then
     * {@code 0} if salary of {@code o1} is greater then the salary of {@code o2}
     */
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o1.getSalary(), o2.getSalary());
    }
}