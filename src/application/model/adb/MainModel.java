package application.model.adb;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import application.model.Commands;
import application.model.IModel;
import javafx.scene.input.KeyEvent;

public class MainModel implements IModel {
    ProcessBuilder pb;
    boolean isRun;
    //private final String adbFile = "C:\\Users\\vend_Leo_Liao001\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe";
    private final String adbFile = "adb.exe";
    private static ConcurrentLinkedQueue<String> queueStr = new ConcurrentLinkedQueue<String>();
	Commands commands;

    public MainModel() {
        pb = new ProcessBuilder();
        commands = new Commands();
        threadStart();
    }

    public void runByStr(String str) {
        pb.command(adbFile,"shell","input","keyevent",str);

        try {
        	pb.start();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
	public void sendAdbString(String str) {

        System.out.println("sendAdbString, str="+ str);

        String content = "\'" + str +  "\'";

        System.out.println("sendAdbString, content="+ content);

        pb.command(adbFile,"shell","input","text ",content);

        try {
            pb.start();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @Override
	public void sendKeyEvent(KeyEvent e) {
    	System.out.println("sendKeyEvent");
    	if( queueStr.size()  <= 3) {
    		String str = commands.map.get(e.getCode());
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
