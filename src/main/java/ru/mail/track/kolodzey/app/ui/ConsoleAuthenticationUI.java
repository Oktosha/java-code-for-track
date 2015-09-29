package ru.mail.track.kolodzey.app.ui;

import ru.mail.track.kolodzey.app.auth.AuthenticationSystem;
import ru.mail.track.kolodzey.app.auth.InvalidPasswordException;
import ru.mail.track.kolodzey.app.auth.UserAlreadyExistsException;
import ru.mail.track.kolodzey.app.auth.UserNotExistsException;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class ConsoleAuthenticationUI implements UserInterface {
    AuthenticationSystem authenticationSystem;
    public ConsoleAuthenticationUI(AuthenticationSystem authenticationSystem) {
        this.authenticationSystem = authenticationSystem;
    }

    @Override
    public void run() {
        System.out.println("Welcome to HelloWorld 2.0!");
        runChooseOptionsUI();
    }

    private void runChooseOptionsUI() {
        for (;;) {
            System.out.println("Enter «1» to log in");
            System.out.println("Enter «0» to sign up");
            System.out.println("Enter «q» to quit");
            try (Scanner scanner = new Scanner(new InputStreamReader(System.in))) {
                String ans = scanner.nextLine();
                switch (ans) {
                    case "1":
                        runLogInUI();
                        return;
                    case "2":
                        runSignUpUI();
                        return;
                    case "q":
                        return;
                    default:
                        System.out.println("We don't understand you.");
                        System.out.println("Reenter what you want, please.");
                }
            }
        }
    }

    private void runSignUpUI() {
        System.out.println("SIGN UP");
        System.out.println("Enter the login you want:");
        try (Scanner scanner = new Scanner(new InputStreamReader(System.in))) {
            String login = scanner.nextLine();
            while (authenticationSystem.userExists(login)) {
                System.out.println("User with this login already exists. Enter other login:");
                login = scanner.nextLine();
            }
            String password;
            System.out.println("Enter the password:");
            password = scanner.nextLine();
            try {
                authenticationSystem.signUp(login, password);
            } catch (UserAlreadyExistsException e) {
                System.out.println("Sorry, while you were entering password another user with your login signed up");
                runSignUpUI();
            }
        }
    }

    private void runLogInUI() {
        System.out.println("LOG IN");
        try (Scanner scanner = new Scanner(new InputStreamReader(System.in))) {
            System.out.println("Enter your login:");
            String login = scanner.nextLine();
            System.out.println("Enter the password:");
            String password = scanner.nextLine();
            try {
                authenticationSystem.logIn(login, password);
            } catch (UserNotExistsException | InvalidPasswordException e) {
                System.out.println("Sorry, incorrect login or password. Try again.");
                runLogInUI();
            }
        }
    }
}
