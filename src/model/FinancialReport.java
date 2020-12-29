package model;

public class FinancialReport {
    private Employee employee;
    private double salary;
    private double bonusAmount;
    private double taxAmount;

    public FinancialReport(Employee employee, double salary, double bonusAmount, double taxAmount) {
        setEmployee(employee);
        setSalary(salary);
        setBonusAmount(bonusAmount);
        setTaxAmount(taxAmount);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBonusAmount(double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getBonusAmount() {
        return bonusAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return this.getEmployee().getTaxPayerID() + ", " + this.getEmployee().getFirstName() + " " +
                this.getEmployee().getLastName() + ", " + this.getSalary() + ", " + this.getBonusAmount() + ", " +
                this.getTaxAmount();
    }
}