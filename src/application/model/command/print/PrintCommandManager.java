package application.model.command.print;

import java.util.List;

import org.apache.log4j.Logger;

import application.model.command.CommandModel;
import application.model.command.adb.ICommand;
import javafx.scene.input.KeyEvent;

public class PrintCommandManager extends CommandModel {
    private static final Logger log = Logger.getLogger(PrintCommandManager.class);

    public PrintCommandManager() {

    }

    @Override
    public void sendKeyEvent(KeyEvent e) {
        log.info(e.getCode());
    }

    @Override
    public void executeTextEvent(String str) {
        log.info("str=" + str);
    }

    @Override
    public List<ICommand> getAllCommand() {
        return null;
    }

}