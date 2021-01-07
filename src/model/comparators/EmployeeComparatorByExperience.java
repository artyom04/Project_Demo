package model.comparators;

import model.Employee;

import java.util.Comparator;

/**
 * {@code EmployeeComparatorByExperience} class, which compares two {@code Employee}s by their experience
 *
 * @author Artyom
 */
public class EmployeeComparatorByExperience implements Comparator<Employee> {

    /**
     * @param o1 the first {@code Employee} to compare
     * @param o2 the second {@code Employee} to compare
     * @return the value {@code 0} if experience of {@code o1} is equal to the
     * experience of {@code o2}; a value less then {@code 0} if experience of
     * {@code o1} is less then the experience of {@code o2}; a value greater then
     * {@code 0} if experience of {@code o1} is greater then the experience of {@code o2}
     */
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o1.getExperience(), o2.getExperience());
    }
}