package model.exceptions;

/**
 * Thrown by a {@code Scanner} to indicate that the token
 * retrieved does not match the pattern for the expected type, or
 * that the token is out of range for the expected type.
 *
 * @author Artyom
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs an {@code InvalidInputException}
     *
     * @param message the detail message
     * @param input   input of user
     * @param <T>     type of {@code input}
     */
    public <T> InvalidInputException(String message, T input) {
        super(message + ", Your input: " + input);
    }
}