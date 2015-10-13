package ru.mail.track.kolodzey.app.ui;

import ru.mail.track.kolodzey.app.auth.User;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by DKolodzey on 06.10.15.
 */
public class ChatUI {

    private User user;
    private Scanner scanner = null;

    public ChatUI(User user) {
        this.user = user;
    }

    public void run() {
        System.out.println("Welcome to Chatter!");
        try (Scanner scanner = new Scanner(new InputStreamReader(System.in))) {
            this.scanner = scanner;
            Parser parser = new ParserImpl();
            Context context = new Context();
            Interpreter interpreter = new InterpreterImpl();
            while (scanner.hasNextLine()) {
                interpreter.interpret(parser.parse(scanner.nextLine()), context);
            }
        }
    }
}
