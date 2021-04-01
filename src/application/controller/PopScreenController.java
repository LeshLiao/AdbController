package application.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import name.schedenig.adbcontrol.AdbControlPanel;
import name.schedenig.adbcontrol.AdbHelper;
import name.schedenig.adbcontrol.Config;

public class PopScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPaneMain;

    @FXML
    private Pane paneSub;

    @FXML
    private Button buttonFullScreen;

    @FXML
    private Button buttonSet1000x562;

    @FXML
    private Button buttonSet1920x1080;

    @FXML
    private Button buttonSet1440x810;

    @FXML
    private Button buttonSet500x281;

    @FXML
    private SwingNode swingNodePopScreen;


    AdbControlPanel panel;
    File configFile;
    Config config;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("PopScreenController initialize");

		configFile = new File("config.properties");
		config = new Config();
		try(FileInputStream in = new FileInputStream(configFile))
		{
			config.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		runScreenshotMonitor();



		new Thread() {
			  @Override
			  public void run() {
				try {
					sleep(100);

					Stage stage = (Stage) buttonFullScreen.getScene().getWindow();
					stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			            @Override
			            public void handle(WindowEvent t) {
			            	System.out.println("setOnCloseRequest");
			            	panel.stopUpdateThread();
			            }
			        });
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			  }
		}.start();
	}

	public void initData(MainController mainController) {
		System.out.println("PopScreenController initData()");

		mainController.printTest("hihihi im from PopScreenController");
    }

    @FXML
    void setFullScreen(ActionEvent event) {
    	Stage stage = (Stage) buttonFullScreen.getScene().getWindow();
    	System.out.println("setFullScreen");

    	paneSub.setLayoutX(stage.getX());
    	paneSub.setLayoutY(stage.getY());
    	stage.setFullScreen(true);
    }

    void cancelFullScreen() {
    	Stage stage = (Stage) buttonFullScreen.getScene().getWindow();
    	stage.setFullScreen(false);
    }

	public void runScreenshotMonitor() {
		System.out.println("runScreenshotMonitor");

		panel = new AdbControlPanel(config);

		panel.setAdbHelper(new AdbHelper(config));
		panel.setSize(500, 281);//1920:1080
		panel.setLayout(null);
		swingNodePopScreen.setContent(panel);
	}

    @FXML
    void setSize1920x1080(ActionEvent event) {
    	System.out.println("setSize1920x1080");
    	ResetScreenSize(1920, 1080);
    }

    @FXML
    void setSize1440x810(ActionEvent event) {
    	System.out.println("setSize1440x810");
    	ResetScreenSize(1440, 810);
    }

    @FXML
    void setSize1000x562(ActionEvent event) {
    	System.out.println("setSize1000x562");
    	ResetScreenSize(1000, 562);
    }

    @FXML
    void setSize500x281(ActionEvent event) {
    	System.out.println("setSize500x281");
    	ResetScreenSize(500, 281);
    }

    public void ResetScreenSize(int Width, int Height) {
    	cancelFullScreen();
    	setAnchorPaneMainToZero();

    	panel.stopUpdateThread();

		panel = new AdbControlPanel(config);
		panel.setAdbHelper(new AdbHelper(config));
		panel.setSize(Width, Height);
		panel.setLayout(null);
		swingNodePopScreen.setContent(panel);
		Stage stage = (Stage) buttonFullScreen.getScene().getWindow();
		stage.setWidth(Width+15);
		stage.setHeight(Height+40);
		stage.centerOnScreen();

		if(stage.getY() < 0) {
			stage.setX(0);
			stage.setY(0);
		}
    }

    @FXML
    void anchorPaneOnKeyPressed(KeyEvent e) {
        if(e.getCode() == KeyCode.ESCAPE) {
        	setAnchorPaneMainToZero();
        }
    }

    void setAnchorPaneMainToZero() {
    	paneSub.setLayoutX(0);
    	paneSub.setLayoutY(0);
    }


}
