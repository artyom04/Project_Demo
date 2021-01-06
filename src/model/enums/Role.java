package model.enums;

public enum Role {
    MANAGER(1),
    OPERATOR(2),
    ASSISTANT(3);

    int numberOfRole;

    Role(int numberOfRole) {
        this.numberOfRole = numberOfRole;
    }

    public int getNumberOfRole() {
        return numberOfRole;
    }

    @Override
    public String toString() {
        String printResult = super.toString();
        return printResult.substring(0, 1).toUpperCase() + printResult.substring(1).toLowerCase();
    }
}