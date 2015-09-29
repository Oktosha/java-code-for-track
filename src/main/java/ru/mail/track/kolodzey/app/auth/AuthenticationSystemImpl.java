package ru.mail.track.kolodzey.app.auth;

/**
 * Created by DKolodzey on 29.09.15.
 */
public class AuthenticationSystemImpl implements AuthenticationSystem {

    private UserDB userDB;

    public  AuthenticationSystemImpl(UserDB userDB) {
        this.userDB = userDB;
    }

    @Override
    public User logIn(String login, String password) throws UserNotExistsException, InvalidPasswordException {
        User user = userDB.getUserByLogin(login);
        if (user == null) {
            throw new UserNotExistsException();
        }
        if (!user.passwordIsCorrect(password)) {
            throw new InvalidPasswordException();
        }
        return user;
    }

    @Override
    public User signUp(String login, String password) throws UserAlreadyExistsException {
        userDB.addUser(login, password);
        return null;
    }

    @Override
    public boolean userExists(String login) {
        return false;
    }
}
