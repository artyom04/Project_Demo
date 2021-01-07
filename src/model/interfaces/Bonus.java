package model.interfaces;

/**
 * {@code Bonus} interface which has two functions
 *
 * @author Artyom
 */
public interface Bonus {
    /**
     * Function determines whether the object is eligible for bonus or not
     *
     * @return {@code true} if object is eligible for bonus, {@code false} if not
     */
    boolean isEligibleForBonus();

    /**
     * Calculates the amount of bonus
     *
     * @return the amount of calculated bonus
     */
    double calculateBonus();
}