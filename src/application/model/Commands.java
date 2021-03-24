package application.model;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;

public class Commands {

	public Map<KeyCode, String> map = new HashMap<KeyCode, String>();

	public Commands() {

		map.put(KeyCode.NUMPAD6, "KEYCODE_DPAD_RIGHT");
		map.put(KeyCode.NUMPAD4, "KEYCODE_DPAD_LEFT");
		map.put(KeyCode.NUMPAD2, "KEYCODE_DPAD_DOWN");
		map.put(KeyCode.NUMPAD8, "KEYCODE_DPAD_UP");

		map.put(KeyCode.NUMPAD5, "KEYCODE_DPAD_CENTER");
		map.put(KeyCode.NUMPAD9, "KEYCODE_BACK");
		map.put(KeyCode.NUMPAD7, "KEYCODE_HOME");

		map.put(KeyCode.DELETE, "KEYCODE_DEL");
		map.put(KeyCode.BACK_SPACE, "KEYCODE_DEL");

	}

}

