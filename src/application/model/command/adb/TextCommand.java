package application.model.command.adb;

import java.io.IOException;

import org.apache.log4j.Logger;

public class TextCommand implements ICommand {

    private ProcessBuilder pb;
    private final String adbFile = "adb";
    private static final Logger log = Logger.getLogger(TextCommand.class);
    private String text = "";

    public TextCommand(String _text) {
        text = _text;
    }

    @Override
    public boolean execute() {
        log.info("text=" + text);

        pb = new ProcessBuilder();
        String content = "\'" + text + "\'";
        pb.command(adbFile, "shell", "input", "text ", content);

        try {
            pb.start();
        } catch (IOException e1) {
            e1.printStackTrace();
            log.error("Exception, text=" + text);
            return false;
        }
        return true;
    }

}
