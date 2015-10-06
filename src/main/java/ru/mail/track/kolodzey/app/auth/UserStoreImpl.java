package ru.mail.track.kolodzey.app.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class UserStoreImpl implements UserStore {
    private Map<String, User> userMap;

    private class UserImpl implements User {
        private String login;
        private String password;

        UserImpl(String login, String password) {
            this.login = login;
            this.password = password;
        }

        @Override
        public String getLogin() {
            return this.login;
        }

        @Override
        public boolean passwordIsCorrect(String password) {
            return this.password.equals(password);
        }
    }

    public UserStoreImpl() {
        this.userMap = new HashMap<>();
    }

    @Override
    public User getUser(String login, String password) {
        User user = userMap.get(login);
        return ((user != null) && (user.passwordIsCorrect(password))) ? user : null;
    }

    @Override
    public User addUser(String login, String password) throws UserAlreadyExistsException {
        if (isUserExist(login))
            throw new UserAlreadyExistsException();
        return userMap.put(login, new UserImpl(login, password));
    }

    @Override
    public boolean isUserExist(String login) {
        return (userMap.containsKey(login));
    }

    @Override
    public void close() throws IOException {
    }
}
