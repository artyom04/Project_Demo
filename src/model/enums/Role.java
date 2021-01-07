package model.enums;

/**
 * {@code Role} enum, which holds Role values
 *
 * @author Artyom
 */
public enum Role {
    /**
     * Manager
     */
    MANAGER(1),
    /**
     * Operator
     */
    OPERATOR(2),
    /**
     * Assistant
     */
    ASSISTANT(3);

    /**
     * Integer corresponding to {@code Role} values
     * {@code 1} for {@code MANAGER}, {@code 2} for {@code OPERATOR}, {@code 3} for {@code ASSISTANT}
     */
    int numberOfRole;

    /**
     * Constructor
     *
     * @param numberOfRole the number of role
     */
    Role(int numberOfRole) {
        this.numberOfRole = numberOfRole;
    }

    /**
     * @return {@code numberOfRole} corresponding to {@code Role} value
     */
    public int getNumberOfRole() {
        return numberOfRole;
    }

    /**
     * @return {@code String} representation of {@code Role} value, first letter is uppercase, then lowercase.
     * Example: Manager
     */
    @Override
    public String toString() {
        String printResult = super.toString();
        return new StringBuilder().append(printResult.substring(0, 1).toUpperCase())
                .append(printResult.substring(1).toLowerCase()).toString();
    }
}