package ru.mail.track.kolodzey.app.history;

import ru.mail.track.kolodzey.app.auth.User;

import java.util.List;

/**
 * Created by DKolodzey on 13.10.15.
 * How to check user
 */
public interface MessageStore {
    public List<Message> getHistory(User user, int size);
    public List<Message> getHistory(User user);
    public void addMessage(User user, Message message);
}
