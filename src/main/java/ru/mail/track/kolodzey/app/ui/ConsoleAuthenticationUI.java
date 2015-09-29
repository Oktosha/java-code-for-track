package ru.mail.track.kolodzey.app.ui;

import ru.mail.track.kolodzey.app.auth.AuthenticationSystem;

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
        System.out.println("You entered Sign Up Interface");
    }

    private void runLogInUI() {
        System.out.println("You entered Log In Interface");
    }
}
