package service;

import model.HrManager;
import model.exceptions.InvalidInputException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * {@code HrManagerService} class which extends {@code EmployeeService} class
 * Contains functions which manipulate with {@code HrManager} class objects
 *
 * @author Artyom
 */
public class HrManagerService extends EmployeeService {

    /**
     * Creates {@code HrManager} instance
     *
     * @return {@code HrManager} object
     */
    private static HrManager createHrManager() {
        HrManager hrManager = new HrManager(EmployeeService.createEmployee());
        boolean indicator = true;
        while (indicator) {
            System.out.print("Enter the Username: ");
            String username = scanner.next();
            try {
                hrManager.setUsername(username.trim());
                indicator = false;
            } catch (Exception e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Email: ");
            String email = scanner.next();
            try {
                hrManager.setEmail(email.trim());
                indicator = false;
            } catch (Exception e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Password: ");
            String password = scanner.next();
            try {
                hrManager.setPassword(password.trim());
                indicator = false;
            } catch (InvalidInputException | NoSuchAlgorithmException e) {
                System.out.println("Please try again!");
                System.out.println(e.toString());
            }
        }
        return hrManager;
    }

    /**
     * Register new {@code HrManager}
     */
    public static void register() {
        createEmployeeAndSave(createHrManager(), "src/files/user_database.txt");
    }

    /**
     * Reads from file and creates LinkedHashSet of {@code HrManager}s
     *
     * @return LinkedHashSet of {@code HrManager}s
     * @throws Exception if can't read from file
     */
    public static ArrayList<HrManager> getHrManagersFromFile() throws Exception {
        ArrayList<HrManager> hrManagersArrayList = new ArrayList<>();
        String[] userInformation = FileService.read("src/files/user_database.txt");
        for (int i = 1; i < userInformation.length; i++) {
            hrManagersArrayList.add(new HrManager(userInformation[i]));
        }
        return hrManagersArrayList;
    }

    /**
     * Login {@code HrManager}
     *
     * @return {@code true} if login successful, {@code false} otherwise
     */
    public static boolean login() {
        try {
            ArrayList<HrManager> userArrayList = getHrManagersFromFile();
            if (userArrayList.isEmpty()) {
                System.out.println("Database is Empty! Please Register");
                return false;
            }
            System.out.print("Enter the Username: ");
            String username = scanner.next();
            System.out.print("Enter the Password: ");
            String password = scanner.next();
            for (HrManager hrManager : userArrayList) {
                if (hrManager.getUsername().equals(username)) {
                    if (hrManager.getPassword().equals(EncryptionService.getMd5(password))) {
                        System.out.println("Login Successful!");
                        return true;
                    }
                }
            }
            System.out.println("Invalid Input Data!");
            return false;
        } catch (Exception e) {
            System.out.println("Invalid File/ Can't Read");
            System.out.println(e.toString());
        }
        return false;
    }
}