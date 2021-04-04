package application.model.adb;

import java.io.IOException;

import org.apache.log4j.Logger;

public class TextCommand implements ICommand {

    private ProcessBuilder pb;
    private final String adbFile = "adb";
    private static final Logger log = Logger.getLogger(TextCommand.class);

    @Override
    public boolean execute(String str) {
        log.info("str=" + str);

        pb = new ProcessBuilder();
        String content = "\'" + str + "\'";
        pb.command(adbFile, "shell", "input", "text ", content);

        try {
            pb.start();
        } catch (IOException e1) {
            e1.printStackTrace();
            log.error("Exception, str=" + str);
            return false;
        }
        return true;
    }

}
