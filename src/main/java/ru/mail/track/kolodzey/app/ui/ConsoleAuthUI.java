package ru.mail.track.kolodzey.app.ui;

import ru.mail.track.kolodzey.app.auth.User;
import ru.mail.track.kolodzey.app.auth.UserAlreadyExistsException;
import ru.mail.track.kolodzey.app.auth.UserStore;

import java.io.Console;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class ConsoleAuthUI implements AuthUI {
    UserStore userStore;
    Scanner scanner = null;
    Console console = System.console();

    public ConsoleAuthUI(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public User loadUser() {
        System.out.println("Welcome to HelloWorld 2.0!");
        try (Scanner scanner = new Scanner(new InputStreamReader(System.in))) {
            this.scanner = scanner;
            return runChooseOptionsUI();
        }
    }

    private User runChooseOptionsUI() {
        User user = null;
        while(user == null) {
            System.out.println("Enter «1» to log in");
            System.out.println("Enter «0» to sign up");
            System.out.println("Enter «q» to quit");
            String ans = scanner.nextLine();
            switch (ans) {
                case "1":
                    user = runLogInUI();
                    break;
                case "0":
                    user = runSignUpUI();
                    break;
                case "q":
                    return null;
                default:
                    System.out.println("We don't understand you.");
                    System.out.println("Reenter what you want, please.");
            }
        }
        return user;
    }

    private User runSignUpUI() {
        System.out.println("SIGN UP");
        System.out.println("Enter the login you want:");

        String login = scanner.nextLine();
        while (userStore.isUserExist(login)) {
            System.out.println("User with this login already exists. Enter other login:");
            login = scanner.nextLine();
        }
        System.out.println("Enter the password:");
        String password = String.copyValueOf(console.readPassword());
        try {
            userStore.addUser(login, password);
            return userStore.getUser(login, password);
        } catch (UserAlreadyExistsException e) {
            System.out.println("Sorry, while you were entering password another user with your login signed up");
            return null;
        }

    }

    private User runLogInUI() {
        System.out.println("LOG IN");
        System.out.println("Enter your login:");
        String login = scanner.nextLine();
        System.out.println("Enter the password:");
        String password = String.copyValueOf(console.readPassword());
        User user = userStore.getUser(login, password);
        if (user == null) {
            System.out.println("Sorry, login or password is incorrect");
        }
        return user;
    }
}
