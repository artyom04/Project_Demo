package model;

import model.exceptions.InvalidInputException;
import model.interfaces.Bonus;
import model.interfaces.TaxPaying;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Employee extends Person implements Bonus, TaxPaying {
    protected static final DecimalFormat DECIMAL_FORMAT_2 = new DecimalFormat("#.##");
    public static final double MINIMAL_SALARY = 68000;
    private String taxPayerID;
    private double salary;
    private double experience;

    public Employee() {

    }

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

    public void setSalary(double salary) throws InvalidInputException {
        if (salary >= MINIMAL_SALARY) {
            this.salary = salary;
        } else {
            String message = "Salary must be greater or equal to " + MINIMAL_SALARY;
            throw new InvalidInputException(message, salary);
        }
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) throws InvalidInputException {
        if (experience >= 0 && experience <= 50) {
            this.experience = experience;
        } else {
            throw new InvalidInputException("Experience must be in interval [0,50] ", experience);
        }
    }

    public void printEmployee() {
        System.out.print("Full Name: " + this.getFirstName() + " " + this.getLastName() +
                ", Birthday: " + this.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Passport ID: " + this.getIdentificationNumber() + ", Tax-Payer ID: " + this.getTaxPayerID() +
                ", Salary: " + this.getSalary() + ", Experience: " + this.getExperience());
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
            return Double.parseDouble(DECIMAL_FORMAT_2.format(this.getSalary() * 0.3));
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        String formattedDate = this.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return this.getFirstName() + " " + this.getLastName() + "," + formattedDate + "," +
                this.getIdentificationNumber() + "," + this.getTaxPayerID() + "," + this.getSalary() + "," +
                this.getExperience();
    }
}