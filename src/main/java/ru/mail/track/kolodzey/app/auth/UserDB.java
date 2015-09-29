package ru.mail.track.kolodzey.app.auth;



import java.io.Closeable;

/**
 * Created by DKolodzey on 29.09.15.
 */
public interface UserDB extends Closeable {
    public User getUserByLogin(String login);
    public void addUser(String login, String password) throws UserAlreadyExistsException;
}
