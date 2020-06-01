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

public class SignedIn implements Initializable {
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
    @FXML
    public Button EmployeesManagementToDeleteProfileButton;
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
    public TextField AddEmployeeLevelCodeTextField;
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
    Employee selectedEmployee = new Employee();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<Employee> emps = FXCollections.observableArrayList();
        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        LevelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        CertificateDateColumn.setCellValueFactory(new PropertyValueFactory<>("certificateDate"));
        /*FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        LevelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        CertificateDateColumn.setCellValueFactory(new PropertyValueFactory<>("certificateDate"));
        EditWorkerTableView.setItems(foundemployees);*/
        AddWorkerPageAnchorPane.setVisible(false);
        EditWorkerPageAnchorPane.setVisible(false);

        System.out.println("SignedIN initialised!");

        EmployeesManagementToAddWorkerButton.setOnAction((event) -> {
            System.out.println("Employees Management To Add Workerpage Button click!");
            AddWorkerPageAnchorPane.setVisible(true);
            EditWorkerPageAnchorPane.setVisible(false);
        });

        AddEmployeeButton.setOnAction((event) -> {

            System.out.println("Add Employee Button click!");

            String firstName = AddEmployeeFirstnameTextField.getText() ;
            String lastName = AddEmployeeLastNameTextField.getText() ;
            String userName = AddEmployeeUsernameTextField.getText() ;
            LocalDate localDate = AddEmployeeDatePicker.getValue();
            LocalDate cDate = null;
            if (localDate == null) System.out.println("error pick date!");
            else {
                /*Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                java.util.Date date =  Date.from(instant);
             cDate =(java.sql.Date) date;*/
                System.out.println(localDate);
            }

            int level = 1;
            try{
                level = Integer.parseInt(AddEmployeeLevelCodeTextField.getText());
            }catch(Exception e ){
                e.printStackTrace();
            }
            String jobinfo = AddEmployeeTitelField.getText();
            System.out.println(firstName + lastName+ userName+level );
            DatabaseHandler.AddEmployee(new Employee(firstName, lastName, userName, level, jobinfo, localDate));
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
            ResultSet rsRow = new DatabaseHandler().getEmployee(employee1);
            ObservableList<Employee> list = FXCollections.observableArrayList();
            EditWorkerTableView.setItems(getObservableListOfEmployees(rsRow));


        }));

        EditWorkerTableView.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    selectedEmployee = row.getItem();
                    EditFirstNameTextField.setText(selectedEmployee.getFirstName());
                    EditLastNameTextField.setText(selectedEmployee.getLastName());
                    EditTitleTextField.setText(selectedEmployee.getTitle());
                    EditLevelChoiceBox.setValue(selectedEmployee.getLevel());///

                    System.out.println(selectedEmployee.toString());
                }
            });
            return row;
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
            //adding each employee to the observable list frookin emps
            //emps.add(employee);
            list.add(counter, employee);
            // System.out.println("showing employee from oblist 'emps': "+ emps.get(counter).toString());
            counter++;
        }
        System.out.println("showing elements from observable list emps : " + counter + "  elements... ");
        for (int i = counter - 1; i != 0; i--) {
            System.out.println("showing element number: '" + i + "'   from list: " + list.get(i));
        }
        return list;
    }

}
