
package application.model.command.adb;

import org.apache.log4j.Logger;

public class WaitCommand implements ICommand {
    private static final Logger log = Logger.getLogger(WaitCommand.class);
    private long millis = 0;

    public WaitCommand(long _millis) {
        millis = _millis;
    }

    @Override
    public boolean execute() {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("Exception, millis=" + millis);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}