package model.enums;

/**
 * {@code AcademicDegree} enum, which holds Academic Degree values
 *
 * @author Artyom
 */

public enum AcademicDegree {
    /**
     * Associate Degree
     */
    ASSOCIATE(1),

    /**
     * Bachelors Degree
     */
    BACHELORS(2),

    /**
     * Masters Degree
     */
    MASTERS(3),

    /**
     * Doctoral Degree
     */
    DOCTORAL(4);

    /**
     * Integer corresponding to {@code AcademicDegree} values
     * {@code 1} for {@code ASSOCIATE}, {@code 2} for {@code BACHELORS}, {@code 3} for {@code DOCTORAL},
     * {@code 4} for {@code DOCTORAL}
     */
    int numberOfDegree;

    /**
     * Constructor
     *
     * @param numberOfDegree the number of degree
     */
    AcademicDegree(int numberOfDegree) {
        this.numberOfDegree = numberOfDegree;
    }

    /**
     * @return {@code numberOfDegree} corresponding to {@code AcademicDegree} value
     */
    public int getNumberOfDegree() {
        return numberOfDegree;
    }

    /**
     * @return {@code String} representation of {@code AcademicDegree} value, first letter is uppercase,
     * then lowercase.
     * Example: Bachelors
     */
    @Override
    public String toString() {
        String printResult = super.toString();
        return new StringBuilder().append(printResult.substring(0, 1).toUpperCase())
                .append(printResult.substring(1).toLowerCase()).toString();
    }
}