package ru.mail.track.kolodzey.app.auth;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class UserImpl implements User {

    private final String login;
    private final String password;

    public UserImpl(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public boolean passwordIsCorrect(String password) {
        return this.password.equals(password);
    }
}
