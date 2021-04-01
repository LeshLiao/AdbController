package application;

import org.apache.log4j.xml.DOMConfigurator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class AdbMainProgram extends Application {
	@Override
	public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/NewView.fxml"));
            Scene scene = new Scene(root);

            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);

            primaryStage.initStyle(StageStyle.UNDECORATED);	// hide top border of stage

    		Image icon = new Image("icon/remote-control-1409191-1191921.png");

    		primaryStage.getIcons().add(icon);

            scene.setOnMousePressed(pressEvent -> {
            	scene.setOnMouseDragged(dragEvent -> {
	 		        primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
	 		        primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            	});
     	    });

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                }
            });

            primaryStage.setTitle("My Application");
            primaryStage.setScene(scene);
            primaryStage.show();

            //Logger log = Logger.getRootLogger();
            //log.setLevel(Level.INFO);
            //log.info("set log level:" + "Level.INFO");
            DOMConfigurator.configure("log/log4j.xml" );

        } catch(Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		System.out.println("MainProgram main()");
		launch(args);
	}

}
