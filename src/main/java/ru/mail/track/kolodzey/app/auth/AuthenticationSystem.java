package ru.mail.track.kolodzey.app.auth;

/**
 * Created by DKolodzey on 29.09.15.
 */
public interface AuthenticationSystem {
    User logIn(String login, String password) throws UserNotExistsException, InvalidPasswordException;
    User signUp(String login, String password) throws UserAlreadyExistsException;
    boolean userExists(String login);
}
