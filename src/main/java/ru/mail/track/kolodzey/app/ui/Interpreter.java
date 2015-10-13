package ru.mail.track.kolodzey.app.ui;

import java.util.List;

/**
 * Created by DKolodzey on 13.10.15.
 */
public interface Interpreter {
    public void interpret(List<CommandInfo> commands, Context context);
}
