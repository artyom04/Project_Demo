package model;

import model.exceptions.InvalidInputException;
import service.EncryptionService;
import service.HrManagerService;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HrManager extends Employee {
    private static final String EMAIL_VERIFICATION = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private String username;
    private String email;
    private String password;


    public HrManager(String data) throws Exception {
        super(data);
        String[] splitData = data.split(",");
        this.username = splitData[6];
        this.email = splitData[7];
        this.password = splitData[8];
    }

    public HrManager(Employee employee) {
        super(employee);
    }

    public String getUsername() {
        return username;
    }

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

    public String getEmail() {
        return email;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InvalidInputException, NoSuchAlgorithmException {
        if (checkPassword(password)) {
            this.password = EncryptionService.getMd5(password);
        } else {
            throw new InvalidInputException("Password should contain at least 2 uppercase letters and 3 " +
                    "numbers and has min 8 character length", password);
        }
    }

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

    @Override
    public String toString() {
        return super.toString() + "," + this.getUsername() + "," + this.getEmail() + "," +
                this.getPassword();
    }
}