package ru.mail.track.kolodzey.app;

import ru.mail.track.kolodzey.app.auth.*;
import ru.mail.track.kolodzey.app.ui.ConsoleAuthUI;
import ru.mail.track.kolodzey.app.ui.AuthUI;
import ru.mail.track.kolodzey.app.ui.ConsoleMainUI;
import ru.mail.track.kolodzey.app.ui.MainUI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        try (UserStore userStore = new UserStoreImpl()) {
            AuthUI authUI = new ConsoleAuthUI(userStore);
            User user = authUI.loadUser();
            if (user != null) {
                MainUI mainUI = new ConsoleMainUI(user);
                mainUI.run();
            }
        }
    }
}
