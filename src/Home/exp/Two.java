package Home.exp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Two implements Initializable{

    @FXML
    public Label label;
    @FXML
    private Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        //button.getStylesheets().add("Home/Resources/myCSS.css");
        System.out.println("Two initialized");



    }




}
