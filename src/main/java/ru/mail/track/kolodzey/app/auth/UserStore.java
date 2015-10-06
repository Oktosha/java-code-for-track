package ru.mail.track.kolodzey.app.auth;



import java.io.Closeable;

/**
 * Created by DKolodzey on 29.09.15.
 */
public interface UserStore extends Closeable {
    /*returns null if user doesn't exist or password is incorrect */
    public User getUser(String login, String password);

    public User addUser(String login, String password) throws UserAlreadyExistsException;

    public boolean isUserExist(String login);
}
