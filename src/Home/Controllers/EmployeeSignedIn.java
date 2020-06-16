package Home.Controllers;

import Home.DatabaseHandling.DatabaseHandler;
import Home.model.Company;
import Home.model.Equipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.util.function.Predicate;

public class EmployeeSignedIn implements Initializable {
    private static ResultSet onhandCompaniesResultset = null;
    private static Company onHandCompany = new Company();
    private static Company selectedCompany = new Company();
    private static Equipment onHandEquipment = new Equipment();
    private static final Equipment selectedEquipment = new Equipment();
    private static ResultSet onHandEquipmentResultSet = null;

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
    public AnchorPane companySettingsPane;
    public Button SearchCompaniesButton;
    public Button companiesSaveNewSettingsButton;
    public Label selectedCompnyLabel;
    public Button MenuToEquipmentSettings;
    public Label CompanySettingsStatusLabel;


    public SplitPane equipSettingsPane;

    public TableView<Equipment> EquipmentTable;
    public TableColumn<Equipment, String> equipMPCMediumColumn;
    public TableColumn<Equipment, Integer> equipPDistanceColumn;
    public TableColumn<Equipment, String> equipNameColumn;
    public TableColumn<Equipment, String> equipUVLightDensityColumn;
    public TableColumn<Equipment, Integer> equipDistanceOfLightColumn;
    public TableColumn<Equipment, String> equipMagTechColumn;
    public TextField searchEquipmentTextField;
    public Label SelectedEquipmentLable;
    //
    boolean companySettingsClicks = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        companySettingsPane.setVisible(false);
        currentEmployeeSessionLabel.setText("Current session employee: " + WelcomePageController.signedInEmployee.toString());


        MenuToCompanySettings.setOnAction(event -> {
            //boolean b = companySettingsPane.isVisible())? false:true
            boolean b;
            companySettingsPane.setVisible(b = !companySettingsPane.isVisible());
            System.out.println(companySettingsClicks);
            if (b) {
                equipSettingsPane.setVisible(false);
                //refreshCompaniesTable(new Company());
                frefreshCmpnyTable();
                if (selectedCompany.getName() != null) {
                    selectedCompnyLabel.setText("Selected company: " + selectedCompany.toString());
                } else {
                    selectedCompnyLabel.setText("No company selected!");
                }
            } else System.out.println("closed...");
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
                    //refreshCompaniesTable(new Company());
                    frefreshCmpnyTable();
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
            //refreshCompaniesTable(company);
            frefreshCmpnyTable();
        });

        companiesSaveNewSettingsButton.setOnAction(event -> {
            if (onHandCompany != null) selectedCompany = onHandCompany;
            else System.out.println("no selected companies");
            addCompanyClearAll();
            SearchCompaniesNameTextField.clear();
            companySettingsPane.setVisible(!companySettingsPane.isVisible());
        });

        companySettingsCancelButton.setOnAction(event -> {
            addCompanyClearAll();
            SearchCompaniesNameTextField.clear();
            companySettingsPane.setVisible(false);
            companySettingsClicks = !companySettingsClicks;
        });

