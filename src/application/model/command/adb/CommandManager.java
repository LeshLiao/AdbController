package application.model.command.adb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import application.model.command.ICommandModel;
import javafx.scene.input.KeyEvent;

public class CommandManager implements ICommandModel {
    boolean isRun;
    private static final Logger log = Logger.getLogger(CommandManager.class);
    private List<ICommand> commandList = new ArrayList<>();
    private static ConcurrentLinkedQueue<String> queueStr = new ConcurrentLinkedQueue<String>();
    MappingTableCmd mappingTable;

    public CommandManager() {
        log.debug("constructor");

        mappingTable = new MappingTableCmd();
        threadStart();
        BasicConfigurator.configure();
    }

    public void executeKeyEvent(String str) {
        ICommand cmd = new KeyeventCommand(str);
        cmd.execute();
        commandList.add(cmd);
        // commandList.add(new WaitCommand(1000));
    }

    @Override
    public void executeTextEvent(String str) {
        ICommand cmd = new TextCommand(str);
        cmd.execute();
        commandList.add(cmd);
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

    public void threadStart() {
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
        // TODO Auto-generated method stub
        return commandList;
    }

}
