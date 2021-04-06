package application.model.command.print;

import org.apache.log4j.Logger;

import application.model.command.ICommandModel;
import javafx.scene.input.KeyEvent;

public class PrintCommandManager implements ICommandModel {
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

}