package application.model.command;

import javafx.scene.input.KeyEvent;

public interface ICommandModel {
    public void sendKeyEvent(KeyEvent e);
    public void executeTextEvent(String str);
}