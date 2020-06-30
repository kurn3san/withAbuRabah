package Home.Controllers;

import Home.Animations.Shaker;
import Home.DatabaseHandling.DatabaseHandler;
import Home.model.Admin;
import Home.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public ChoiceBox<String> SignInChoiceBox;
    DatabaseHandler dbh = new DatabaseHandler();
    //private static Connection connection =;
    protected static Admin signedInAdmin;
    protected static Employee signedInEmployee;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> ChoiceBoxObsrvblList = FXCollections.observableArrayList();
        ChoiceBoxObsrvblList.add("Admin");
        ChoiceBoxObsrvblList.add("Employee");
        SignInChoiceBox.setItems(ChoiceBoxObsrvblList);
        SignInChoiceBox.setValue("Admin");
        try {
            DatabaseHandler.connect();
            System.out.println("connected to database!!");
        } catch (Exception e) {
            System.out.println("connection failed!");
            e.printStackTrace();
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
        //signing in
        SignInButton.setOnAction((event) -> {
                    String signInUsername = SignInUsernameTextField.getText().trim().toLowerCase();
                    String SignInPassword = SignInPasswordField.getText().trim().toLowerCase();
                    // checking validity of fields' informatinos
                    if (SignInPassword.equals("")) System.out.println("please enter a pasword man.. come on!");
                    else if (SignInPassword.equals("") && signInUsername.equals("")) ErrorEmptyFieldsLabel.setVisible(true);
                    else if (signInUsername.equals("")) {
                        System.out.println("please enter a username man");
                        Shaker shaker = new Shaker(SignInUsernameTextField); // not working apparently...
                        shaker.shake();
                    } else if (!SignInPassword.equals("") && !signInUsername.equals("")) {
                        if (SignInChoiceBox.getValue().equals("Admin")) {
                            Admin admin = new Admin();
                            admin.setUserName(signInUsername);
                            admin.setPassword(SignInPassword);
                            System.out.println("SignInButton click!");
                            ErrorEmptyFieldsLabel.setVisible(false); // gotta move from here..
                            ResultSet userRow = DatabaseHandler.getAdmin(admin); //passing the query result set for finding matches
                            int counter = 0;
                            try {
                                while (userRow.next()) {
                                    counter++;
                                    admin.setUserName(userRow.getString(4));
                                    admin.setFirstName(userRow.getString(2));
                                    admin.setLastName(userRow.getString(3));
                                    admin.setLevel(userRow.getInt(6));
                                    System.out.println(admin.toString());
                                    signedInAdmin = admin;
                                }
                                if (counter == 1) {
                                    //checking for that one match...
                                    System.out.println("successful...");
                                    goToAdminSignedIn();
                                    //gotta move from hrereereererere
                                } else {
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
                                    if (counter == 1) {
                                        System.out.println("username exists. password incorrect.");
                                    } else {
                                        System.out.println("please stop wasting out time man...");
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ////Employee Sign in
                            Admin admin = new Admin();
                            Employee employee = new Employee();
                            employee.setUsername(signInUsername);
                            employee.setPassword(SignInPassword);
                            System.out.println("SignInButton click!");
                            ErrorEmptyFieldsLabel.setVisible(false);
                            signedInEmployee = DatabaseHandler.getEmployee(employee);
                            if (signedInEmployee != null) {
                                ///here the sign in is complete!
                                goToEmployeeSignedIn();

                            } else {
                                if (DatabaseHandler.isThereSuchNEmployee(employee)) {
                                    System.out.println("username exists... password wrong");
                                } else System.out.println("no such username...");
                            }
                        }
                    }
                }
        );
        SignUpButton.setOnAction((event) -> {
            System.out.println("Sign up Button click!");
            createUser();
        });

    }

    private void goToAdminSignedIn() {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Home/Views/AdminSignedIn.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
            System.out.println("exception");
            e.printStackTrace();
        }
        //button.getScene().getWindow().hide();
        Parent root = loader.getRoot();
        primaryStage.setTitle("Welcome!");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        ErrorEmptyFieldsLabel.getScene().getWindow().hide();
        primaryStage.show();
    }

    private void goToEmployeeSignedIn() {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Home/Views/EmployeeSignedIn.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
            System.out.println("exception");
            e.printStackTrace();
        }
        //button.getScene().getWindow().hide();
        Parent root = loader.getRoot();
        primaryStage.setTitle("Welcome!");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        ErrorEmptyFieldsLabel.getScene().getWindow().hide();
        primaryStage.show();
    }

    private void createUser() {

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
        if (DatabaseHandler.getDbConnection() != null) DatabaseHandler.signUpAdmin(admin);
        System.out.println("admin created and signed up!");

    }

}

