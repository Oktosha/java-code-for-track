package ru.mail.track.kolodzey.app.ui;

import com.sun.istack.internal.Nullable;
import ru.mail.track.kolodzey.app.auth.User;

/**
 * Created by DKolodzey on 29.09.15.
 */

public interface AuthUI {
    @Nullable
    public User loadUser();
}
