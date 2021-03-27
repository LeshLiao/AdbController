package application;

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

public class MainProgram extends Application {
	@Override
	public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/NewView.fxml"));
            Scene scene = new Scene(root);

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
                    System.exit(0);
                }
            });

            primaryStage.setTitle("My Application");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		System.out.println("main");
		
		for(String str : args) {
			System.out.println(str);
		}
		
		launch(args);
	}
}
