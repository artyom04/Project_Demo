package model.exceptions;

public class InvalidInputException extends Exception {
    public <T> InvalidInputException(String message, T input) {
        super(message + ", Your input: " + input);
    }
}