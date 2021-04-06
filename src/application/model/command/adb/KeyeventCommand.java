package application.model.command.adb;

import java.io.IOException;

import org.apache.log4j.Logger;

public class KeyeventCommand implements ICommand {
    private static final Logger log = Logger.getLogger(KeyeventCommand.class);
    private ProcessBuilder pb;
    private final String adbFile = "adb";
    private String keyEvent = "";

    public KeyeventCommand(String str) {
        keyEvent = str;
        // log.setLevel(Level.ERROR);
    }

    @Override
    public boolean execute() {
        log.info("keyEvent=" + keyEvent);

        pb = new ProcessBuilder();
        pb.command(adbFile, "shell", "input", "keyevent", keyEvent);

        try {
            pb.start();
        } catch (IOException e1) {
            e1.printStackTrace();
            log.error("Exception, keyEvent=" + keyEvent);
            return false;
        }
        return true;
    }

}
