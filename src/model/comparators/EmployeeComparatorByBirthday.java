package model.comparators;

import model.Employee;

import java.util.Comparator;

/**
 * {@code EmployeeComparatorByBirthday} class, which compares two {@code Employee}s by their birthday
 *
 * @author Artyom
 */
public class EmployeeComparatorByBirthday implements Comparator<Employee> {

    /**
     * @param o1 the first {@code Employee} to compare
     * @param o2 the second {@code Employee} to compare
     * @return the value {@code 0} if {@code o1} born at the same date as {@code o2};
     * a value less then {@code 0} if {@code o1} is younger than {@code o2};
     * a value greater then {@code 0} if {@code o1} is older than {@code o2}
     */
    @Override
    public int compare(Employee o1, Employee o2) {
        return o2.getBirthday().compareTo(o1.getBirthday());
    }
}