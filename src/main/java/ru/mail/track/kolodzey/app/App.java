package ru.mail.track.kolodzey.app;

import ru.mail.track.kolodzey.app.auth.*;
import ru.mail.track.kolodzey.app.ui.ConsoleAuthenticationUI;
import ru.mail.track.kolodzey.app.ui.UserInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Map<String, User> userMap = new HashMap<>();
        userMap.put("daria", new UserImpl("daria", "111"));
        UserDB userDB = new UserDBImpl(userMap, new UserFactoryImpl());
        UserInterface ui = new ConsoleAuthenticationUI(new AuthenticationSystemImpl(userDB));
        ui.run();
    }
}
