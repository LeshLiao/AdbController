package application.model.print;

import application.model.MappingTableCmd;
import application.model.IModel;
import javafx.scene.input.KeyEvent;

public class PrintCommandManager implements IModel{
	private MappingTableCmd commands;

	public PrintCommandManager() {
		commands = new MappingTableCmd();
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
