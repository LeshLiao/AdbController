package application.controller;

import application.view.MainPanel;

public class MainController {

	private MainPanel view;

	public MainController(MainPanel _view) {
		view = _view;
	}


	public void buttonSendOnAction() {
		System.out.println("MainController buttonSendOnAction");
	}
}
