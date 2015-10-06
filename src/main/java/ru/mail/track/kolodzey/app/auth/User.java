package ru.mail.track.kolodzey.app.auth;

import com.sun.istack.internal.NotNull;

/**
 * Created by DKolodzey on 29.09.15.
 */
public interface User {
    @NotNull
    public String getLogin();
    public boolean passwordIsCorrect(String password);
}
