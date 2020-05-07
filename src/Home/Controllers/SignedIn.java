package Home.Controllers;

import Home.Databases.DatabaseHandler;
import Home.model.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SignedIn implements Initializable {
    @FXML
    public Button AddEmployeeButton;
    @FXML
    public TextField AddEmployeeFirstnameTextField;
    @FXML
    public TextField AddEmployeeLastNameTextField;
    @FXML
    public TextField AddEmployeeUsernameTextField;
    @FXML
    public TextField AddEmployeeLevelCodeTextField;
    @FXML
    public PasswordField AddEmployeeJobinfoField;
    @FXML
    public Button EmployeesManagementToAddWorkerButton;
    @FXML
    public AnchorPane AddWorkerPageAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        EmployeesManagementToAddWorkerButton.setOnAction((event) -> {
            System.out.println("Employees Management To Add Workerpage Button click!");
            AddWorkerPageAnchorPane.setVisible(true);

        });

        AddEmployeeButton.setOnAction((event) -> {


            DatabaseHandler databaseHandler = new DatabaseHandler();

            System.out.println("Add Employee Button click!");

            String firstName = AddEmployeeFirstnameTextField.getText() ;
            String lastName = AddEmployeeLastNameTextField.getText() ;
            String userName = AddEmployeeUsernameTextField.getText() ;
            int level = 1;
            try{
                level = Integer.parseInt(AddEmployeeJobinfoField.getText());
            }catch(Exception e ){
            }
            String jobinfo = AddEmployeeJobinfoField.getText();
            System.out.println(firstName + lastName+ userName+level );
            databaseHandler.AddEmployee(new Employee(firstName, lastName, userName, level,jobinfo));
            System.out.println("added maan!");
        });

    }
}
