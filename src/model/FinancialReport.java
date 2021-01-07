package model;

/**
 * {@code FinancialReport} class, which allows to create Financial Reports of {@code Employee}s
 * and represent information about their salaries, bonus, and tax amount
 *
 * @author Artyom
 */
public class FinancialReport {
    /**
     * {@code Employee} for which is the {@code FinancialReport}
     */
    private Employee employee;
    /**
     * Salary
     */
    private double salary;
    /**
     * Bonus Amount
     */
    private double bonusAmount;
    /**
     * Tax Amount
     */
    private double taxAmount;

    /**
     * Constructor with parameters;
     * Creates {@code FinancialReport} from given data
     *
     * @param employee    {@code Employee} object reference
     * @param salary      parameter of type {@code double}
     * @param bonusAmount parameter of type {@code double}
     * @param taxAmount   parameter of type {@code double}
     */
    public FinancialReport(Employee employee, double salary, double bonusAmount, double taxAmount) {
        setEmployee(employee);
        setSalary(salary);
        setBonusAmount(bonusAmount);
        setTaxAmount(taxAmount);
    }

    /**
     * Sets {@code FinancialReport}s {@code Employee}
     *
     * @param employee {@code Employee} object reference
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Sets {@code FinancialReport}s Salary
     *
     * @param salary parameter of type {@code double}
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Sets {@code FinancialReport}s Bonus Amount
     *
     * @param bonusAmount parameter of type {@code double}
     */
    public void setBonusAmount(double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    /**
     * Sets {@code FinancialReport}s Tax Amount
     *
     * @param taxAmount parameter of type {@code double}
     */
    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * Returns the Employee of {@code FinancialReport}
     *
     * @return reference to object which represents the {@code Employee} of {@code FinancialReport}
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Returns the Bonus Amount of {@code FinancialReport}
     *
     * @return value which represents the {@code bonusAmount} of {@code FinancialReport}
     */
    public double getBonusAmount() {
        return bonusAmount;
    }

    /**
     * Returns the Tax Amount of {@code FinancialReport}
     *
     * @return value which represents the {@code taxAmount} of {@code FinancialReport}
     */
    public double getTaxAmount() {
        return taxAmount;
    }

    /**
     * Returns the Salary of {@code FinancialReport}
     *
     * @return value which represents the {@code salary} of {@code FinancialReport}
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @return {@code String} representation of {@code FinancialReport}
     */
    @Override
    public String toString() {
        return new StringBuilder().append(this.getEmployee().getTaxPayerID()).append(", ")
                .append(this.getEmployee().getFirstName()).append(" ").append(this.getEmployee().getLastName())
                .append(", ").append(this.getSalary()).append(", ").append(this.getBonusAmount()).append(", ")
                .append(this.getTaxAmount()).toString();
    }
}