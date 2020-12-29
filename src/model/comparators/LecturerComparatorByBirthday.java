package model.comparators;

import model.Lecturer;

import java.util.Comparator;

public class LecturerComparatorByBirthday implements Comparator<Lecturer> {

    @Override
    public int compare(Lecturer o1, Lecturer o2) {
        return o2.getBirthday().compareTo(o1.getBirthday());
    }
}