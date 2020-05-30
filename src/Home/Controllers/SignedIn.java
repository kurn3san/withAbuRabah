package Home.Controllers;

import Home.DatabaseHandling.DatabaseHandler;
import Home.model.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    @FXML
    public Button EmployeesManagementToEditWorkerButton;
    @FXML
    public AnchorPane EditWorkerPageAnchorPane;
    @FXML
    public Button EditWorkerSearchButton;
    @FXML
    public TextField SearchWorkerFirstNameTextField;
    @FXML
    public TextField SearchWorkerLastNameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        System.out.println("SignedIN initialised!");
        EmployeesManagementToAddWorkerButton.setOnAction((event) -> {
            System.out.println("Employees Management To Add Workerpage Button click!");
            AddWorkerPageAnchorPane.setVisible(true);
            EditWorkerPageAnchorPane.setVisible(false);
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
        EmployeesManagementToEditWorkerButton.setOnAction((actionEvent -> {
            System.out.println("on action Edit profile menu");
            AddWorkerPageAnchorPane.setVisible(false);
            EditWorkerPageAnchorPane.setVisible(true);

        }));

        EditWorkerSearchButton.setOnAction((actionEvent -> {
            Employee employee = new Employee();
            if (!SearchWorkerFirstNameTextField.getText().equals(""))
                employee.setFirstName(SearchWorkerFirstNameTextField.getText().toLowerCase());
            if (!SearchWorkerLastNameTextField.getText().equals(""))
                employee.setLastName(SearchWorkerLastNameTextField.getText().toLowerCase());
            ResultSet rsRow = DatabaseHandler.getEmployee(employee);

            try {

                while (rsRow.next()) {
                    employee.setLastName(rsRow.getString(3));
                    employee.setFirstName(rsRow.getString(2));
                    employee.setLevel(rsRow.getInt(5));
                    employee.setJobinfo(rsRow.getString(6));
                    employee.setUsername(rsRow.getString(4));
                    System.out.println(employee);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }));


    }
}
