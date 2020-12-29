package model;

import model.exceptions.InvalidInputException;
import service.FileService;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected LocalDate birthday;
    protected String identificationNumber;

    public Person() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws InvalidInputException {
        nameValidator(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws InvalidInputException {
        nameValidator(lastName);
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

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
            FileService.write("src/files/passport_id_database.txt", "\n" + identificationNumber);
            this.identificationNumber = identificationNumber;
        }
    }

    private void nameValidator(String name) throws InvalidInputException {
        if (name.isEmpty()) {
            throw new InvalidInputException("Can't be empty!", name);
        } else if (!Character.isUpperCase(name.charAt(0))) {
            throw new InvalidInputException("Should start by Uppercase Letter!", name);
        } else if (name.length() > 25 || name.length() < 2) {
            throw new InvalidInputException("Should have length at least 2, at most 25", name);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return person.identificationNumber.equals(identificationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificationNumber);
    }
}