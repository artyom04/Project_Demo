package model;

import model.enums.AcademicDegree;
import model.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Lecturer extends Employee {
    private AcademicDegree academicDegree;
    private String taughtCourse;

    public Lecturer(Employee employee) {
        super(employee);
    }

    public Lecturer(String data) throws Exception {
        String[] splitData = data.split(",");
        setFirstName(splitData[0].split(" ")[0]);
        setLastName(splitData[0].split(" ")[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setBirthday(LocalDate.parse(splitData[1], formatter));
        this.identificationNumber = splitData[2];
        setTaxPayerID(splitData[3]);
        setSalary(Double.parseDouble(splitData[4]));
        setExperience(Double.parseDouble(splitData[5]));
        setAcademicDegree(splitData[6].toUpperCase());
        setTaughtCourse(splitData[7]);
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = AcademicDegree.valueOf(academicDegree);
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public boolean setAcademicDegree(int choice) {
        boolean indicator;
        switch (choice) {
            case 1:
                academicDegree = AcademicDegree.ASSOCIATE;
                indicator = true;
                break;
            case 2:
                academicDegree = AcademicDegree.BACHELORS;
                indicator = true;
                break;
            case 3:
                academicDegree = AcademicDegree.MASTERS;
                indicator = true;
                break;
            case 4:
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
    public double calculateBonus() {
        if (academicDegree.getNumberOfDegree() == 4) {
            return Double.parseDouble(df2.format(this.getSalary() * 0.4));
        } else {
            return super.calculateBonus();
        }
    }

    @Override
    public String toString() {
        String formattedDate = this.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return this.getFirstName() + " " + this.getLastName() + "," + formattedDate + "," +
                this.getIdentificationNumber() + "," + this.getTaxPayerID() + "," + this.getSalary() + "," +
                this.getExperience() + "," + this.getAcademicDegree() + "," + this.getTaughtCourse();
    }
}