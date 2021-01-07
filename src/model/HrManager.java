package model;

import model.exceptions.InvalidInputException;
import service.EncryptionService;
import service.HrManagerService;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code HrManager} class which extends {@code Employee} class
 *
 * @author Artyom
 */
public class HrManager extends Employee {

    /**
     * Email verification pattern
     */
    private static final String EMAIL_VERIFICATION = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    /**
     * username of {@code HrManager}
     */
    private String username;

    /**
     * email of {@code HrManager}
     */
    private String email;

    /**
     * password of {@code HrManager}
     */
    private String password;


    /**
     * Creates an {@code HrManager} from the given data
     *
     * @param data parameter of {@code String} type; {@code data} should be a single {@code String} line,
     *             in which data should be separated by commas
     * @throws InvalidInputException will be thrown in case of invalid data in {@code data}
     */
    public HrManager(String data) throws InvalidInputException {
        super(data);
        String[] splitData = data.split(",");
        this.username = splitData[6];
        this.email = splitData[7];
        this.password = splitData[8];
    }

    /**
     * Creates {@code HrManager} from {@code Employee}
     *
     * @param employee {@code Employee} object reference
     */
    public HrManager(Employee employee) {
        super(employee);
    }

    /**
     * Returns Username of {@code HrManager}
     *
     * @return value which represents the {@code username} of {@code HrManager}
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the Username of {@code HrManager} if it has at least 11 characters and is unique
     *
     * @param username parameter of type {@code String}
     * @throws Exception if {@code username} is already taken, or it has length less than 11 characters, or
     *                   can't read from file
     */
    public void setUsername(String username) throws Exception {
        ArrayList<HrManager> hrManagers = HrManagerService.getHrManagersFromFile();
        if (username.length() <= 10) {
            throw new InvalidInputException("Username should have at least 11 characters!", username);
        }
        for (HrManager hrManager : hrManagers) {
            if (hrManager.getUsername().equals(username)) {
                throw new InvalidInputException("Username has been already taken, please use another!", username);
            }
        }
        this.username = username;
    }

    /**
     * Returns Email of {@code HrManager}
     *
     * @return value which represents the {@code email} of {@code HrManager}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets Email Address of {@code HrManager} by ensuring that it satisfies to the {@code EMAIL_VERIFICATION}
     * pattern and that it is unique
     *
     * @param email parameter of type {@code String}
     * @throws Exception if {@code email} doesn't satisfy to the {@code EMAIL_VERIFICATION}
     *                   pattern, or it is not unique, or can't read from file
     */
    public void setEmail(String email) throws Exception {
        ArrayList<HrManager> hrManagers = HrManagerService.getHrManagersFromFile();
        Pattern emailPattern = Pattern.compile(EMAIL_VERIFICATION);
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidInputException("Email should be like: something@example.com", email);
        }
        for (HrManager hrManager : hrManagers) {
            if (hrManager.getEmail().equals(email)) {
                throw new InvalidInputException("Email has been already registered!", email);
            }
        }
        this.email = email;
    }

    /**
     * Returns Encrypted Password of {@code HrManager}
     *
     * @return value which represents the {@code password} of {@code HrManager}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets Encrypted(Md5) Password of {@code HrManager}, by ensuring that it has min 8 character length and
     * contains at least 2 uppercase characters and 3 numbers
     *
     * @param password parameter of type {@code String}
     * @throws InvalidInputException    if {@code password} doesn't contain at least 2 uppercase letters and
     *                                  3 numbers, or it's length is less than 8 characters
     * @throws NoSuchAlgorithmException if Md5 encryption fails
     */
    public void setPassword(String password) throws InvalidInputException, NoSuchAlgorithmException {
        if (checkPassword(password)) {
            this.password = EncryptionService.getMd5(password);
        } else {
            throw new InvalidInputException("Password should contain at least 2 uppercase letters and 3 " +
                    "numbers and has min 8 character length", password);
        }
    }

    /**
     * Do Password Verification
     *
     * @param password parameter of type {@code String}
     * @return {@code true} if password contains at least 2 uppercase characters and 3 numbers and has minimum
     * 8 characters length
     */
    private boolean checkPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        int countOfUppercase = 0;
        int countOfDigits = 0;
        for (int i = 0; i < password.length(); i++) {
            char tempChar = password.charAt(i);
            if (Character.isDigit(tempChar)) {
                countOfDigits++;
            } else if (Character.isUpperCase(tempChar)) {
                countOfUppercase++;
            }
            if (countOfDigits >= 3 && countOfUppercase >= 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return {@code String} representation of {@code HrManager}
     */
    @Override
    public String toString() {
        return new StringBuilder().append(super.toString()).append(",").append(this.getUsername())
                .append(",").append(this.getEmail()).append(",").append(this.getPassword()).toString();
    }
}