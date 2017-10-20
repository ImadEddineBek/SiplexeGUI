package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Controller {
    public void lancer(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("progam/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 794, 681));
        primaryStage.show();
    }
}
