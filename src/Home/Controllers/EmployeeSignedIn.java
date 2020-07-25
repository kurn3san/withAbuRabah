package Home.Controllers;

import Home.DatabaseHandling.DatabaseHandler;
import Home.Main;
import Home.model.Company;
import Home.model.Equipment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
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
    private static Equipment selectedEquipment = new Equipment();
    private static ResultSet onHandEquipmentResultSet = null;
    private static final ObservableList onHandEquipmentsList = FXCollections.observableArrayList();
    public TableColumn<Equipment, Double> equipUVLightIntensityColumn;
    //////////////////////////
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
    /////////////
    public TableView<Equipment> EquipmentTable;
    public TableColumn<Equipment, String> equipMPCMediumColumn;
    public TableColumn<Equipment, Integer> equipPDistanceColumn;
    public TableColumn<Equipment, String> equipNameColumn;
    public TextField addequipDistnceOfLightTxtField;
    public TableColumn<Equipment, Integer> equipDistanceOfLightColumn;
    public TableColumn<Equipment, String> equipMagTechColumn;
    public TextField searchEquipmentTextField;
    public Label SelectedEquipmentLable;
    //add new Equipment
    public Button selectEquipCancelButton;
    public Button selectEquipSaveNewSettingsButton;
    public TextField addEquipmentNameTxtField;
    public TextField addequipPoleDistanceTxtFeild;
    public TextField addequipMpCarMediumTxtField;
    @FXML
    private Button EmployeeLogOutButton;
    public TextField addequipUvLghtIntTxtField;
    public TextField addequipMagTechTxtField;
    public Button addEquipSaveButton;
    public Button addequipClearAllButton;
    public Label addequipLghtIntErrorLabel;
    public Label addequipEquipNameErrorLabel;
    public Label addequipPoleDistanceErrirLabel;
    public Label addequipCarrierMediumErrorLabel;
    public Label addequipDistanceErrorLabel;
    public Label addequipSavingResultLabel;
    public Button equipDividerButton;
    public TextField equipPoleDistanceTxtField;
    public TextField equipUVlghtDenTxtField;
    public TextField equipNameTxtField;
    public TextField equipMpCarMedTxtField;
    public TextField equipDisOfLightTxtField;
    public TextField equipMagTechTxtField;
    public Label equipExaminAreaLabel;
    public TextField equipExaminAreaTxtField;
    public TextField equipTempTxtField;
    public TextField equipGausFldStrnthTxtField;
    public ChoiceBox<String> equipCurntTypCBox;
    public TextField equipLuxMtrTxtField;
    public Label SequipurfaceTempLabel;
    public Label equipGausFelStrenLabel;
    public Button equipOpenDivideButton;
    public Button equipCloseDvdButton;
    public TextField equipSurfCondTxtField;
    public TextField equipIDOfLightEquipTxtFld;
    public TextField equipLftngTstDteNumTxtFld;
    public TextField equipHeatTrtmntTxtfld;
    public TextField equipDmgntzationTxtFld;
    public TextField equipTstMediumTxtFld;
    public Button equipClearAllButton;
    public Button equipRstPageButton;
    @FXML
    private Button addEquipCancelButton;
    boolean companySettingsClicks = false;

    /*
    <TextField fx:id="equipTempTxtField" layoutX="780.0" layoutY="359.0" prefHeight="17.0"
                                       prefWidth="51.0"/>
        */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double deviderV = 0.8704;
        companySettingsPane.setVisible(false);
        currentEmployeeSessionLabel.setText("Current session employee: " + WelcomePageController.signedInEmployee.toString());
        MenuToCompanySettings.setOnAction(event -> {
            //boolean b = companySettingsPane.isVisible())? false:true
            boolean b;
            companySettingsPane.setVisible(b = !companySettingsPane.isVisible());
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
        EmployeeLogOutButton.setOnAction(event -> {
            EmployeeLogOutButton.getScene().getRoot().getScene().getWindow().hide();
            WelcomePageController.signedInEmployee = null;
            Main.WelcomePageStage.show();
        });
        //

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
        searchEquipmentTextField.setOnMouseClicked(event -> {
            equipSettingsPane.setDividerPosition(0, deviderV);
        });
        MenuToEquipmentSettings.setOnAction(event -> {
            boolean b;
            equipSettingsPane.setVisible(b = !equipSettingsPane.isVisible());
            if (b) {
                //true
                companySettingsPane.setVisible(false);
                equipSettingsPane.setDividerPosition(0, deviderV);
                refreshEquipTable();
                addEquipClearAll();
                if (selectedCompany.getName() != null) {
                    selectedCompnyLabel.setText("Selected company: " + selectedCompany.toString());
                } else {
                    selectedCompnyLabel.setText("No company selected!");
                }
            } else System.out.println("closed...");
        });
        // Buttons, adding equipment side...
        addEquipSaveButton.setOnAction(event -> {
            if (!addEquipmentNameTxtField.getText().equals("") && !addequipPoleDistanceTxtFeild.getText().equals("") && !addequipMpCarMediumTxtField.getText().equals("")
                    && !addequipUvLghtIntTxtField.getText().equals("") && !addequipDistnceOfLightTxtField.getText().equals("") && !addequipMagTechTxtField.getText().equals("")) {
                Equipment equipment = new Equipment();
                equipment.setuVLightIntensity(Double.parseDouble(addequipUvLghtIntTxtField.getText()));
                equipment.setDistanceOfLight(Integer.parseInt(addequipDistnceOfLightTxtField.getText()));
                equipment.setDoubleuVLightIntensity(Double.parseDouble(addequipUvLghtIntTxtField.getText()));
                equipment.setMagTech(addequipMagTechTxtField.getText());
                equipment.setPoleDistance(Integer.parseInt(addequipPoleDistanceTxtFeild.getText()));
                equipment.setEquipmentName(addEquipmentNameTxtField.getText());
                //
                if (!isThereSuchEquipment(equipment)) {
                    if (DatabaseHandler.addEquipment(equipment)) addEquipClearAll();
                }
            }
        });

        // only numbers in Numeric fields... adding equipment
        addequipDistnceOfLightTxtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d")) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        if (newValue.length() == 0) addequipDistnceOfLightTxtField.setText("");
                        else addequipDistnceOfLightTxtField.setText(oldValue);
                    }
                }
            }
        });
        addequipUvLghtIntTxtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(\\d)")) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        if (newValue.length() == 0) addequipUvLghtIntTxtField.setText("");
                        else addequipUvLghtIntTxtField.setText(oldValue);
                    }
                }
            }
        });
        addequipPoleDistanceTxtFeild.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d")) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        if (newValue.length() == 0) addequipPoleDistanceTxtFeild.setText("");
                        else addequipPoleDistanceTxtFeild.setText(oldValue);
                    }
                }
            }
        });
        // only numbers in Numeric fields...  choosing equipment
        equipLuxMtrTxtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d")) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        if (newValue.length() == 0) equipLuxMtrTxtField.setText("0");
                        else equipLuxMtrTxtField.setText(oldValue);
                    }
                }
            }
        });
        equipGausFldStrnthTxtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(\\d)((\\.)(\\d)?)")) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        if (newValue.length() == 0) equipGausFldStrnthTxtField.setText("0");
                        else equipGausFldStrnthTxtField.setText(oldValue);
                    }
                }
            }
        });
        equipDisOfLightTxtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(\\d)")) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        if (newValue.length() == 0) equipDisOfLightTxtField.setText("0");
                        else equipDisOfLightTxtField.setText(oldValue);
                    }
                }
            }
        });

        equipPoleDistanceTxtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("(\\d)((\\.)(\\d)?)")) {
                    try {
                        Double.parseDouble(newValue);
                    } catch (Exception e) {
                        if (newValue.length() == 0) equipPoleDistanceTxtField.setText("0");
                        else equipPoleDistanceTxtField.setText(oldValue);
                    }
                }
            }
        });

        equipTempTxtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String re = "((\\d){0,4})?";
                if (!newValue.matches(re) && newValue.length() != 0) {
                    try {
                        System.out.println(Double.parseDouble(newValue));
                        System.out.println("tried");
                    } catch (Exception e) {
                        equipTempTxtField.setText(oldValue);
                    }
                }
            }
        });

        EquipmentTable.setRowFactory(tv -> {
            TableRow<Equipment> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) onHandEquipment = row.getItem();
                SelectedEquipmentLable.setText("Don't forget to save!");
            });
            equipSettingsPane.setDividerPosition(0, deviderV);
            return row;
        });
        selectEquipCancelButton.setOnAction(event -> {
            addEquipClearAll();
            equipSettingsPane.setVisible(false);
        });
        addequipClearAllButton.setOnAction(event -> {
            addEquipClearAll();
        });
        addEquipCancelButton.setOnAction(event -> {
            equipSettingsPane.setDividerPosition(0, deviderV);
            addEquipClearAll();
        });
        selectEquipSaveNewSettingsButton.setOnAction(event -> {
            if (!onHandEquipment.equals(new Equipment())) selectedEquipment = onHandEquipment;
            equipSettingsPane.setDividerPosition(0, deviderV);
            equipSettingsPane.setVisible(false);
        });
        //Standard info
        equipExaminAreaTxtField.setText("KAYNAK+HAZ");
        equipTempTxtField.setText("");
        equipGausFldStrnthTxtField.setText("3.2");
        equipCurntTypCBox.setItems(FXCollections.observableArrayList("AC", "DC"));
        equipLuxMtrTxtField.setText("1200");

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
        return list;
    }

    private boolean isThereSuchEquipment(Equipment equipment) {
        ResultSet RS = onHandEquipmentResultSet;
        while (true) {
            Equipment equipment1 = new Equipment();
            try {
                //going through each row of results
                if (!RS.next()) break;
                equipment1.setEquipmentName(RS.getString(2));
                equipment1.setPoleDistance(RS.getInt(3));
                equipment1.setMpCarrierMedium(RS.getString(4));
                equipment1.setDoubleuVLightIntensity(RS.getDouble(5));
                equipment1.setDistanceOfLight(RS.getInt(6));
                equipment1.setMagTech(RS.getString(7));
                if (equipment.equals(equipment1)) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void frefreshCmpnyTable() {
        ObservableList<Company> Oblist = getObservableListOfCompanies(onhandCompaniesResultset = DatabaseHandler.getCompanies(new Company()));
        FilteredList<Company> flist = new FilteredList<Company>(Oblist);
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
                equipment.setDoubleuVLightIntensity(resultSet.getDouble(5));
                equipment.setuVLightIntensity(resultSet.getDouble(5));
                System.out.println("showing equipment.setuVLightIntensity(resultSet.getDouble(5)):" + equipment.getuVLightIntensity());
                System.out.println(equipment.getuVLightIntensity());
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
        return list;
    }
    private void refreshEquipTable() {

        FilteredList<Equipment> flist = new FilteredList<Equipment>(getObservableListOfEquipment(onHandEquipmentResultSet = DatabaseHandler.getEquipmentResultSet(new Equipment())));
        searchEquipmentTextField.textProperty().addListener((observable, oldValue, newValue) ->
                flist.setPredicate((Predicate<? super Equipment>) (Equipment equipment1) -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    } else {
                        boolean b = false;
                        try {
                            b = Integer.parseInt(newValue) == equipment1.getPoleDistance();
                        } catch (Exception e) {
                        }
                        try {
                            b = Integer.parseInt(newValue) == equipment1.getDistanceOfLight();
                        } catch (Exception e) {
                        }
                        try {
                            b = Double.parseDouble(newValue) == equipment1.getDoubleuVLightIntensity();
                        } catch (Exception e) {
                        }
                        try {
                            b = Double.parseDouble(newValue) == equipment1.getuVLightIntensity();
                        } catch (Exception e) {
                        }
                        return equipment1.getEquipmentName().toLowerCase().contains(newValue.toLowerCase()) || equipment1.getEquipmentName().equals(newValue.toLowerCase()) ||
                                equipment1.getMpCarrierMedium().toLowerCase().contains(newValue.toLowerCase()) || equipment1.getMpCarrierMedium().toLowerCase().equals(newValue.toLowerCase()) ||
                                equipment1.getMagTech().contains(newValue.toLowerCase()) || equipment1.getMagTech().equals(newValue.toLowerCase())
                                || b;
                    }
                }));
        SortedList<Equipment> sortedList = new SortedList<>(flist);
        sortedList.comparatorProperty().bind(EquipmentTable.comparatorProperty());
        EquipmentTable.setItems(sortedList);
        EquipmentTable.setItems(flist);
        equipNameColumn.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        equipPDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("poleDistance"));
        equipMPCMediumColumn.setCellValueFactory(new PropertyValueFactory<>("mpCarrierMedium"));
        equipUVLightIntensityColumn.setCellValueFactory(new PropertyValueFactory<>("uVLightIntensity"));
        equipDistanceOfLightColumn.setCellValueFactory(new PropertyValueFactory<>("distanceOfLight"));
        equipMagTechColumn.setCellValueFactory(new PropertyValueFactory<>("magTech"));
    }
    private void addEquipClearAll() {
        addequipDistnceOfLightTxtField.clear();
        addequipPoleDistanceTxtFeild.clear();
        addEquipmentNameTxtField.clear();
        addequipPoleDistanceTxtFeild.clear();
        addequipMpCarMediumTxtField.clear();
        addequipDistnceOfLightTxtField.clear();
        addequipUvLghtIntTxtField.clear();
        addequipMagTechTxtField.clear();
        addequipLghtIntErrorLabel.setVisible(false);
        addequipEquipNameErrorLabel.setVisible(false);
        addequipPoleDistanceErrirLabel.setVisible(false);
        addequipCarrierMediumErrorLabel.setVisible(false);
        addequipDistanceErrorLabel.setVisible(false);
        addequipSavingResultLabel.setVisible(false);
    }

    /*
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("((\\d{0,2}))([\\.]\\d{0,4})?|(100)")) {
                    addequipDistnceTxtField.setText(oldValue);
                }
            }
    */
}
