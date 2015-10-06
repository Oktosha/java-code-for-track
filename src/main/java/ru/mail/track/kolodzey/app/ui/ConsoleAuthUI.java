package ru.mail.track.kolodzey.app.ui;

import ru.mail.track.kolodzey.app.auth.User;
import ru.mail.track.kolodzey.app.auth.UserAlreadyExistsException;
import ru.mail.track.kolodzey.app.auth.UserStore;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class ConsoleAuthUI implements AuthUI {
    UserStore userStore;
    public ConsoleAuthUI(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public User loadUser() {
        System.out.println("Welcome to HelloWorld 2.0!");
        return runChooseOptionsUI();
    }

    private User runChooseOptionsUI() {
        for (;;) {
            System.out.println("Enter «1» to log in");
            System.out.println("Enter «0» to sign up");
            System.out.println("Enter «q» to quit");
            try (Scanner scanner = new Scanner(new InputStreamReader(System.in))) {
                String ans = scanner.nextLine();
                switch (ans) {
                    case "1":
                        return runLogInUI();
                    case "2":
                        return runSignUpUI();
                    case "q":
                        return null;
                    default:
                        System.out.println("We don't understand you.");
                        System.out.println("Reenter what you want, please.");
                }
            }
        }
    }

    private User runSignUpUI() {
        System.out.println("SIGN UP");
        System.out.println("Enter the login you want:");
        try (Scanner scanner = new Scanner(new InputStreamReader(System.in))) {
            String login = scanner.nextLine();
            while (userStore.isUserExist(login)) {
                System.out.println("User with this login already exists. Enter other login:");
                login = scanner.nextLine();
            }
            String password;
            System.out.println("Enter the password:");
            password = scanner.nextLine();
            try {
                return userStore.addUser(login, password);
            } catch (UserAlreadyExistsException e) {
                System.out.println("Sorry, while you were entering password another user with your login signed up");
                return runChooseOptionsUI();
            }
        }
    }

    private User runLogInUI() {
        System.out.println("LOG IN");
        try (Scanner scanner = new Scanner(new InputStreamReader(System.in))) {
            System.out.println("Enter your login:");
            String login = scanner.nextLine();
            System.out.println("Enter the password:");
            String password = scanner.nextLine();
            return userStore.getUser(login, password);
        }
    }
}
