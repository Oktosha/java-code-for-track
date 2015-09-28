package ru.mail.track.kolodzey.app;

import ru.mail.track.kolodzey.app.ui.ConsoleAuthenticationUI;
import ru.mail.track.kolodzey.app.ui.UserInterface;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        UserInterface ui = new ConsoleAuthenticationUI();
        ui.run();
    }
}
