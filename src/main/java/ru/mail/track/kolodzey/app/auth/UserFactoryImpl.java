package ru.mail.track.kolodzey.app.auth;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class UserFactoryImpl implements UserFactory {
    @Override
    public User createUser(String login, String password) {
        return new UserImpl(login, password);
    }
}
