package ru.mail.track.kolodzey.app;

import ru.mail.track.kolodzey.app.auth.User;
import ru.mail.track.kolodzey.app.auth.UserStore;
import ru.mail.track.kolodzey.app.auth.UserStoreImpl;
import ru.mail.track.kolodzey.app.ui.ConsoleAuthUI;
import ru.mail.track.kolodzey.app.ui.AuthUI;
import ru.mail.track.kolodzey.app.ui.ConsoleMainUI;
import ru.mail.track.kolodzey.app.ui.MainUI;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException {
        try (UserStore userStore = new UserStoreImpl(Paths.get("data.txt"))) {
            AuthUI authUI = new ConsoleAuthUI(userStore);
            User user = authUI.loadUser();
            if (user != null) {
                MainUI mainUI = new ConsoleMainUI(user);
                mainUI.run();
            }
        }
    }
}
