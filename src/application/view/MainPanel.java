package application.view;

import application.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainPanel {

	private MainController mainController;

	public MainPanel() {
		mainController = new MainController(this);
	}

    @FXML
    private Button buttonSend;

    @FXML
    private Label labelText;

    @FXML
    private TextField textFieldMessage;

    @FXML
    void buttonSendOnAction(ActionEvent event) {
    	mainController.buttonSendOnAction();

    }
}
