package model;

import model.enums.AcademicDegree;
import model.exceptions.InvalidInputException;

public class Lecturer extends Employee {
    private AcademicDegree academicDegree;
    private String taughtCourse;

    public Lecturer(Employee employee) {
        super(employee);
    }

    public Lecturer(String data) throws Exception {
        super(data);
        String[] splitData = data.split(",");
        setAcademicDegree(splitData[6].toUpperCase());
        setTaughtCourse(splitData[7]);
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = AcademicDegree.valueOf(academicDegree);
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public boolean setAcademicDegreeByValue(String choice) {
        boolean indicator;
        switch (choice) {
            case "1":
                academicDegree = AcademicDegree.ASSOCIATE;
                indicator = true;
                break;
            case "2":
                academicDegree = AcademicDegree.BACHELORS;
                indicator = true;
                break;
            case "3":
                academicDegree = AcademicDegree.MASTERS;
                indicator = true;
                break;
            case "4":
                academicDegree = AcademicDegree.DOCTORAL;
                indicator = true;
                break;
            default:
                indicator = false;
                break;
        }
        return indicator;
    }

    public String getTaughtCourse() {
        return taughtCourse;
    }

    public void setTaughtCourse(String taughtCourse) throws InvalidInputException {
        if (!taughtCourse.isEmpty()) {
            this.taughtCourse = taughtCourse;
        } else {
            throw new InvalidInputException("Can't be Empty", taughtCourse);
        }
    }

    @Override
    public void printEmployee() {
        super.printEmployee();
        System.out.println(", Academic Degree: " + this.getAcademicDegree() + ", Taught Course: " + this.getTaughtCourse());
    }

    @Override
    public double calculateBonus() {
        if (academicDegree.getNumberOfDegree() == 4) {
            return Double.parseDouble(DECIMAL_FORMAT_2.format(this.getSalary() * 0.4));
        } else {
            return super.calculateBonus();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.getAcademicDegree() + "," + this.getTaughtCourse();
    }
}