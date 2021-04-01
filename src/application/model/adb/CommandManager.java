package application.model.adb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import application.model.IModel;
import application.model.MappingTableCmd;
import javafx.scene.input.KeyEvent;


public class CommandManager implements IModel {
    boolean isRun;
    private List<Command> commandList = new ArrayList<>();
    private static ConcurrentLinkedQueue<String> queueStr = new ConcurrentLinkedQueue<String>();
	MappingTableCmd mappingTable;

    public CommandManager() {
    	mappingTable = new MappingTableCmd();
        threadStart();

        BasicConfigurator.configure();
    }

    public void runByStr(String str) {
    	Command cmd = new KeyeventCommand();
    	cmd.execute(str);
    	commandList.add(cmd);
    }

    @Override
	public void sendAdbString(String str) {
    	Command cmd = new TextCommand();
    	cmd.execute(str);
    	commandList.add(cmd);
    }

    @Override
	public void sendKeyEvent(KeyEvent e) {
    	System.out.println("sendKeyEvent");
    	if( queueStr.size()  <= 3) {
    		String str = mappingTable.map.get(e.getCode());
    		if(str != null) {
    			queueStr.offer(str);
    		}

    	}
    }

	public void threadStart() {
		System.out.println("threadStart");

		  new Thread() {
		  @Override
		  public void run() {
		      while(true) {
		          try {
		              Thread.sleep(100);
		              if(!queueStr.isEmpty()) {
		                  String commandStr = "";
		                  for(String str :queueStr) {
		                      commandStr += " " + str;
		                  }
		                  runByStr(commandStr);
		                  queueStr.clear();
		              }

		          } catch (InterruptedException e) {
		              e.printStackTrace();
		          }

		      }

		  }
		}.start();
	}

}
