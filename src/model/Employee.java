package model;

import model.exceptions.InvalidInputException;
import model.interfaces.Bonus;
import model.interfaces.TaxPaying;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@code Employee} class which extends {@code Person} class and implements {@code Bonus} and
 * {@code TaxPaying} interfaces
 *
 * @author Artyom
 */
public class Employee extends Person implements Bonus, TaxPaying {
    /**
     * Allows to format decimal numbers in a way, that they have two digits after dot
     */
    protected static final DecimalFormat DECIMAL_FORMAT_2 = new DecimalFormat("#.##");

    /**
     * {@code Employee}'s minimum salary amount
     */
    public static final double MINIMAL_SALARY = 68000;
    /**
     * {@code Employee}'s tax payer ID
     */
    private String taxPayerID;
    /**
     * {@code Employee}'s salary
     */
    private double salary;
    /**
     * {@code Employee}'s experience
     */
    private double experience;

    /**
     * Default Constructor
     */
    public Employee() {

    }

    /**
     * Creates an {@code Employee} from the given data
     *
     * @param data parameter of {@code String} type; {@code data} should be a single {@code String} line,
     *             in which data should be separated by commas
     * @throws InvalidInputException will be thrown in case of invalid data in {@code data}
     */
    public Employee(String data) throws InvalidInputException {
        String[] splitData = data.split(",");
        setFirstName(splitData[0].split(" ")[0]);
        setLastName(splitData[0].split(" ")[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setBirthday(LocalDate.parse(splitData[1], formatter));
        this.identificationNumber = splitData[2];
        setTaxPayerID(splitData[3]);
        setSalary(Double.parseDouble(splitData[4]));
        setExperience(Double.parseDouble(splitData[5]));
    }

    /**
     * Creates {@code Employee} from {@code Employee}, i.e. make copy
     *
     * @param employee {@code Employee} object reference
     */
    public Employee(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.birthday = employee.getBirthday();
        this.identificationNumber = employee.getIdentificationNumber();
        this.taxPayerID = employee.getTaxPayerID();
        this.salary = employee.getSalary();
        this.experience = employee.getExperience();
    }

    /**
     * Returns the Tax Payer ID of {@code Employee}
     *
     * @return value which represent the {@code taxPayerID} of {@code Employee}
     */
    public String getTaxPayerID() {
        return taxPayerID;
    }

    /**
     * Sets {@code Employee}'s {@code taxPayerID} from given {@code String} if it satisfies to given pattern;
     * Pattern Example: AA000000
     *
     * @param taxPayerID parameter of type {@code String}
     * @throws InvalidInputException if parameter doesn't satisfy to given pattern
     */
    public void setTaxPayerID(String taxPayerID) throws InvalidInputException {
        if (taxPayerID.matches("[A-Z]{2}[0-9]{6}")) {
            this.taxPayerID = taxPayerID;
        } else {
            throw new InvalidInputException("Tax Payer Id should be of these pattern: AA000000", taxPayerID);
        }
    }

    /**
     * Returns the Salary of {@code Employee}
     *
     * @return value which represent the {@code salary} of {@code Employee}
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets {@code Employee}'s Salary if it is greater or equal to {@code MINIMAL_SALARY} amount
     *
     * @param salary parameter of type {@code double}
     * @throws InvalidInputException if {@code salary} is less than the amount of {@code MINIMAL_SALARY}
     */
    public void setSalary(double salary) throws InvalidInputException {
        if (salary >= MINIMAL_SALARY) {
            this.salary = salary;
        } else {
            String message = "Salary must be greater or equal to " + MINIMAL_SALARY;
            throw new InvalidInputException(message, salary);
        }
    }

    /**
     * Returns the Experience of {@code Employee}
     *
     * @return value which represent the {@code experience} of {@code Employee}
     */
    public double getExperience() {
        return experience;
    }

    /**
     * Sets {@code Employee}'s Experience if it is in range [0,50]
     *
     * @param experience parameter of type {@code double}
     * @throws InvalidInputException if {@code experience} is out of range [0,50]
     */
    public void setExperience(double experience) throws InvalidInputException {
        if (experience >= 0 && experience <= 50) {
            this.experience = experience;
        } else {
            throw new InvalidInputException("Experience must be in interval [0,50] ", experience);
        }
    }

    /**
     * Prints the information of {@code Employee}
     */
    public void printEmployee() {
        System.out.printf("Full Name: %s %s, Birthday: %s, Passport ID: %s, Tax-Payer ID: %s, Salary: %s, " +
                        "Experience: %s", this.getFirstName(), this.getLastName(),
                this.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                this.getIdentificationNumber(), this.getTaxPayerID(), this.getSalary(),
                this.getExperience());
    }

    /**
     * Returns the Tax Amount of {@code Employee}
     *
     * @return value which represent the tax amount of {@code Employee}
     */
    @Override
    public double calculateTaxAmount() {
        if (this.getSalary() == MINIMAL_SALARY) {
            return this.getSalary() * 5 / 100;
        } else if (this.getSalary() > MINIMAL_SALARY && this.getSalary() < 3 * MINIMAL_SALARY) {
            return this.getSalary() * 10 / 100;
        } else {
            return this.getSalary() * 20 / 100;
        }
    }

    /**
     * Determines whether {@code Employee} is eligible for bonus
     *
     * @return {@code true} if {@code experience} of {@code Employee} is greater or equal to {@code 1}
     */
    @Override
    public boolean isEligibleForBonus() {
        return this.getExperience() >= 1;
    }

    /**
     * Return the bonus amount of {@code Employee}
     *
     * @return value which represent the bonus amount of {@code Employee}
     */
    @Override
    public double calculateBonus() {
        if (this.isEligibleForBonus()) {
            return Double.parseDouble(DECIMAL_FORMAT_2.format(this.getSalary() * 0.3));
        } else {
            return 0;
        }
    }

    /**
     * @return {@code String} representation of {@code Employee}
     */
    @Override
    public String toString() {
        String formattedDate = this.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new StringBuilder().append(this.getFirstName()).append(" ").append(this.getLastName())
                .append(",").append(formattedDate).append(",").append(this.getIdentificationNumber())
                .append(",").append(this.getTaxPayerID()).append(",").append(this.getSalary()).append(",")
                .append(this.getExperience()).toString();
    }
}