package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import application.controller.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class MainPanel implements Initializable {

	private MainController mainController;

    @FXML
    private Button buttonNum1;

    @FXML
    private Button buttonNum2;

    @FXML
    private Button buttonNum3;

    @FXML
    private Button buttonSend;

    @FXML
    private Label labelText;

    @FXML
    private TextField textFieldMessage;

    @FXML
    private AnchorPane newAnchorPane;

    @FXML
    private Button buttonNum8;

    @FXML
    private Button buttonNum9;

    @FXML
    private Button buttonNum4;

    @FXML
    private Button buttonNum5;

    @FXML
    private Button buttonNum6;

    @FXML
    private Button buttonNum7;

	public MainPanel() {
		mainController = new MainController(this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialize");

		textFieldMessage.setOnKeyPressed(e -> {
			System.out.println("myTextField key pressed");
			mainController.sendTextFieldKey(e);
		});

		newAnchorPane.setOnKeyPressed(e -> {
			System.out.println("mainPane key pressed:" + e.getText());
			mainController.sendPaneKey(e);
		});

		disableMode();
	}

    @FXML
    void buttonSendOnAction(ActionEvent event) {
    	mainController.buttonSendOnAction();
    }

	@FXML
	void labelCloseClick() {
		Platform.exit();
	    System.exit(0);
	}

    public void testFeedback() {
    	textFieldMessage.setText("ya");
    }

    public String getTextBoxMsg() {
    	return textFieldMessage.getText();
    }

    public void disableMode() {
    	textFieldMessage.clear();
    	labelText.setDisable(true);
    	//buttonSend.setDisable(true);
    	textFieldMessage.setDisable(true);
    	labelText.requestFocus();
    }

	public void enableMode() {

		textFieldMessage.clear();
		labelText.setDisable(false);
		//buttonSend.setDisable(false);
		textFieldMessage.setDisable(false);
		textFieldMessage.requestFocus();

		new Thread() {
		  @Override
		  public void run() {
			try {
				sleep(5);
				textFieldMessage.clear();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
		}.start();

	}


    @FXML
    void buttonNum2Click(ActionEvent event) {
    	mainController.sendPaneKey(new KeyEvent(null, null, null, KeyCode.NUMPAD2, false, false, false, false));
    }

    @FXML
    void buttonNum4Click(ActionEvent event) {
    	mainController.sendPaneKey(new KeyEvent(null, null, null, KeyCode.NUMPAD4, false, false, false, false));
    }

    @FXML
    void buttonNum5Click(ActionEvent event) {
    	mainController.sendPaneKey(new KeyEvent(null, null, null, KeyCode.NUMPAD5, false, false, false, false));
    }

    @FXML
    void buttonNum6Click(ActionEvent event) {
    	mainController.sendPaneKey(new KeyEvent(null, null, null, KeyCode.NUMPAD6, false, false, false, false));
    }

    @FXML
    void buttonNum7Click(ActionEvent event) {
    	mainController.sendPaneKey(new KeyEvent(null, null, null, KeyCode.NUMPAD7, false, false, false, false));
    }

    @FXML
    void buttonNum8Click(ActionEvent event) {
    	mainController.sendPaneKey(new KeyEvent(null, null, null, KeyCode.NUMPAD8, false, false, false, false));
    }

    @FXML
    void buttonNum9Click(ActionEvent event) {
    	mainController.sendPaneKey(new KeyEvent(null, null, null, KeyCode.NUMPAD9, false, false, false, false));
    }

}
