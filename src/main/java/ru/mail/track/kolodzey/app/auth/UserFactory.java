package ru.mail.track.kolodzey.app.auth;

/**
 * Created by DKolodzey on 29.09.15.
 */
public interface UserFactory {
    public User createUser(String login, String password);
}
