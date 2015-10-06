package ru.mail.track.kolodzey.app.ui;

import ru.mail.track.kolodzey.app.auth.User;

/**
 * Created by DKolodzey on 06.10.15.
 */
public class ConsoleMainUI implements MainUI {

    private User user;
    public ConsoleMainUI(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        System.out.println("Hello, " + user.getLogin() + "!");
    }
}
