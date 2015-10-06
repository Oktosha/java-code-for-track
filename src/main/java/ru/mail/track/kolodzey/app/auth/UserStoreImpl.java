package ru.mail.track.kolodzey.app.auth;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class UserStoreImpl implements UserStore {
    private Map<String, UserImpl> userMap;
    private Path path;

    private class UserImpl implements User {
        private String login;
        private String password;

        UserImpl(String login, String password) {
            this.login = login;
            this.password = password;
        }

        UserImpl(String serializedUser) {
            String[] data = serializedUser.split(" ");
            login = data[0];
            password = data[1];
        }

        @Override
        public String getLogin() {
            return this.login;
        }

        @Override
        public boolean passwordIsCorrect(String password) {
            return this.password.equals(password);
        }

        public String toString() {
            return login + " " + password;
        }
    }

    public UserStoreImpl(Path path) throws IOException{
        this.path = path;
        this.userMap = new HashMap<>();
        try (Scanner scanner = new Scanner(Files.newInputStream(path, StandardOpenOption.CREATE))) {
            while (scanner.hasNextLine()) {
                String serializedUser = scanner.nextLine();
                UserImpl user = new UserImpl(serializedUser);
                userMap.put(user.getLogin(), user);
            }
        }
    }

    @Override
    public User getUser(String login, String password) {
        User user = userMap.get(login);
        return ((user != null) && (user.passwordIsCorrect(password))) ? user : null;
    }

    @Override
    public void addUser(String login, String password) throws UserAlreadyExistsException {
        if (isUserExist(login))
            throw new UserAlreadyExistsException();
        userMap.put(login, new UserImpl(login, password));
    }

    @Override
    public boolean isUserExist(String login) {
        return (userMap.containsKey(login));
    }

    @Override
    public void close() throws IOException {
        try (PrintWriter out = new PrintWriter(Files.newOutputStream(path, StandardOpenOption.CREATE))) {
            for (UserImpl user : userMap.values()) {
                out.println(user.toString());
            }
        }
    }
}
