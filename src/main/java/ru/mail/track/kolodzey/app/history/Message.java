package ru.mail.track.kolodzey.app.history;

import java.time.OffsetDateTime;

/**
 * Created by DKolodzey on 13.10.15.
 */
public interface Message {
    public String getText();
    public OffsetDateTime getTime();
}
