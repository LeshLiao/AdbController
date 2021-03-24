package application.model.print;

import application.model.Commands;
import application.model.IModel;
import javafx.scene.input.KeyEvent;

public class PrintModel implements IModel{
	private Commands commands;

	public PrintModel() {
		commands = new Commands();
	}

	@Override
	public void sendKeyEvent(KeyEvent e) {
		System.out.println(commands.map.get(e.getCode()));
	}

	@Override
	public void sendAdbString(String str) {
		// TODO Auto-generated method stub

	}

}
