package model.comparators;

import model.Lecturer;

import java.util.Comparator;

public class LecturerComparatorBySalary implements Comparator<Lecturer> {
    @Override
    public int compare(Lecturer o1, Lecturer o2) {
        return Double.compare(o1.getSalary(), o2.getSalary());

    }
}