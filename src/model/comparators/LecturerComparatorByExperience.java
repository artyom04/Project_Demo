package model.comparators;

import model.Lecturer;

import java.util.Comparator;

public class LecturerComparatorByExperience implements Comparator<Lecturer> {
    @Override
    public int compare(Lecturer o1, Lecturer o2) {
        return Double.compare(o1.getExperience(), o2.getExperience());
    }
}