        ///////equipment menu stuff
        MenuToEquipmentSettings.setOnAction(event -> {
            boolean b;
            equipSettingsPane.setVisible(b = !equipSettingsPane.isVisible());

            System.out.println(companySettingsClicks);
            if (b) {
                //true
                companySettingsPane.setVisible(false);
                refreshEquipTable();
                if (selectedCompany.getName() != null) {
                    selectedCompnyLabel.setText("Selected company: " + selectedCompany.toString());
                } else {
                    selectedCompnyLabel.setText("No company selected!");
                }
            } else System.out.println("closed...");

        });
        EquipmentTable.setRowFactory(tv -> {
            TableRow<Equipment> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) onHandEquipment = row.getItem();
                SelectedEquipmentLable.setText(onHandEquipment.toString() + " will be selected, don't forget to save!");
            });
            return row;
        });

        ////end of initialise...

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

  /*  private void refreshCompaniesTable(Company company) {
        CompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        CompanyAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        RgistrationDateCllumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        companySettingsPane.setVisible(true);
        if (!SearchCompaniesNameTextField.getText().equals(""))
            company.setName(SearchCompaniesNameTextField.getText().toLowerCase());
        CompaniesTableView.setItems(getObservableListOfCompanies(onhandCompaniesResultset = DatabaseHandler.getCompanies(company)));

    }*/

    private void frefreshCmpnyTable() {
        ObservableList eOblist = getObservableListOfCompanies(onhandCompaniesResultset = DatabaseHandler.getCompanies(new Company()));
        int counter = 0;
        FilteredList<Company> flist = new FilteredList<Company>(eOblist);
        SearchCompaniesNameTextField.textProperty().addListener((observable, oldValue, newValue) ->
                flist.setPredicate((Predicate<? super Company>) (Company company1) -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    } else
                        return company1.getName().toLowerCase().contains(newValue.toLowerCase()) || company1.getAddress().toLowerCase().contains(newValue.toLowerCase());
                }));
        SortedList<Company> sortedList = new SortedList<>(flist);
        sortedList.comparatorProperty().bind(CompaniesTableView.comparatorProperty());
        CompaniesTableView.setItems(sortedList);
        CompaniesTableView.setItems(flist);

        CompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        CompanyAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        RgistrationDateCllumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));

    }

    public ObservableList getObservableListOfEquipment(ResultSet resultSet) {
        ObservableList<Equipment> list = FXCollections.observableArrayList();
        int counter = 0;
        list.clear();
        while (true) {
            Equipment equipment = new Equipment();
            try {
                //going through each row of results
                if (!resultSet.next()) break;
                equipment.setEquipmentName(resultSet.getString(2));
                equipment.setPoleDistance(resultSet.getInt(3));
                equipment.setMpCarrierMedium(resultSet.getString(4));
                equipment.setuVLightDensity(resultSet.getString(5));
                equipment.setDistanceOfLight(resultSet.getInt(6));
                equipment.setMagTech(resultSet.getString(7));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(" company added to list! " + equipment.toString());
            //adding each company to the observable list  list
            list.add(counter, equipment);
            counter++;
        }
        counter = 0;
        return list;
    }

    private void refreshEquipTable() {
        ObservableList oblist = getObservableListOfEquipment(onHandEquipmentResultSet = DatabaseHandler.getEquipmentResultSet(new Equipment()));
        FilteredList<Equipment> flist = new FilteredList<Equipment>(oblist);
        searchEquipmentTextField.textProperty().addListener((observable, oldValue, newValue) ->
                flist.setPredicate((Predicate<? super Equipment>) (Equipment equipment1) -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    } else {
                        try {
                            return equipment1.getDistanceOfLight() == Integer.parseInt(newValue) || equipment1.getPoleDistance() == Integer.parseInt(newValue);
                        } catch (Exception e) {
                            return equipment1.getEquipmentName().toLowerCase().contains(newValue.toLowerCase()) ||
                                    equipment1.getMpCarrierMedium().toLowerCase().contains(newValue.toLowerCase()) ||
                                    equipment1.getuVLightDensity().toLowerCase().contains(newValue.toLowerCase()) ||
                                    equipment1.getMagTech().contains(newValue.toLowerCase());
                        }


                    }
                }));
        SortedList<Equipment> sortedList = new SortedList<>(flist);
        sortedList.comparatorProperty().bind(EquipmentTable.comparatorProperty());
        EquipmentTable.setItems(sortedList);
        EquipmentTable.setItems(flist);
        equipMPCMediumColumn.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        equipPDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("poleDistance"));
        equipMPCMediumColumn.setCellValueFactory(new PropertyValueFactory<>("mpCarrierMedium"));
        equipUVLightDensityColumn.setCellValueFactory(new PropertyValueFactory<>("uVLightDensity"));
        equipDistanceOfLightColumn.setCellValueFactory(new PropertyValueFactory<>("distanceOfLight"));
        equipMagTechColumn.setCellValueFactory(new PropertyValueFactory<>("magTech"));
    }


}
