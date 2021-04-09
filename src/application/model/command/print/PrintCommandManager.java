package application.model.command.print;

import org.apache.log4j.Logger;

import application.model.command.CommandModel;
import application.model.record.RecordManager;
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
    public void setRecorder(RecordManager recordManager) {
        // TODO Auto-generated method stub
    }

    @Override
    public RecordManager getRecorder() {
        // TODO Auto-generated method stub
        return null;
    }

}