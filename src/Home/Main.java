package Home;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Views/WelcomePage.fxml"));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Welcome Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        System.out.println("First View running...");
        /*  Stage primaryStage = new Stage();
        //String uurl = this.getClass().getResource("/Home/exp/two.fxml").toExternalForm();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Home/exp/sample.fxml"));
        try{
            loader.load();
        }catch(Exception e){
            System.out.println("exception");
            e.printStackTrace();
        }
        //button.getScene().getWindow().hide();
        Parent root = loader.getRoot();
        primaryStage.setTitle("two");
        Scene scene = new Scene(root, 300,540);
        primaryStage.setScene(scene);
        primaryStage.show();*/

    }
    public static void main(String[] args) {
        launch(args);
    }
}
