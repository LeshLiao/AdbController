package application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PopScreenController {
    @FXML
    private Button buttonFullScreen;

    @FXML
    void setFullScreen(ActionEvent event) {
    	System.out.print("full screen button");
    	Stage stage = (Stage) buttonFullScreen.getScene().getWindow();
    	stage.setFullScreen(true);
    	
    }

}
