package ru.mail.track.kolodzey.app.auth;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class AuthenticationSystemImpl implements AuthenticationSystem {
    @Override
    public User logIn(String login, String password) throws UserNotExistsException, InvalidPasswordException {
        return null;
    }

    @Override
    public User signUp(String login, String password) throws UserAlreadyExistsException {
        return null;
    }

    @Override
    public boolean userExists(String login) {
        return false;
    }
}
