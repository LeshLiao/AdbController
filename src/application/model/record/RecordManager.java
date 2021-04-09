package application.model.record;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import application.model.command.adb.ICommand;
import application.model.command.adb.WaitCommand;

public class RecordManager {
    private static final Logger log = Logger.getLogger(RecordManager.class);

    private List<ICommand> commandList = new ArrayList<>();
    private long indexTime = 0;
    private boolean enableRunThread;
    public boolean isRecording;

    public void startRecord() {
        log.info("");

        isRecording = true;
        indexTime = 0;
        commandList.clear();
    }

    public void stopRecord() {
        log.info("");

        isRecording = false;
    }

    public void addCommand(ICommand cmd) {
        log.debug("");

        if (indexTime == 0) {
            indexTime = System.currentTimeMillis();
        } else {
            long currentTime = System.currentTimeMillis();
            long waitMillis = currentTime - indexTime;
            indexTime = currentTime;
            commandList.add(new WaitCommand(waitMillis));
        }
        commandList.add(cmd);
    }

    public void runAllCommands() {
        log.info("Start...");

        enableRunThread = true;

        new Thread(() -> {
            for (ICommand cmd : commandList) {
                if (enableRunThread == false)
                    break;
                cmd.execute();
            }
        }, "Run cmd Thread").start();

        log.info("end.");
    }

    public void StopRunning() {
        log.info("Stop.");
        enableRunThread = false;
    }
}
