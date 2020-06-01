package Home.Controllers;

import Home.Animations.Shaker;
import Home.DatabaseHandling.DatabaseHandler;
import Home.model.Admin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WelcomePageController implements Initializable{

    //Main Anchorpane
    @FXML
    public Label KorogementLabel;
    //SignUp Input Anchorpane
    @FXML
    public AnchorPane SignUpInputAnchorPane;    //on the Left side
    @FXML
    public Button SignUpButton;
    @FXML
    public Pane SignUpInputOuterPane;
    @FXML
    public Pane SignUpInnerPane;
    @FXML
    public Label SignUpFirstnameLabel;
    @FXML
    public TextField SignUpFirstnameTextField;
    @FXML
    public TextField SignUpLastNameTextField;
    @FXML
    public TextField SignUpUsernameTextField;
    @FXML
    public PasswordField SignUpPasswordField;
    @FXML
    public TextField SignUpActivatinoCodeTextField;
    @FXML
    public SVGPath SignUpGoToLogInSVGButton;        //on the right side
    //Log in inputs
    @FXML
    public AnchorPane LogInInputAnchorPane;
    @FXML
    public Pane SignInOuterPane;
    @FXML
    public Label SignInPasswordLabel;
    @FXML
    public PasswordField SignInPasswordField;
    @FXML
    public Button SignInButton;
    @FXML
    public TextField SignInUsernameTextField;
    @FXML
    public Label SignInUsernameLabel;
    @FXML
    public Pane SignUpInnerPane1;
    @FXML
    public AnchorPane SignInInputAnchorPane;
    @FXML
    public AnchorPane SignUpToSignInAnchorPane;
    @FXML
    public SVGPath SignInGoTOSingUpSVGButton;
    @FXML
    public AnchorPane SignInToSignUpAnchorPane;
    @FXML
    public Label ErrorEmptyFieldsLabel;
    DatabaseHandler dbh = new DatabaseHandler();
    //private static Connection connection =;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            DatabaseHandler.connect();
            //dbh.connect();
            //connection = dbh.getDbConnection();
            System.out.println("connected to database!!");
        } catch (SQLException e) {
            System.out.println("connection failed!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("connection failed!");
        }
        System.out.println("First View initialized!");
        //Main Screen

        //changes to Sign in menu
        SignUpGoToLogInSVGButton.setOnMouseClicked((event) -> {
            System.out.println("click!33");
            SignUpInputAnchorPane.setVisible(false);
            SignUpToSignInAnchorPane.setVisible(false);
            SignInInputAnchorPane.setVisible(true);
            SignInToSignUpAnchorPane.setVisible(true);



        });
        //Changes to the Sign UP Menu
        SignInGoTOSingUpSVGButton.setOnMouseClicked((event) -> {
            System.out.println("click!");
            SignUpInputAnchorPane.setVisible(true);
            SignUpToSignInAnchorPane.setVisible(true);
            SignInInputAnchorPane.setVisible(false);
            SignInToSignUpAnchorPane.setVisible(false);
            ErrorEmptyFieldsLabel.setVisible(false);
        });
        SignInButton.setOnAction((event) -> {
            String signInUsername = SignInUsernameTextField.getText().trim().toLowerCase();
            String SignInPassword = SignInPasswordField.getText().trim().toLowerCase();
            // checking validity of fields' informatinos
            if(signInUsername.equals("")) System.out.println("please enter a username man");
            Shaker shaker = new Shaker(SignInUsernameTextField); // not working apparently...
            shaker.shake();
            if(SignInPassword.equals("")) System.out.println("please enter a pasword man.. come on!");
            if(SignInPassword.equals("")&&signInUsername.equals("")) ErrorEmptyFieldsLabel.setVisible(true);
            if(!signInUsername.equals("")||!SignInPassword.equals("")){

                Admin admin = new Admin();
                admin.setUserName(signInUsername);
                admin.setPassword(SignInPassword);
            System.out.println("SignInButton click!");
            ErrorEmptyFieldsLabel.setVisible(false); // gotta move from here..
                ResultSet userRow = DatabaseHandler.getUser(admin); //passing the query result set for finding matches
            int counter = 0;
                try {
                    while (userRow.next()) {
                        counter++;
                        admin.setUserName(userRow.getString(4));
                        admin.setFirstName(userRow.getString(2));
                        admin.setLastName(userRow.getString(3));
                        admin.setLevel(userRow.getInt(6));
                        admin.toString();
                    }
                    if (counter == 1) {
                        //checking for that one match...
                        System.out.println("successful...");
                        goToSignedIn();
                        ErrorEmptyFieldsLabel.getScene().getWindow().hide();    //gotta move from hrereereererere
                    }else{
                        userRow = DatabaseHandler.getUserByUserName(admin);
                        while (userRow.next()) {
                            //System.out.println(userRow.getInt(1)+" "+userRow.getString(2));
                            admin.setUserName(userRow.getString(4));
                            admin.setFirstName(userRow.getString(2));
                            admin.setLastName(userRow.getString(3));
                            admin.setLevel(userRow.getInt(6));
                            System.out.println(admin.toString());
                            //System.out.println(admin.getUserName()+admin.getLastName()+admin.getFirstName());
                            counter++;
                        }
                        if(counter==1){
                            System.out.println("username exists. password incorrect.");
                        } else{
                            System.out.println("please stop wasting out time man...");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        SignUpButton.setOnAction((event) -> {

            System.out.println("Sign up Button click!");
            createUser();

        });

    }

    private void goToSignedIn() {

        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Home/Views/SignedIn.fxml"));
        try{
            loader.load();
        }catch(Exception e){
            System.out.println("exception");
            e.printStackTrace();
        }
        //button.getScene().getWindow().hide();
        Parent root = loader.getRoot();
        primaryStage.setTitle("Welcome!");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void createUser(){

        System.out.println("CREATING USER...");
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String firstName = SignUpFirstnameTextField.getText().toLowerCase();
        String lastName = SignUpLastNameTextField.getText().toLowerCase();
        String userName = SignUpUsernameTextField.getText().toLowerCase();
        String Password = SignUpPasswordField.getText();
        int level = 1;
        try{
             level = Integer.parseInt(SignUpActivatinoCodeTextField.getText());

        } catch (Exception e){
            e.printStackTrace();
        }

        Admin admin = new Admin(firstName, lastName, userName, Password, level);
        System.out.println(firstName + lastName+ userName+Password+ level );
        if (DatabaseHandler.getDbConnection() != null) DatabaseHandler.SignUpUser(admin);
        System.out.println("admin created and signed up!");

    }

}

