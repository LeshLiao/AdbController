package application.controller;

import application.model.IModel;
import application.model.adb.CommandManager;
import application.view.MainPanel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {

	private MainPanel view;
	private IModel model;

	public MainController(MainPanel _view) {
		view = _view;
		model = new CommandManager();
	}

	public void buttonSendOnAction() {

		System.out.println("MainController buttonSendOnAction");
		model.sendAdbString(view.getTextBoxMsg());
    	view.disableMode();

	}

	public void sendTextFieldKey(KeyEvent e) {

        if(e.getCode() == KeyCode.ESCAPE) {
        	view.disableMode();
        } else if (e.getCode() == KeyCode.ENTER) {
        	model.sendAdbString(view.getTextBoxMsg());
        	view.disableMode();
        }

	}

	public void sendPaneKey(KeyEvent e) {

        if(e.getCode() == KeyCode.T) {
	        System.out.println("click T");
	        view.enableMode();
    	}else {
    		model.sendKeyEvent(e);
    	}
	}

	public void printTest(String str) {
		System.out.println("MainController printTest()" + str);
	}

}
