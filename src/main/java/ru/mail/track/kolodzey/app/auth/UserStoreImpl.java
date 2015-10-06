package ru.mail.track.kolodzey.app.auth;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class UserStoreImpl implements UserStore {
    private Map<String, UserImpl> userMap;
    private Path path;
    private MessageDigest messageDigest;
    private HexBinaryAdapter adapter = new HexBinaryAdapter();

    private byte[] getHash(String password) {
        messageDigest.reset();
        return messageDigest.digest(password.getBytes());

    }

    private class UserImpl implements User {

        private String login;
        private byte[] passwordHash;

        UserImpl(String login, String password) {
            this.login = login;
            this.passwordHash = getHash(password);
        }

        UserImpl(String serializedUser) {
            this.login = serializedUser.split(" ")[0];
            this.passwordHash = adapter.unmarshal(serializedUser.split(" ")[1]);
        }

        @Override
        public String getLogin() {
            return this.login;
        }

        @Override
        public boolean passwordIsCorrect(String password) {
            return MessageDigest.isEqual(this.passwordHash, (getHash(password)));
        }

        public String toString() {
            return login + " " + adapter.marshal(passwordHash);
        }
    }

    public UserStoreImpl(Path path) throws IOException, NoSuchAlgorithmException {
        this.path = path;
        this.userMap = new HashMap<>();
        this.messageDigest = MessageDigest.getInstance("MD5");
        if (Files.exists(path)) {
            try (Scanner scanner = new Scanner(Files.newInputStream(path, StandardOpenOption.CREATE))) {
                while (scanner.hasNextLine()) {
                    String serializedUser = scanner.nextLine();
                    UserImpl user = new UserImpl(serializedUser);
                    userMap.put(user.getLogin(), user);
                }
            }
        }
    }

    @Override
    public User getUser(String login, String password) {
        User user = userMap.get(login);
        return ((user != null) && (user.passwordIsCorrect(password))) ? user : null;
    }

    @Override
    public void addUser(String login, String password) throws UserAlreadyExistsException, IOException {
        if (isUserExist(login))
            throw new UserAlreadyExistsException();
        userMap.put(login, new UserImpl(login, password));
        try (PrintWriter out = new PrintWriter(Files.newOutputStream(path,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.println(getUser(login, password).toString());
        }
    }

    @Override
    public boolean isUserExist(String login) {
        return (userMap.containsKey(login));
    }

    @Override
    public void close() throws IOException {
    }
}
