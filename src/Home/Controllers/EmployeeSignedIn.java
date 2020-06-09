package Home.Controllers;

import Home.DatabaseHandling.DatabaseHandler;
import Home.model.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class EmployeeSignedIn implements Initializable {
    private static ResultSet onhandCompaniesResultset = null;
    private static Company onHandCompany = new Company();
    private static Company selectedCompany = new Company();
    public AnchorPane addCompanyAnchorPane;
    public AnchorPane createReportAnchorPane;
    public Label currentEmployeeSessionLabel;
    public Button MenuToCompanySettings;
    public Button addCompanySaveButton;
    public Button companySettingsCancelButton;
    public Button addCompanyClearAllButton;
    public TextField addCompanyCityTextField;
    public TextField addCompanyNameTextField;
    public TableView<Company> CompaniesTableView;
    public TableColumn<Company, String> CompanyNameColumn;
    public TableColumn<Company, String> CompanyAddressColumn;
    public TableColumn<Company, Date> RgistrationDateCllumn;
    public TextField SearchCompaniesNameTextField;
    public SplitPane companySettingsPane;
    public Button SearchCompaniesButton;
    public Button companiesSaveNewSettingsButton;
    public Label selectedCompnyLabel;
    //
    ObservableList<Company> companies = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companySettingsPane.setVisible(false);
        currentEmployeeSessionLabel.setText("Current session employee: " + WelcomePageController.signedInEmployee.toString());
        CompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        CompanyAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        RgistrationDateCllumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));

        MenuToCompanySettings.setOnAction(event -> {
            companySettingsPane.setVisible(true);
            Company company = new Company();
            refreshCompaniesTable(company);
            if (selectedCompany.getName() != null) {
                selectedCompnyLabel.setText("Selected company: " + selectedCompany.toString());
            } else {
                selectedCompnyLabel.setText("No company selected!");
            }
        });

        addCompanyClearAllButton.setOnAction(event -> {
            addCompanyClearAll();
        });

        addCompanySaveButton.setOnAction(event -> {
            if (!addCompanyNameTextField.equals("") && !addCompanyCityTextField.equals("")) {
                Company company = new Company(addCompanyNameTextField.getText().toLowerCase(),
                        addCompanyCityTextField.getText().toLowerCase(),
                        new Date(Calendar.getInstance().getTime().getTime()));
                if (!DatabaseHandler.isThereSuchACompany(company)) {
                    DatabaseHandler.addCompany(company);
                    addCompanyClearAll();
                    SearchCompaniesNameTextField.clear();
                    refreshCompaniesTable(new Company());
                    onHandCompany = company;
                    selectedCompnyLabel.setText("company: " + onHandCompany.toString() + " will be selected, don't forget to save!");
                } else System.out.println("company already exists with the same name and same address");
            } else System.out.println("not enough infos");
        });
        CompaniesTableView.setRowFactory(tv -> {
            TableRow<Company> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) onHandCompany = row.getItem();
                selectedCompnyLabel.setText("company: " + onHandCompany.toString() + " will be selected, don't forget to save!");
            });
            return row;
        });

        companySettingsCancelButton.setOnAction(event -> {
            addCompanyClearAll();
            companySettingsPane.setVisible(false);
            SearchCompaniesNameTextField.clear();
        });

        SearchCompaniesButton.setOnAction(event -> {
            Company company = new Company();
            if (!SearchCompaniesNameTextField.getText().equals(""))
                company.setName(SearchCompaniesNameTextField.getText().toLowerCase());
            refreshCompaniesTable(company);
        });
        companiesSaveNewSettingsButton.setOnAction(event -> {
            if (onHandCompany != null) {
                selectedCompany = onHandCompany;

            } else {
                System.out.println("no selected companies");
            }
            companySettingsPane.setVisible(false);

            //onHandCompany
        });
        companySettingsCancelButton.setOnAction(event -> {
            addCompanyClearAll();
            SearchCompaniesNameTextField.clear();
            companySettingsPane.setVisible(false);
        });


    }

    private void addCompanyClearAll() {
        addCompanyCityTextField.clear();
        addCompanyNameTextField.clear();
    }

    public ObservableList getObservableListOfCompanies(@NotNull ResultSet rsRow) {
        ObservableList<Company> list = FXCollections.observableArrayList();
        int counter = 0;
        list.clear();
        while (true) {
            Company company = new Company();
            try {
                //going through each row of results
                if (!rsRow.next()) break;
                company.setName(rsRow.getString(2));
                company.setAddress(rsRow.getString(3));
                company.setRegisterDate(rsRow.getDate(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(" company added to list! " + company.toString());
            //adding each company to the observable list  list
            list.add(counter, company);
            counter++;
        }
        counter = 0;
        return list;
    }

    private void refreshCompaniesTable(Company company) {
        companySettingsPane.setVisible(true);
        if (!SearchCompaniesNameTextField.getText().equals(""))
            company.setName(SearchCompaniesNameTextField.getText().toLowerCase());
        CompaniesTableView.setItems(getObservableListOfCompanies(onhandCompaniesResultset = DatabaseHandler.getCompanies(company)));
    }
}
