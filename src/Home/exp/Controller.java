package Home.exp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//implements Initializable
import com.jfoenix.controls.JFXButton;

public class Controller implements Initializable{
    @FXML
    public Button button;
    @FXML
    private JFXButton button2;
    /*public void buttonfunk1 (Stage primaryStage)throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("jkhhk/Two.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();
        }*/
    @FXML
    public void buttonfunk(ActionEvent actionEvent) throws IOException {
        System.out.println("Button clicked");
        /*button.setOnAction((event) -> {
        System.out.println("Button clicked222222222");
        });*/
        System.out.println("launching stage two...");

        /*try{
            System.out.println("sleeping...");
            TimeUnit.SECONDS.sleep(3);
        }catch(Exception exception){}
        */


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("initialized!!");

        button2.setOnAction((event) -> {
            System.out.println("Button clicked");
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Home/exp/two.fxml"));
            try{
                loader.load();
            }catch(Exception e){
                System.out.println("exception");
                e.printStackTrace();
            }
            button.getScene().getWindow().hide();
            Parent root = loader.getRoot();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 500,777));
            primaryStage.show();
        });
    }


}
