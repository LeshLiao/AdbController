package application.model.command.adb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import application.model.command.CommandModel;
import javafx.scene.input.KeyEvent;

public class CommandManager extends CommandModel {
    boolean isRun;
    private static final Logger log = Logger.getLogger(CommandManager.class);
    private List<ICommand> commandList = new ArrayList<>();
    private static ConcurrentLinkedQueue<String> queueStr = new ConcurrentLinkedQueue<String>();
    private MappingTableCmd mappingTable;
    private long indexTime = 0;
    // private long startTime = System.currentTimeMillis();
    // private long endTime = System.currentTimeMillis();

    public CommandManager() {
        log.debug("constructor");

        mappingTable = new MappingTableCmd();
        threadStart();
        BasicConfigurator.configure();
    }

    public void executeKeyEvent(String str) {
        ICommand cmd = new KeyeventCommand(str);
        cmd.execute();
        recordCommand(cmd);
    }

    @Override
    public void executeTextEvent(String str) {
        ICommand cmd = new TextCommand(str);
        cmd.execute();
        recordCommand(cmd);
    }

    @Override
    public void sendKeyEvent(KeyEvent e) {
        if (queueStr.size() <= 3) {
            String str = mappingTable.map.get(e.getCode());
            if (str != null) {
                queueStr.offer(str);
                log.debug(str);
            }
        }
    }

    private void threadStart() {
        log.debug("Start.");

        new Thread("Queue thread") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        if (!queueStr.isEmpty()) {
                            String commandStr = "";
                            for (String str : queueStr) {
                                commandStr += " " + str;
                            }
                            executeKeyEvent(commandStr);
                            queueStr.clear();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public List<ICommand> getAllCommand() {
        return commandList;
    }

    private void recordCommand(ICommand cmd) {
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
}
