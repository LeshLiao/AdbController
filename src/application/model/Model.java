package application.model;

import application.model.command.CommandModel;
import application.model.command.adb.ICommand;
import javafx.scene.input.KeyEvent;

public class Model {
    private CommandModel commandModel;

    public Model(CommandModel _model) {
        commandModel = _model;
        // model = new PrintCommandManager(); // For unit test
    }

    public void executeTextEvent(String str) {
        commandModel.executeTextEvent(str);
    }

    public void sendKeyEvent(KeyEvent e) {
        commandModel.sendKeyEvent(e);
    }

    public void runAllCommands() {
        for (ICommand cmd : commandModel.getAllCommand()) {
            cmd.execute();
        }
    }
}
