package model;

import model.exceptions.InvalidInputException;
import model.exceptions.InvalidValueException;
import model.interfaces.Bonus;
import model.interfaces.TaxPaying;

import java.text.DecimalFormat;


public class Employee extends Person implements Bonus, TaxPaying {
    protected static final DecimalFormat df2 = new DecimalFormat("#.##");
    public static final double MINIMAL_SALARY = 68000;
    private String taxPayerID;
    private double salary;
    private double experience;

    public Employee() {

    }

    public Employee(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.birthday = employee.getBirthday();
        this.identificationNumber = employee.getIdentificationNumber();
        this.taxPayerID = employee.getTaxPayerID();
        this.salary = employee.getSalary();
        this.experience = employee.getExperience();
    }

    public String getTaxPayerID() {
        return taxPayerID;
    }

    public void setTaxPayerID(String taxPayerID) throws InvalidInputException {
        if (taxPayerID.matches("[A-Z]{2}[0-9]{6}")) {
            this.taxPayerID = taxPayerID;
        } else {
            throw new InvalidInputException("Tax Payer Id should be of these pattern: AA000000", taxPayerID);
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws InvalidValueException {
        if (salary >= MINIMAL_SALARY) {
            this.salary = salary;
        } else {
            String message = "Salary must be greater or equal to " + MINIMAL_SALARY;
            throw new InvalidValueException(message, salary);
        }
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) throws InvalidValueException {
        if (experience >= 0 && experience <= 50) {
            this.experience = experience;
        } else {
            throw new InvalidValueException("Experience must be in interval [0,50] ", experience);
        }
    }

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

    @Override
    public boolean isEligibleForBonus() {
        return this.getExperience() >= 1;
    }

    @Override
    public double calculateBonus() {
        if (this.isEligibleForBonus()) {
            return Double.parseDouble(df2.format(this.getSalary() * 0.3));
        } else {
            return 0;
        }
    }
}