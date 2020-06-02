package Home.Controllers;

import Home.DatabaseHandling.DatabaseHandler;
import Home.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminSignedInController implements Initializable {
    private static Employee selectedEmployee = new Employee();
    //edit general info stuff...
    @FXML
    public TextField EditFirstNameTextField;
    @FXML
    public CheckBox EditFirstNameCheckBox;
    @FXML
    public TextField EditLastNameTextField;
    @FXML
    public CheckBox EditLastNameCheckBox;
    @FXML
    public CheckBox EditTitlecheckBox;
    @FXML
    public TextField EditTitleTextField;
    @FXML
    public CheckBox EditLevelCheckBox;
    @FXML
    public ChoiceBox EditLevelChoiceBox;
    @FXML
    public CheckBox EditCDateCheckBox;
    @FXML
    public DatePicker EditCdateDatePicker;
    @FXML
    public Button EditSelectedEmployeesSaveButton;

    //password tab stuff
    @FXML
    public PasswordField EnterNewPasswordFirstPasswordField;
    @FXML
    public PasswordField EnterNewPasswordSecondPasswordField;
    @FXML
    public PasswordField EnterOldPasswordPasswordField;
    @FXML
    public Button SaveNewPasswordButton;
    @FXML
    public CheckBox ChangePasswordCheckBox;

    //Table view stuff
    @FXML
    public TableColumn<Employee, String> FirstNameColumn;
    @FXML
    public Button AddEmployeeButton;
    @FXML
    public TextField AddEmployeeFirstnameTextField;
    @FXML
    public TextField AddEmployeeLastNameTextField;
    @FXML
    public TextField AddEmployeeUsernameTextField;
    @FXML
    public ChoiceBox AddEmployeeLevelChoiceBox;
    @FXML
    public TextField AddEmployeeTitelField;
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
    @FXML
    public DatePicker AddEmployeeDatePicker;
    @FXML
    public TableColumn<Employee, String> LastNameColumn;
    @FXML
    public TableColumn<Employee, String> titleColumn;
    @FXML
    public TableColumn<Employee, String> UsernameColumn;
    @FXML
    public TableColumn<Employee, Integer> LevelColumn;
    @FXML
    public TableColumn<Employee, LocalDate> CertificateDateColumn;
    @FXML
    public TableView<Employee> EditWorkerTableView;
    public Button DeleteProfileButton;
    public Button AddWorkerClearAllFieldsButton;
    public PasswordField AddEmployeeSecondPasswordField;
    public PasswordField AddEmployeeFirstPasswordField;
    public Label CurrentSessionInfoLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Current session: Admin:
        CurrentSessionInfoLabel.setText("Current session: Admin:  " + WelcomePageController.signedInAdmin.toString());
        AddWorkerPageAnchorPane.setVisible(false);
        EditWorkerPageAnchorPane.setVisible(false);
        System.out.println("SignedIN initialised!");
        System.out.println("Admin: " + WelcomePageController.signedInAdmin.toString());
        ObservableList<Integer> EditLevelChoiceBoxItems = FXCollections.observableArrayList();
        EditLevelChoiceBoxItems.add(1);
        EditLevelChoiceBoxItems.add(2);
        EditLevelChoiceBoxItems.add(3);
        EditLevelChoiceBoxItems.add(4);
        EditLevelChoiceBoxItems.add(5);
        EditLevelChoiceBox.setItems(EditLevelChoiceBoxItems);
        AddEmployeeLevelChoiceBox.setItems(EditLevelChoiceBoxItems);
        AddEmployeeLevelChoiceBox.setValue(1);
        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        LevelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        CertificateDateColumn.setCellValueFactory(new PropertyValueFactory<>("certificateDate"));

        EmployeesManagementToAddWorkerButton.setOnAction((event) -> {
            System.out.println("Employees Management To Add Workerpage Button click!");
            AddWorkerPageAnchorPane.setVisible(true);
            EditWorkerPageAnchorPane.setVisible(false);
        });

        AddEmployeeButton.setOnAction((event) -> {

            System.out.println("Add Employee Button click!");

            String firstName = AddEmployeeFirstnameTextField.getText();
            String lastName = AddEmployeeLastNameTextField.getText();
            String userName = AddEmployeeUsernameTextField.getText();
            LocalDate localDate = AddEmployeeDatePicker.getValue();
            String password = AddEmployeeFirstPasswordField.getText();
            String passConf = AddEmployeeSecondPasswordField.getText();
            if (localDate == null) System.out.println("error pick date!");
            else System.out.println(localDate);
            int level = 1;
            try {
                System.out.println("Level is: " + level);
                level = (Integer) AddEmployeeLevelChoiceBox.getValue();
                if (password.equals(passConf) && !password.equals("")) System.out.println("two passwords match...");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String jobinfo = AddEmployeeTitelField.getText();
            System.out.println(firstName + lastName + userName + level + password);
            if (!DatabaseHandler.isThereSuchNEmployee(new Employee(firstName, lastName, userName, level, jobinfo, localDate, password)))
                DatabaseHandler.AddEmployee(new Employee(firstName, lastName, userName, level, jobinfo, localDate, password));
            else System.out.println("this username is taken man... sorry...");
            System.out.println("added maan!");
        });
        EmployeesManagementToEditWorkerButton.setOnAction((actionEvent -> {

            System.out.println("on action Edit profile menu");
            AddWorkerPageAnchorPane.setVisible(false);
            EditWorkerPageAnchorPane.setVisible(true);

        }));
        EditWorkerSearchButton.setOnAction((actionEvent -> {

            Employee employee1 = new Employee();
            if (!SearchWorkerFirstNameTextField.getText().equals(null))
                employee1.setFirstName(SearchWorkerFirstNameTextField.getText().toLowerCase());
            if (!SearchWorkerLastNameTextField.getText().equals(null))
                employee1.setLastName(SearchWorkerLastNameTextField.getText().toLowerCase());
            //getting a result set from the DB
            ResultSet rsRow = DatabaseHandler.getEmployee(employee1);
            //ObservableList<Employee> list = FXCollections.observableArrayList();
            EditWorkerTableView.setItems(getObservableListOfEmployees(rsRow));
        }));
        EditWorkerTableView.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    selectedEmployee = row.getItem();
                    EditFirstNameTextField.setText(selectedEmployee.getFirstName());
                    EditFirstNameCheckBox.setSelected(false);
                    EditLastNameTextField.setText(selectedEmployee.getLastName());
                    EditLastNameCheckBox.setSelected(false);
                    EditTitleTextField.setText(selectedEmployee.getTitle());
                    EditTitlecheckBox.setSelected(false);
                    EditLevelChoiceBox.setValue(selectedEmployee.getLevel());///
                    EditLevelCheckBox.setSelected(false);
                    EditCdateDatePicker.setValue(selectedEmployee.getCertificateDate());
                    EditCDateCheckBox.setSelected(false);
                    System.out.println(selectedEmployee.toString());
                    EditSelectedEmployeesSaveButton.setVisible(true);
                    DeleteProfileButton.setVisible(true);
                    SaveNewPasswordButton.setVisible(true);
                }
            });
            return row;
        });
        EditSelectedEmployeesSaveButton.setOnAction((event) -> {
            System.out.println("saving selected info...");
            if ((EditFirstNameCheckBox.isSelected()) && (!EditFirstNameTextField.equals("")))
                selectedEmployee.setFirstName(EditFirstNameTextField.getText().toLowerCase());
            if ((EditLastNameCheckBox.isSelected()) && (!EditLastNameTextField.equals("")))
                selectedEmployee.setLastName(EditLastNameTextField.getText().toLowerCase());
            if ((EditTitlecheckBox.isSelected()) && (!EditTitleTextField.equals("")))
                selectedEmployee.setTitle(EditTitleTextField.getText().toLowerCase());
            if (EditLevelCheckBox.isSelected()) selectedEmployee.setLevel((Integer) EditLevelChoiceBox.getValue());
            if (EditCDateCheckBox.isSelected()) selectedEmployee.setCertificateDate(EditCdateDatePicker.getValue());
            //sending to database Handler update function

            ///////////
            DatabaseHandler.updateEmployee(selectedEmployee);
            Employee employee1 = new Employee();
            try {
                ResultSet rsRow = DatabaseHandler.getEmployee(selectedEmployee);
                EditWorkerTableView.setItems(getObservableListOfEmployees(rsRow));
            } catch (NullPointerException e) {
                System.out.println("nothing is selected nigga!");
                e.printStackTrace();
            }
            EditSelectedEmployeesSaveButton.setVisible(false);
            DeleteProfileButton.setVisible(false);
            SaveNewPasswordButton.setVisible(false);

            //////////
        });
    }

    public ObservableList getObservableListOfEmployees(ResultSet rsRow) {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        int counter = 0;
        list.clear();

        while (true) {
            Employee employee = new Employee();
            try {
                //going through each row of results
                if (!rsRow.next()) break;
                employee.setLastName(rsRow.getString(3).toUpperCase());
                employee.setFirstName(rsRow.getString(2));
                employee.setLevel(rsRow.getInt(5));
                employee.setTitle(rsRow.getString(6));
                employee.setUsername(rsRow.getString(4));
                employee.setCertificateDate(rsRow.getDate(7).toLocalDate());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Employee added to list!" + employee.toString() + " counter: " + counter);
            //adding each employee to the observable list  list
            list.add(counter, employee);
            // System.out.println("showing employee from oblist 'emps': "+ emps.get(counter).toString());
            counter++;
        }
        return list;
    }

}
