package model;

import model.exceptions.InvalidInputException;
import service.EncryptionService;
import service.FileService;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HrManager extends Employee {
    private static final String EMAIL_VERIFICATION = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private String username;
    private String email;
    private String password;


    public HrManager(String data) throws Exception {
        String[] splitData = data.split(",");
        setFirstName(splitData[0].split(" ")[0]);
        setLastName(splitData[0].split(" ")[1]);
        this.username = splitData[1];
        this.email = splitData[2];
        this.password = splitData[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setBirthday(LocalDate.parse(splitData[4], formatter));
        this.identificationNumber = splitData[5];
        setTaxPayerID(splitData[6]);
        setSalary(Double.parseDouble(splitData[7]));
        setExperience(Double.parseDouble(splitData[8]));
    }

    public HrManager(Employee employee) {
        super(employee);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        String[] userInformation = FileService.read("src/files/user_database.txt");
        if (username.length() <= 10) {
            throw new InvalidInputException("Username should have at least 11 digits!", username);
        }
        for (int i = 1, userInformationLength = userInformation.length; i < userInformationLength; i++) {
            String information = userInformation[i];
            String[] splitInfo = information.split(",");
            if (splitInfo[1].equals(username)) {
                throw new InvalidInputException("Username has been already taken, please use another!", username);
            }
        }
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidInputException {
        Pattern emailPattern = Pattern.compile(EMAIL_VERIFICATION);
        Matcher matcher = emailPattern.matcher(email);
        if (matcher.matches()) {
            this.email = email;
        } else {
            throw new InvalidInputException("Email should be like: something@example.com", email);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InvalidInputException, NoSuchAlgorithmException {
        if (checkPassword(password)) {
            this.password = EncryptionService.getMd5(password);
        } else {
            throw new InvalidInputException("Password should contain at least 2 uppercase letters and 3 " +
                    "numbers and has min 8 digit length", password);
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
        String formattedDate = this.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return this.getFirstName() + " " + this.getLastName() + "," + this.getUsername() + "," + this.getEmail() + "," +
                this.getPassword() + "," + formattedDate + "," + this.getIdentificationNumber() + "," +
                this.getTaxPayerID() + "," + this.getSalary() + "," + this.getExperience();
    }
}