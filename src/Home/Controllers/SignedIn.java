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
    private static ObservableList<Employee> emps = FXCollections.observableArrayList();
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
    @FXML
    public TableColumn<Employee, String> FirstNameColumn;
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

            Employee employee = new Employee();
            if (!SearchWorkerFirstNameTextField.getText().equals(null))
                employee.setFirstName(SearchWorkerFirstNameTextField.getText().toLowerCase());
            if (!SearchWorkerLastNameTextField.getText().equals(null))
                employee.setLastName(SearchWorkerLastNameTextField.getText().toLowerCase());
            //getting a result set from the DB
            ResultSet rsRow = DatabaseHandler.getEmployee(employee);
            ObservableList<Employee> list = FXCollections.observableArrayList();

            try {

                int counter = 0;
                emps.clear();

                while (rsRow.next()) {
                    //going through each row of results
                    employee.setLastName(rsRow.getString(3).toUpperCase());
                    employee.setFirstName(rsRow.getString(2));
                    employee.setLevel(rsRow.getInt(5));
                    employee.setTitle(rsRow.getString(6));
                    employee.setUsername(rsRow.getString(4));
                    employee.setCertificateDate(rsRow.getDate(7).toLocalDate());
                    System.out.println(employee.toString() + " counter: " + counter);
                    //adding each employee to the observable list frookin emps
                    //emps.add(employee);
                    emps.add(counter, employee);
                    list.add(employee);
                    // System.out.println("showing employee from oblist 'emps': "+ emps.get(counter).toString());
                    counter++;
                }
                System.out.println("showing elements from observable list emps : " + counter + "  elements... ");
                for (int i = counter - 1; i != 0; i--) {
                    System.out.println("showing element number: '" + i + "'   from list: " + emps.get(i));
                }
                for (int i = counter - 1; i != 0; i--) {
                    System.out.println("showing element number: '" + i + "'   from list: " + list.get(i));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //ObservableList<Employee> emps = FXCollections.observableArrayList();
            FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            LastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            LevelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
            CertificateDateColumn.setCellValueFactory(new PropertyValueFactory<>("certificateDate"));
            //adding the observable list to the table view...
            EditWorkerTableView.setItems(emps);
        }));


    }
    /*public void refreshTable(){
        ObservableList<Employee> foundemployees = FXCollections.observableArrayList();
        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("title"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
        LevelColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("level"));
        CertificateDateColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("certificateDate"));
        EditWorkerTableView.setItems(foundemployees);
        EditWorkerTableView.refresh();

    }*/



}
