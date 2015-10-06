package ru.mail.track.kolodzey.app.auth;



/**
 * Created by DKolodzey on 29.09.15.
 */
public interface User {

    public String getLogin();
    public boolean passwordIsCorrect(String password);
}
