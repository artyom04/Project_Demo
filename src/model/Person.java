package model;

import model.exceptions.InvalidInputException;
import service.FileService;

import java.time.LocalDate;
import java.util.Objects;

/**
 * {@code Person} abstract class
 *
 * @author Artyom
 */
public abstract class Person {

    /**
     * First Name of {@code Person}
     */
    protected String firstName;

    /**
     * Last Name of {@code Person}
     */
    protected String lastName;

    /**
     * Birthday of {@code Person}
     */
    protected LocalDate birthday;

    /**
     * Passport ID of {@code Person}
     */
    protected String identificationNumber;

    /**
     * Returns the First Name of {@code Person}
     *
     * @return the value which represents the {@code firstName} of {@code Person}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the {@code Person}'s First Name, if it isn't empty, contains only letters, first letter is uppercase
     * has minimum length of 2 characters and maximum length of 25 characters
     *
     * @param firstName parameter of type {@code String}
     * @throws InvalidInputException if {@code firstName} is empty, or it doesn't contain only letters, or
     *                               first letter is not uppercase, or the length is not in the range of [2,25] characters
     */
    public void setFirstName(String firstName) throws InvalidInputException {
        nameValidator(firstName);
        this.firstName = firstName;
    }


    /**
     * Returns the Last Name of {@code Person}
     *
     * @return the value which represents the {@code lastName} of {@code Person}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the {@code Person}'s Last Name, if it isn't empty, contains only letters, first letter is uppercase
     * has minimum length of 2 characters and maximum length of 25 characters
     *
     * @param lastName parameter of type {@code String}
     * @throws InvalidInputException if {@code lastName} is empty, or it doesn't contain only letters, or
     *                               first letter is not uppercase, or the length is not in the range of [2,25] characters
     */
    public void setLastName(String lastName) throws InvalidInputException {
        nameValidator(lastName);
        this.lastName = lastName;
    }

    /**
     * Returns the BirthDay of {@code Person}
     *
     * @return the value which represents the {@code birthDay} of {@code Person}
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Sets the Birthday of {@code Person}
     *
     * @param birthday parameter of type {@code LocalDate}
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Returns the Passport ID of {@code Person}
     *
     * @return the value which represents the {@code identificationNumber} of {@code Person}
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Sets the Passport ID of {@code Person} if it is unique and satisfies the following pattern: AA0000000
     *
     * @param identificationNumber parameter of type {@code String}
     * @throws Exception if {@code identificationNumber} is not unique, or doesn't satisfy the following
     *                   pattern: AA0000000, or can't read/write to file
     */
    public void setIdentificationNumber(String identificationNumber) throws Exception {

        if (!identificationNumber.matches("[A-Z]{2}[0-9]{7}")) {
            throw new InvalidInputException("Should be of this pattern: AA0000000", identificationNumber);
        } else {
            String[] data = FileService.read("src/files/passport_id_database.txt");
            for (int i = 1; i < data.length; i++) {
                if (data[i].equals(identificationNumber)) {
                    throw new InvalidInputException("There is a person with the same ID, Passport ID must be unique!",
                            identificationNumber);
                }
            }
            this.identificationNumber = identificationNumber;
        }
    }

    /**
     * Validates the Name, in order to make sure that it is not empty, contains only letters, the first letter
     * is uppercase, and has the length in the range of [2,25] characters
     *
     * @param name parameter of type {@code String}
     * @throws InvalidInputException if {@code name} is empty, or it doesn't contain only letters,
     *                               or the first letter is not uppercase, or the length is not in the range
     *                               of [2,25] characters
     */
    private void nameValidator(String name) throws InvalidInputException {
        if (name.isEmpty()) {
            throw new InvalidInputException("Can't be empty!", name);
        } else if (!Character.isUpperCase(name.charAt(0))) {
            throw new InvalidInputException("Should start by Uppercase Letter!", name);
        } else if (name.length() > 25 || name.length() < 2) {
            throw new InvalidInputException("Should have length at least 2 characters, at most 25 characters", name);
        } else if (!containOnlyLetters(name)) {
            throw new InvalidInputException("Should contain only letters", name);
        }
    }

    /**
     * Validates the text to make sure that it contains only letters
     *
     * @param text parameter of type {@code String}
     * @return {@code true} if {@code text} contains only letters, {@code false} otherwise
     */
    private boolean containOnlyLetters(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isLetter(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checked whether two {@code Person}'s are equal. They are equal if their {@code identificationNumber}'s
     * are the same
     *
     * @param o the object to be compared with
     * @return {@code true} if the object is the same as {@code Person} object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return person.identificationNumber.equals(identificationNumber);
    }

    /**
     * Returns the hash code value for this object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(identificationNumber);
    }
}