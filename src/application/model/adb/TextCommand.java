package application.model.adb;

import java.io.IOException;

import org.apache.log4j.Logger;

public class TextCommand implements Command  {

	private ProcessBuilder pb;
	private final String adbFile = "adb";

	private static final Logger log = Logger.getLogger(TextCommand.class);

	@Override
	public boolean execute(String str) {
		System.out.println("TextCommand execute() "+ str);


		log.trace("Trace Message!");
		log.debug("Debug Message!");
		log.info("Info Message!");
		log.warn("Warn Message!");
		log.error("Error Message!");
		log.fatal("Fatal Message!");

		pb = new ProcessBuilder();
        String content = "\'" + str +  "\'";
        pb.command(adbFile,"shell","input","text ",content);

        try {
            pb.start();
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
        return true;

	}

}
