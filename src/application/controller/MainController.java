package application.controller;

import org.apache.log4j.Logger;

import application.model.Model;
import application.model.command.adb.CommandManager;
import application.view.MainPanel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {

    private MainPanel view;

    private Model model;
    private static final Logger log = Logger.getLogger(MainController.class);

    public MainController(MainPanel _view) {
        view = _view;
        model = new Model(new CommandManager());
        // model = new Model(new PrintCommandManager()); // For unit test
    }

    public void buttonSendOnAction() {
        log.debug("TextBoxMsg=" + view.getTextBoxMsg());

        model.executeTextEvent(view.getTextBoxMsg());
        view.disableMode();

    }

    public void sendTextFieldKey(KeyEvent e) {
        log.debug("KeyEvent=" + e.getCharacter());

        if (e.getCode() == KeyCode.ESCAPE) {
            view.disableMode();
        } else if (e.getCode() == KeyCode.ENTER) {
            model.executeTextEvent(view.getTextBoxMsg());
            view.disableMode();
        }

    }

    public void sendPaneKey(KeyEvent e) {
        log.debug("KeyEvent=" + e.getText());

        if (e.getCode() == KeyCode.T) {
            view.enableMode();
        } else {
            model.sendKeyEvent(e);
        }
    }

    public void printTest(String str) {
        log.debug("str=" + str);
    }

    public void runAllCommands() {
        log.debug("");
        model.runAllCommands();
    }

}
