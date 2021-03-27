package application.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PopScreen {

	public PopScreen() {
		Stage dialog = new Stage();
		
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/view/popScreen.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		
        

        //dialog.initModality(Modality.APPLICATION_MODAL);
        //dialog.initOwner(primaryStage);
        //VBox dialogVbox = new VBox(20);
        ///dialogVbox.getChildren().add(new Text("This is a Dialog"));
        //Scene dialogScene = new Scene(dialogVbox, 300, 200);
        
        //dialog.setFullScreen(true);
        
        dialog.setScene(scene);
        dialog.show();
	}
}
