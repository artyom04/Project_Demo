package model;

import model.enums.Role;
import model.exceptions.InvalidInputException;

/**
 * The {@code AdministrativeEmployee} class which extends the {@code Employee} class, and which allows us
 * to create Administrative Employee.
 *
 * @author Artyom
 */
public class AdministrativeEmployee extends Employee {
    /**
     * {@code Role} of {@code AdministrativeEmployee}
     */
    private Role role;

    /**
     * Creates {@code AdministrativeEmployee} from {@code Employee}
     *
     * @param employee {@code Employee} object reference
     */
    public AdministrativeEmployee(Employee employee) {
        super(employee);
    }

    /**
     * Creates an {@code AdministrativeEmployee} from the given data
     *
     * @param data parameter of {@code String} type; {@code data} should be a single {@code String} line,
     *             in which data should be separated by commas
     * @throws InvalidInputException will be thrown in case of invalid data in {@code data}
     */
    public AdministrativeEmployee(String data) throws InvalidInputException {
        super(data);
        String[] splitData = data.split(",");
        setRole(splitData[6].toUpperCase());
    }

    /**
     * @return the {@code Role} of {@code AdministrativeEmployee}
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the {@code Role} of {@code AdministrativeEmployee}
     *
     * @param choice parameter of type {@code String}
     * @return {@code true} if the {@code Role} have been successfully set, {@code false} otherwise
     */
    public boolean setRoleByValue(String choice) {
        boolean indicator;
        switch (choice) {
            case "1":
                role = Role.MANAGER;
                indicator = true;
                break;
            case "2":
                role = Role.OPERATOR;
                indicator = true;
                break;
            case "3":
                role = Role.ASSISTANT;
                indicator = true;
                break;
            default:
                indicator = false;
                break;
        }
        return indicator;
    }

    /**
     * Sets the {@code Role} of {@code AdministrativeEmployee} from {@code String} data
     *
     * @param role parameter of type {@code String}
     */
    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    /**
     * Prints the information of {@code AdministrativeEmployee}
     */
    @Override
    public void printEmployee() {
        super.printEmployee();
        System.out.printf(", Role: %s%n", this.getRole());
    }

    /**
     * @return the bonus amount of {@code AdministrativeEmployee} depending on the {@code Role}
     */
    @Override
    public double calculateBonus() {
        if (role.getNumberOfRole() == 1) {
            return Double.parseDouble(DECIMAL_FORMAT_2.format(this.getSalary() * 0.4));
        } else {
            return super.calculateBonus();
        }
    }

    /**
     * @return {@code String} representation of {@code AdministrativeEmployee}
     */
    @Override
    public String toString() {
        return new StringBuilder().append(super.toString()).append(",").append(this.getRole()).toString();
    }
}