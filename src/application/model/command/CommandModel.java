package application.model.command;

import application.model.record.RecordManager;
import javafx.scene.input.KeyEvent;

public abstract class CommandModel {
    public abstract void sendKeyEvent(KeyEvent e);

    public abstract void executeTextEvent(String str);

    public abstract void setRecorder(RecordManager recordManager);

    public abstract RecordManager getRecorder();

}