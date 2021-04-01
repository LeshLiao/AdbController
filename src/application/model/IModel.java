package application.model;

import javafx.scene.input.KeyEvent;

public interface IModel {
	public void sendKeyEvent(KeyEvent e);
	public void sendAdbString(String str);
}
