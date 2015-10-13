package ru.mail.track.kolodzey.app.ui;

import java.util.List;

/**
 * Created by DKolodzey on 13.10.15.
 */
public interface Parser {
    public List<CommandInfo> parse(String s);
}
