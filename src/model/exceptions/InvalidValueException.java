package model.exceptions;

public class InvalidValueException extends Exception {
    public <T> InvalidValueException(String message, T value) {
        super(message + ", Your input: " + value);
    }
}