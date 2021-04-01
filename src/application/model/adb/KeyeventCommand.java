package application.model.adb;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class KeyeventCommand implements Command {

	private ProcessBuilder pb;
	private final String adbFile = "adb";

	private static final Logger log = Logger.getLogger(KeyeventCommand.class);

	public KeyeventCommand() {
		log.setLevel(Level.ERROR);
	}
	
	@Override
	public boolean execute(String str) {


		log.trace("Trace Message!");
		log.debug("Debug Message!");
		log.info("Info Message!");
		log.warn("Warn Message!");
		log.error("Error Message!");
		log.fatal("Fatal Message!");


		System.out.println("KeyeventCommand execute() "+ str);

		pb = new ProcessBuilder();
        pb.command(adbFile,"shell","input","keyevent",str);

        try {
        	pb.start();
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
		return true;
	}

}
