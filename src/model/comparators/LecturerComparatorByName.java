package model.comparators;

import model.Lecturer;

import java.util.Comparator;

public class LecturerComparatorByName implements Comparator<Lecturer> {

    @Override
    public int compare(Lecturer o1, Lecturer o2) {
        if (o1.getFirstName().compareTo(o2.getFirstName()) == 0) {
            return o1.getLastName().compareTo(o2.getLastName());
        } else {
            return o1.getFirstName().compareTo(o2.getLastName());
        }
    }
}