package ru.mail.track.kolodzey.app.auth;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.util.Map;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class UserDBImpl implements UserDB {
    private Map<String, User> userMap;
    private UserFactory userFactory;

    public UserDBImpl(Map userMap, UserFactory userFactory) {
        this.userMap = userMap;
        this.userFactory = userFactory;
    }

    @Override
    public User getUserByLogin(String login) {
        return userMap.get(login);
    }

    @Override
    public void addUser(String login, String password) throws UserAlreadyExistsException {
        if (getUserByLogin(login) != null)
            throw new UserAlreadyExistsException();
        userMap.put(login, userFactory.createUser(login, password));
    }

    @Override
    public void close() throws IOException {
    }
}
