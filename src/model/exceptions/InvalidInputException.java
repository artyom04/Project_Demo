package model.exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message, String input) {
        super(message + ", Your input: " + input);
    }
}