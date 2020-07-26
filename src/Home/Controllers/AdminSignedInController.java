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
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class AdminSignedInController implements Initializable {
    private static Employee selectedEmployee = new Employee();
    private static ResultSet onhandResultset = null;
    //edit general info stuff...
    @FXML
    public Button LogoutButton;
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
    public CheckBox EditEmployeeChangePasswordCheckBox;
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
            EnterNewPasswordFirstPasswordField.clear();
            EnterOldPasswordPasswordField.clear();
            EnterNewPasswordSecondPasswordField.clear();
            EditEmployeeChangePasswordCheckBox.setSelected(false);
            clearEditFields();
        });
        LogoutButton.setOnAction(event -> {
            LogoutButton.getScene().getWindow().hide();
            WelcomePageController.showWlcPageWindow();
            WelcomePageController.signedInAdmin = null;
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
        });
        AddWorkerClearAllFieldsButton.setOnAction(event -> {
            AddEmployeeFirstnameTextField.clear();
            AddEmployeeLastNameTextField.clear();
            AddEmployeeTitelField.clear();
            AddEmployeeFirstPasswordField.clear();
            AddEmployeeSecondPasswordField.clear();
            AddEmployeeUsernameTextField.clear();
        });
        EmployeesManagementToEditWorkerButton.setOnAction((actionEvent -> {
            System.out.println("on action Edit profile menu");
            AddEmployeeFirstnameTextField.clear();
            AddEmployeeLastNameTextField.clear();
            AddEmployeeTitelField.clear();
            AddEmployeeFirstPasswordField.clear();
            AddEmployeeSecondPasswordField.clear();
            AddEmployeeUsernameTextField.clear();
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
            onhandResultset = DatabaseHandler.getEmployeeResultSet(employee1);
            EditWorkerTableView.setItems(getObservableListOfEmployees(onhandResultset));
            clearEditFields();
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
                    try {
                        onhandResultset.first();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Employee employee = new Employee();
                    int counter = 0;
                    while (true) {
                        try {
                            //going through each row of results
                            employee.setLastName(onhandResultset.getString(3).toUpperCase());
                            employee.setFirstName(onhandResultset.getString(2));
                            employee.setLevel(onhandResultset.getInt(5));
                            employee.setTitle(onhandResultset.getString(6));
                            employee.setUsername(onhandResultset.getString(4));
                            employee.setCertificateDate(onhandResultset.getDate(7).toLocalDate());
                            employee.setPassword(onhandResultset.getString(8));
                            if (employee.equals(selectedEmployee)) {
                                selectedEmployee = employee;
                                break;
                            }
                            if (!onhandResultset.next()) break;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Employee added to list!" + employee.toString() + " counter: " + counter);
                        //adding each employee to the observable list  list
                        // System.out.println("showing employee from oblist 'emps': "+ emps.get(counter).toString());
                        counter++;
                    }
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
            if (DatabaseHandler.updateEmployee(selectedEmployee)) System.out.println("done!");
            else
                System.out.println("the soviet union is refusing to cooperate, time to bring em some taste of freedom!");
            try {
                ResultSet rsRow = DatabaseHandler.getEmployeeResultSet(selectedEmployee);
                EditWorkerTableView.setItems(getObservableListOfEmployees(rsRow));
            } catch (NullPointerException e) {
                System.out.println("nothing is selected nigga!");
                e.printStackTrace();
            }
            EditSelectedEmployeesSaveButton.setVisible(false);
            DeleteProfileButton.setVisible(false);
            SaveNewPasswordButton.setVisible(false);
            refreshEditTable();
        });
        SaveNewPasswordButton.setOnAction((event -> {
            ////what to do to save new password...
            ///first you read the passwords
            ///then you save it!
            ///bravoo!!
            if (EditEmployeeChangePasswordCheckBox.isSelected()) {
                System.out.println("selected emp pass: " + selectedEmployee.getPassword() + " first password field was: " + EnterNewPasswordFirstPasswordField.getText() +
                        " second password field was: " + EnterNewPasswordSecondPasswordField.getText() + " entered old pass was: " + EnterOldPasswordPasswordField.getText());
                if ((EnterNewPasswordFirstPasswordField.getText().equals(EnterNewPasswordSecondPasswordField.getText())) && (EnterOldPasswordPasswordField.getText().equals(selectedEmployee.getPassword()))) {
                    Employee tempemp = new Employee();
                    tempemp = selectedEmployee;
                    tempemp.setPassword(EnterNewPasswordSecondPasswordField.getText());
                    selectedEmployee.setPassword(EnterNewPasswordSecondPasswordField.getText());//not important...
                    System.out.println("Tempemp pass was: " + tempemp.getPassword() + "SelectedEmployee's new pass was:" + selectedEmployee.getPassword());//not important...
                    ////////
                    if (DatabaseHandler.changeEmployeePassword(tempemp)) {
                        System.out.println("you got it!");
                        refreshEditTable();//success!
                    } else System.out.println("check the stuff...");
                } else if (!EnterNewPasswordFirstPasswordField.getText().equals(EnterNewPasswordSecondPasswordField.getText())) {
                    System.out.println("the stuff you entered won't match.. you better get more focused or you'll have much much problems man/mam... honestly get your shit together... all of it!");
                } else if (!EnterNewPasswordFirstPasswordField.getText().equals(selectedEmployee.getPassword())) {
                    System.out.println("old password was wrong...");
                }
            } else System.out.println("oooopssss... checkbox!");
            EnterNewPasswordFirstPasswordField.clear();
            EnterOldPasswordPasswordField.clear();
            EnterNewPasswordSecondPasswordField.clear();
            EditEmployeeChangePasswordCheckBox.setSelected(false);
        }));
        DeleteProfileButton.setOnAction(event -> {
            if (DatabaseHandler.deleteEmployee(selectedEmployee)) System.out.println("it happened!");
            else System.out.println("didn't happen!");
            refreshEditTable();
        });
    }
    public ObservableList getObservableListOfEmployees(@NotNull ResultSet rsRow) {
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
    public void clearEditFields() {
        EditFirstNameCheckBox.setSelected(false);
        EditFirstNameTextField.clear();
        EditLastNameCheckBox.setSelected(false);
        EditLastNameTextField.clear();
        EditTitlecheckBox.setSelected(false);
        EditTitleTextField.clear();
        EditLevelCheckBox.setSelected(false);
        EditLevelChoiceBox.setValue(1);
        EditCDateCheckBox.setSelected(false);
        EditCdateDatePicker.setValue(LocalDate.now());
        DeleteProfileButton.setVisible(false);
        SaveNewPasswordButton.setVisible(false);
        EditSelectedEmployeesSaveButton.setVisible(false);
    }
    public void refreshEditTable() {
        ResultSet rsRow = DatabaseHandler.freshListOfEmployees();
        //ObservableList<Employee> list = FXCollections.observableArrayList();
        EditWorkerTableView.setItems(getObservableListOfEmployees(rsRow));
        clearEditFields();
    }

}
