package application.model.command;

import java.util.List;

import application.model.command.adb.ICommand;
import javafx.scene.input.KeyEvent;

public interface ICommandModel {
    public void sendKeyEvent(KeyEvent e);

    public void executeTextEvent(String str);

    public List<ICommand> getAllCommand();
}