package model.enums;

public enum AcademicDegree {
    ASSOCIATE(1),
    BACHELORS(2),
    MASTERS(3),
    DOCTORAL(4);

    int numberOfDegree;

    AcademicDegree(int numberOfDegree) {
        this.numberOfDegree = numberOfDegree;
    }

    public int getNumberOfDegree() {
        return numberOfDegree;
    }

    @Override
    public String toString() {
        String printResult = super.toString();
        return printResult.substring(0, 1).toUpperCase() + printResult.substring(1).toLowerCase();
    }
}