package application.model;

import application.model.command.CommandModel;
import application.model.record.RecordManager;
import javafx.scene.input.KeyEvent;

public class Model {
    private CommandModel commandModel;
    private RecordManager recordManager;

    public Model(CommandModel _model) {
        commandModel = _model;
        recordManager = commandModel.getRecorder();
        // model = new PrintCommandManager(); // For unit test
    }

    public void executeTextEvent(String str) {
        commandModel.executeTextEvent(str);
    }

    public void sendKeyEvent(KeyEvent e) {
        commandModel.sendKeyEvent(e);
    }

    public void runAllCommands() {
        recordManager.runAllCommands();
    }

    public void stopRunCommands() {
        recordManager.StopRunning();
    }

    public void startRecord() {
        recordManager.startRecord();
    }

    public void stopRecord() {
        recordManager.stopRecord();
    }
}
