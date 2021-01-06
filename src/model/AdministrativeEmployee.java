package model;

import model.enums.Role;

public class AdministrativeEmployee extends Employee {
    private Role role;

    public AdministrativeEmployee(Employee employee) {
        super(employee);
    }

    public AdministrativeEmployee(String data) throws Exception {
        super(data);
        String[] splitData = data.split(",");
        setRole(splitData[6].toUpperCase());
    }

    public Role getRole() {
        return role;
    }

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

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    @Override
    public void printEmployee() {
        super.printEmployee();
        System.out.println(", Role: " + this.getRole());
    }

    @Override
    public double calculateBonus() {
        if (role.getNumberOfRole() == 1) {
            return Double.parseDouble(DECIMAL_FORMAT_2.format(this.getSalary() * 0.4));
        } else {
            return super.calculateBonus();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.getRole();
    }
}