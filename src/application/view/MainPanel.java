package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import application.controller.MainController;
import application.controller.PopScreenController;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainPanel implements Initializable {

    private static final Logger log = Logger.getLogger(MainPanel.class);
    private MainController mainController;
    private Stage dialog;

    @FXML
    private Button buttonNum1;

    @FXML
    private Button buttonNum2;

    @FXML
    private Button buttonNum3;

    @FXML
    private Button buttonSend;

    @FXML
    private Label labelClose;

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

    @FXML
    private SwingNode swingNodeTest;

    @FXML
    private Button buttonPopScreen;

    @FXML
    private Button buttonShowTable;

    @FXML
    private ToggleButton buttonRecord;

    public MainPanel() {
        log.debug("");

        mainController = new MainController(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("initialize()");

        textFieldMessage.setOnKeyPressed(e -> {
            log.debug("myTextField key pressed" + e.getText());
            mainController.sendTextFieldKey(e);
        });

        newAnchorPane.setOnKeyPressed(e -> {
            log.debug("mainPane key pressed:" + e.getText());
            mainController.sendPaneKey(e);
        });

        disableMode();
    }

    public void popUpWindow01() {
        log.info("");

        if (dialog != null) {
            if (dialog.isShowing())
                return;
        } else {
            dialog = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/popScreen.fxml"));

                Scene scene = new Scene(loader.load());
                dialog.setScene(scene);
                dialog.setResizable(false);
                dialog.setWidth(500 + 15);
                dialog.setHeight(281 + 40);

                PopScreenController controller = loader.getController();
                controller.initData(mainController);

                dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent t) {
                        log.info("dialog.close()");
                        dialog.close();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dialog.show();
    }

    void setFullScreen(ActionEvent event) {
        log.debug("");

        Stage primaryStage = (Stage) buttonSend.getScene().getWindow();
        primaryStage.setFullScreen(true);
    }

    @FXML
    void buttonSendOnAction(ActionEvent event) {
        log.debug("");

        mainController.buttonSendOnAction();
    }

    @FXML
    void labelCloseClick() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void labelIconifiedClick() {
        Stage stage = (Stage) labelClose.getScene().getWindow();
        stage.setIconified(true);
    }

    public void testFeedback() {
        textFieldMessage.setText("ya");
    }

    public String getTextBoxMsg() {
        return textFieldMessage.getText();
    }

    public void disableMode() {
        log.debug("disableMode()");

        textFieldMessage.clear();
        textFieldMessage.setDisable(true);
        buttonSend.requestFocus();
    }

    public void enableMode() {
        log.debug("");

        textFieldMessage.setDisable(false);
        textFieldMessage.requestFocus();

        new Thread(() -> {
            try {
                Thread.sleep(10);
                textFieldMessage.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
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

    @FXML
    void buttonPopScreen(ActionEvent event) {
        log.debug("");
        popUpWindow01();
    }

    @FXML
    void buttonShowTableClick(ActionEvent event) {
        log.info("runAllCommands test");
        mainController.runAllCommands();
    }

    @FXML
    void buttonRecordClick(ActionEvent event) {
        if (buttonRecord.isSelected()) {
            mainController.startRecord();
        } else {
            mainController.stopRecord();
        }
    }

}