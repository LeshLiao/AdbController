package application.model.command;

import java.util.List;

import application.model.command.adb.ICommand;
import javafx.scene.input.KeyEvent;

public abstract class CommandModel {
    public abstract void sendKeyEvent(KeyEvent e);

    public abstract void executeTextEvent(String str);

    public abstract List<ICommand> getAllCommand();
}