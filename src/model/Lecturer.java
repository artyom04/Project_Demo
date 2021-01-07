package model;

import model.enums.AcademicDegree;
import model.exceptions.InvalidInputException;

/**
 * {@code Lecturer} class which extends {@code Employee} class
 *
 * @author Artyom
 */
public class Lecturer extends Employee {
    /**
     * Academic degree of {@code Lecturer}
     */
    private AcademicDegree academicDegree;

    /**
     * Course that {@code Lecturer} is teaching
     */
    private String taughtCourse;


    /**
     * Creates {@code Lecturer} from {@code Employee}
     *
     * @param employee {@code Employee} object reference
     */
    public Lecturer(Employee employee) {
        super(employee);
    }

    /**
     * Creates an {@code Lecturer} from the given data
     *
     * @param data parameter of {@code String} type; {@code data} should be a single {@code String} line,
     *             in which data should be separated by commas
     * @throws InvalidInputException will be thrown in case of invalid data in {@code data}
     */
    public Lecturer(String data) throws InvalidInputException {
        super(data);
        String[] splitData = data.split(",");
        setAcademicDegree(splitData[6].toUpperCase());
        setTaughtCourse(splitData[7]);
    }

    /**
     * Sets the Academic Degree of {@code Lecturer}
     *
     * @param academicDegree parameter of type {@code String}
     */
    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = AcademicDegree.valueOf(academicDegree);
    }

    /**
     * Returns the Academic Degree of {@code Lecturer}
     *
     * @return the {@code AcademicDegree} of {@code Lecturer}
     */
    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    /**
     * Sets the {@code AcademicDegree} of {@code Lecturer}
     *
     * @param choice parameter of type {@code String}
     * @return {@code true} if the {@code AcademicDegree} have been successfully set, {@code false} otherwise
     */
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

    /**
     * Returns the Course which {@code Lecturer} is teaching
     *
     * @return value which represents the {@code taughtCourse} of {@code Lecturer}
     */
    public String getTaughtCourse() {
        return taughtCourse;
    }

    /**
     * Sets Course which {@code Lecturer} is teaching, by ensuring that it is not empty
     *
     * @param taughtCourse parameter of type {@code String}
     * @throws InvalidInputException if {@code taughtCourse} is empty
     */
    public void setTaughtCourse(String taughtCourse) throws InvalidInputException {
        if (!taughtCourse.isEmpty()) {
            this.taughtCourse = taughtCourse;
        } else {
            throw new InvalidInputException("Can't be Empty", taughtCourse);
        }
    }

    /**
     * Prints the information of {@code Lecturer}
     */
    @Override
    public void printEmployee() {
        super.printEmployee();
        System.out.printf(", Academic Degree: %s, Taught Course: %s%n", this.getAcademicDegree(),
                this.getTaughtCourse());
    }

    /**
     * @return the bonus amount of {@code Lecturer} depending on the {@code AcademicDegree}
     */
    @Override
    public double calculateBonus() {
        if (academicDegree.getNumberOfDegree() == 4) {
            return Double.parseDouble(DECIMAL_FORMAT_2.format(this.getSalary() * 0.4));
        } else {
            return super.calculateBonus();
        }
    }

    /**
     * @return {@code String} representation of {@code Lecturer}
     */
    @Override
    public String toString() {
        return new StringBuilder().append(super.toString()).append(",")
                .append(this.getAcademicDegree()).append(",").append(this.getTaughtCourse()).toString();
    }
}