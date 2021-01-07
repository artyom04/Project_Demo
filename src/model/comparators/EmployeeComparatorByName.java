package model.comparators;

import model.Employee;

import java.util.Comparator;

/**
 * {@code EmployeeComparatorByName} class, which compares two {@code Employee}s by their full names
 *
 * @author Artyom
 */
public class EmployeeComparatorByName implements Comparator<Employee> {

    /**
     * @param o1 the first {@code Employee} to compare
     * @param o2 the second {@code Employee} to compare
     * @return the value {@code 0} if both first name and last name of {@code o1} is equal
     * to the first name and last name of {@code o2} respectively; a value greater then {@code 0} if the
     * first name of {@code o1} is lexicographically greater than the first name of {@code o2}, or when
     * the first names of {@code o1} and {@code o2} are equals, then if the last name of {@code o1} is
     * lexicographically greater than the last name of {@code o2}; a value less then {@code 0} if the
     * first name of {@code o1} is lexicographically less than the first name of {@code o2}, or when the
     * first names of {@code o1} and {@code o2} are equals, then if the last name of {@code o1} is
     * lexicographically less than the last name of {@code o2}
     */

    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1.getFirstName().compareTo(o2.getFirstName()) == 0) {
            return o1.getLastName().compareTo(o2.getLastName());
        } else {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    }
}