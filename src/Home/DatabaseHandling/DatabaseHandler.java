package Home.DatabaseHandling;

import Home.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler extends Configs {
    private static Connection dbConnection;

    public static void connect() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    public static Connection getDbConnection() {
        return dbConnection;
    }
    ///write
    public static void signUpAdmin(Admin admin) {
        String insert2 = " INSERT INTO " + "project.users" + "("
                + Consts.USERS_FIRSTNAME + "," + Consts.USERS_LASTNAME + "," + Consts.USERS_USERNAME + "," + Consts.USERS_PASSWORD + ","
                + Consts.USERS_LEVEL + ")" + "VALUES(?,?,?,?,?)";
        String insert = "INSERT INTO " + Consts.USERS_TABLE + " ("
                + Consts.USERS_FIRSTNAME + "," + Consts.USERS_LASTNAME + "," + Consts.USERS_USERNAME + "," + Consts.USERS_PASSWORD + ","
                + Consts.USERS_LEVEL + ")" + "VALUES(?,?,?,?,?)";
        System.out.println(insert);

        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getUserName());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.setInt(5, admin.getLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getUserByUserName(Admin admin) {
        ResultSet resultSet = null;
        if (!admin.getUserName().equals("")) {
            String query = "SELECT * FROM project.users " + " WHERE " + Consts.USERS_USERNAME + " =? ";
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, admin.getUserName());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return resultSet;

        } else {
            // field empty
            System.out.println("enter Username to check if exists...");
            return resultSet;
        }
    }

    public static ResultSet getAdmin(Admin admin) {
        ResultSet resultSet = null;
        if (!admin.getUserName().equals("") || !admin.getPassword().equals("")) {
            String query = "SELECT * FROM project.users " + " WHERE " + Consts.USERS_USERNAME + " =? " + " AND " + Consts.USERS_PASSWORD + " =? ";

            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, admin.getUserName());
                preparedStatement.setString(2, admin.getPassword());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        } else {
            // pass empty
            System.out.println("please enter your credentials...");
            return resultSet;
            //returns null...
        }
    }
    ///////////////
    // Employeee proooblemmms
    ///////////////
    // ////////// add employyes
    public static void AddEmployee(Employee employee) {
        String insert2 = "INSERT INTO " + " project.employee " + "("
                + Consts.EMPLOYEE_FIRSTNAME + "," + Consts.EMPLOYEE_LASTNAME + "," + Consts.EMPLOYEE_USERNAME + "," + Consts.EMPLOYEE_TITEL + ","
                + Consts.EMPLOYEE_LEVEL + "," + Consts.EMPLOYEE_CERTIFICATE_DATE + "," + Consts.EMPLOYEE_PASSWORD + " ) " + " VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(insert2);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUsername());
            preparedStatement.setString(4, employee.getTitle());
            preparedStatement.setInt(5, employee.getLevel());
            preparedStatement.setDate(6, java.sql.Date.valueOf(employee.getCertificateDate()));
            preparedStatement.setString(7, employee.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("added!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isThereSuchNEmployee(Employee employee) {
        String Query = "SELECT * FROM " + Consts.EMPLOYEE_TABLE + " WHERE " + Consts.EMPLOYEE_USERNAME + " = '" + employee.getUsername() + "'";
        ResultSet resultSet = null;
        try {
            resultSet = DatabaseHandler.getDbConnection().prepareStatement(Query).executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Employee foundEmployee = new Employee();
        int counter = 0;
        while (true) {
            try {
                //going through each row of results
                if (!resultSet.next()) break;
                foundEmployee.setLastName(resultSet.getString(3).toUpperCase());
                foundEmployee.setFirstName(resultSet.getString(2));
                foundEmployee.setLevel(resultSet.getInt(5));
                foundEmployee.setTitle(resultSet.getString(6));
                foundEmployee.setUsername(resultSet.getString(4));
                foundEmployee.setCertificateDate(resultSet.getDate(7).toLocalDate());

            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
            System.out.println("Employee found!" + foundEmployee.toString() + " counter: " + counter);
            //adding each employee to the observable list  list
            // System.out.println("showing employee from oblist 'emps': "+ emps.get(counter).toString());
        }
        return counter != 0;
    }

    public static Employee getEmployee(Employee employee) {
        Employee foundEmployee = new Employee();
        String Query = "SELECT * FROM " + Consts.EMPLOYEE_TABLE + " WHERE " + Consts.EMPLOYEE_USERNAME + " = '" + employee.getUsername() + "'" + " AND " +
                Consts.EMPLOYEE_PASSWORD + " = '" + employee.getPassword() + "'";
        ResultSet resultSet = null;
        try {
            resultSet = DatabaseHandler.getDbConnection().prepareStatement(Query).executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int counter = 0;
        while (true) {
            try {
                //going through each row of results
                if (!resultSet.next()) break;
                foundEmployee.setLastName(resultSet.getString(3).toUpperCase());
                foundEmployee.setFirstName(resultSet.getString(2));
                foundEmployee.setLevel(resultSet.getInt(5));
                foundEmployee.setTitle(resultSet.getString(6));
                foundEmployee.setUsername(resultSet.getString(4));
                foundEmployee.setCertificateDate(resultSet.getDate(7).toLocalDate());

            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Employee found!" + foundEmployee.toString() + " counter: " + counter);
            //adding each employee to the observable list  list
            // System.out.println("showing employee from oblist 'emps': "+ emps.get(counter).toString());
            counter++;
        }
        if (counter == 1) return foundEmployee;
        else return null;
    }

    public static Employee getEmployeeByUsername(String string) {
        Employee foundEmployee = new Employee();
        String Query = "SELECT * FROM " + Consts.EMPLOYEE_TABLE + " WHERE " + Consts.EMPLOYEE_USERNAME + " = '" + string + "'";
        ResultSet resultSet = null;
        try {
            resultSet = DatabaseHandler.getDbConnection().prepareStatement(Query).executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int counter = 0;
        while (true) {
            try {
                //going through each row of results
                if (!resultSet.next()) break;
                foundEmployee.setLastName(resultSet.getString(3).toUpperCase());
                foundEmployee.setFirstName(resultSet.getString(2));
                foundEmployee.setLevel(resultSet.getInt(5));
                foundEmployee.setTitle(resultSet.getString(6));
                foundEmployee.setUsername(resultSet.getString(4));
                foundEmployee.setCertificateDate(resultSet.getDate(7).toLocalDate());

            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Employee found!" + foundEmployee.toString() + " counter: " + counter);
            //adding each employee to the observable list  list
            // System.out.println("showing employee from oblist 'emps': "+ emps.get(counter).toString());
            counter++;
        }
        return foundEmployee;
    }

    public static ResultSet getEmployeeResultSet(Employee employee) {
        ResultSet resultSet = null;

        if (!employee.getLastName().equals("") && !employee.getFirstName().equals("")) {
            System.out.println("getEmployee() got: " + employee.toString());
            String query = "SELECT * FROM  " + Consts.EMPLOYEE_TABLE + " WHERE "
                    + Consts.EMPLOYEE_FIRSTNAME + " =? " + " AND " +
                    Consts.EMPLOYEE_LASTNAME + " =? ";
            System.out.println("query is: " + query);
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, employee.getFirstName().toLowerCase());
                preparedStatement.setString(2, employee.getLastName().toLowerCase());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        } else if (!employee.getFirstName().equals("")) {
            System.out.println("getEmployee() got: " + employee.toString());

            String query = "SELECT * FROM  " + Consts.EMPLOYEE_TABLE + " WHERE " + Consts.EMPLOYEE_FIRSTNAME + " = " + "'" + employee.getFirstName() + "'";
            System.out.println("query is:   " + query);
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
            //returns null... or that's what I thought
        } else if (!employee.getLastName().equals("")) {
            System.out.println("getEmployee() got: " + employee.toString());

            String query = "SELECT * FROM  " + Consts.EMPLOYEE_TABLE + " WHERE " + Consts.EMPLOYEE_LASTNAME + " = " + "'" + employee.getLastName() + "'";
            System.out.println("query is:   " + query);
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        } else {
            System.out.println("getEmployee() got: " + employee.toString());

            String query = "SELECT * FROM  " + Consts.EMPLOYEE_TABLE;
            System.out.println("query is: " + query);
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return resultSet;
        }
    }

    public static boolean updateEmployee(Employee employee) {
        String query = " UPDATE " + Consts.EMPLOYEE_TABLE +
                " Set " +
                Consts.EMPLOYEE_FIRSTNAME + " =? ," +
                Consts.EMPLOYEE_LASTNAME + " =? ," +
                Consts.EMPLOYEE_TITEL + " =? ,"
                + Consts.EMPLOYEE_LEVEL + " =? ," +
                Consts.EMPLOYEE_CERTIFICATE_DATE + " =?" +
                " WHERE " + Consts.EMPLOYEE_USERNAME + " = " + " ? ";
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            //preparedStatement.setString(3, employee.getUsername());
            preparedStatement.setString(3, employee.getTitle());
            preparedStatement.setInt(4, employee.getLevel());
            preparedStatement.setDate(5, java.sql.Date.valueOf(employee.getCertificateDate()));
            preparedStatement.setString(6, employee.getUsername());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeEmployeePassword(Employee employee) {
        String query = " UPDATE " + Consts.EMPLOYEE_TABLE +
                " Set " +
                Consts.EMPLOYEE_PASSWORD + " = " + employee.getPassword() +
                " WHERE " + Consts.EMPLOYEE_USERNAME + " = '" + employee.getUsername() + "'";
        try {
            System.out.println(query);
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteEmployee(Employee employee) {
        String query = " Delete FROM " + Consts.EMPLOYEE_TABLE +
                " where username = ?";
        try {
            System.out.println("deleting process with query: " + query);
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public static ResultSet freshListOfEmployees() {
        ResultSet resultSet = null;
        String query = "SELECT * FROM  " + Consts.EMPLOYEE_TABLE;
        System.out.println("query is: " + query);
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static boolean addCompany(Company company) {
        String query = " INSERT INTO " + Consts.COMPANIES_TABLE + " ( " + Consts.COMPANY_NAME + ", " + Consts.COMPANY_ADDRESS + ", " + Consts.COMPANY_REGISTER_DATE + ")" +
                " VALUES(?,?,?) ";
        try {
            PreparedStatement ps = DatabaseHandler.getDbConnection().prepareStatement(query);
            ps.setString(1, company.getName());
            ps.setString(2, company.getAddress());
            ps.setDate(3, company.getRegisterDate());
            ps.executeUpdate();
            System.out.println("added!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int isThereSuchACompany(Company company) {
        //returns -1 if there's not, or the index of the found company...
        String query = "SELECT * FROM " + Consts.COMPANIES_TABLE + " WHERE " + Consts.COMPANY_NAME + " = '" + company.getName() + "';";
        ResultSet rs = null;
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Company foundCompany = new Company();
        int counter = -1;
        while (true) {

            try {
                //going through each row of results
                if (!rs.next()) break;
                foundCompany.setCompanyid(counter = rs.getInt(1));
                foundCompany.setName(rs.getString(2));
                foundCompany.setRegisterDate(rs.getDate(4));
                foundCompany.setAddress(rs.getString(3));

            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Company found!" + foundCompany.toString() + " counter: " + counter);
            //adding each employee to the observable list  list
            // System.out.println("showing employee from oblist 'emps': "+ emps.get(counter).toString());
        }
        return counter;
    }

    public static Company getCompany(Company company) {
        return company;
        ////////////////////////
        ////////must work ooon!!!
    }

    public static ResultSet getCompanies(Company company) {
        ResultSet rs = null;
        if (!company.getName().equals("") && !company.getAddress().equals("")) {
            String query = " SELECT * FROM " + Consts.COMPANIES_TABLE + " Where " + Consts.COMPANY_NAME + " = '" +
                    company.getName() + "' AND " + Consts.COMPANY_ADDRESS + " = '" + company.getName() + ";";
            System.out.println("query was: " + query);
            try {
                rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (!company.getName().equals("") && company.getAddress().equals("")) {
            String query = " SELECT * FROM " + Consts.COMPANIES_TABLE + " Where " + Consts.COMPANY_NAME + " = '" + company.getName() + " '";
            System.out.println("query was: " + query);
            try {
                rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (!company.getAddress().equals("") && company.getName().equals("")) {
            String query = " SELECT * FROM " + Consts.COMPANIES_TABLE + " Where " + Consts.COMPANY_ADDRESS + " = '" + company.getAddress() + " '";
            System.out.println("query was: " + query);
            try {
                rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            String query = " SELECT * FROM " + Consts.COMPANIES_TABLE;
            System.out.println("query was: " + query);
            try {
                rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    public static ResultSet ordersOfCompany(Company company) {
        String query = "SELECT * FROM project.orders WHERE companyid = " + company.getCompanyid();
        ResultSet rs = null;
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
        } catch (Exception e) {

        }
        return rs;
    }

    public static boolean addOrderNoOfCompany(Company company, int no) {
        String query = "INSERT INTO project.orders (joborderno, companyid) VALUES(" + no + ',' + company.getCompanyid() + ");";
        System.out.println(query);
        try {
            int i = DatabaseHandler.getDbConnection().prepareStatement(query).executeUpdate();
            System.out.println(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ResultSet jobOffersOfCompany(Company company) {
        String query = "SELECT * FROM project.joboffers WHERE companyid = " + company.getCompanyid();
        ResultSet rs = null;
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
        } catch (Exception e) {

        }
        return rs;
    }

    public static boolean addJobOfferNoOfCompany(Company company, int no) {
        String query = "INSERT INTO project.joboffers (jobofferno, companyid) VALUES(" + no + ',' + company.getCompanyid() + ");";
        System.out.println(query);
        try {
            int i = DatabaseHandler.getDbConnection().prepareStatement(query).executeUpdate();
            System.out.println(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean addProjectOfCompany(Company company, String string) {
        String query = "SELECT * FROM project.projects WHERE projectname = '" + string + "';";
        ResultSet rs;
        int counter = 0;
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (true) {
                //going through each row of results
                if (!rs.next()) break;
                System.out.println("adding project counter");
                counter++;
            }

        } catch (Exception e) {
        }
        if (counter == 0) {
            String query2 = "INSERT INTO project.projects (projectname, companyid) VALUES(" + string + ',' + company.getCompanyid() + ");";
            System.out.println(query);
            try {
                int i = DatabaseHandler.getDbConnection().prepareStatement(query2).executeUpdate();
                System.out.println(i);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static ObservableList<String> getObservableListOfProjects(Company company) {
        String query = "SELECT * FROM project.projects WHERE companyid = " + company.getCompanyid();
        ResultSet rs = null;
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (true) {

                //going through each row of results
                if (!rs.next()) break;
                list.add(rs.getString(3));
            }
            if (list.isEmpty()) list.add("none");

        } catch (Exception e) {

        }
        return list;
    }

    public static ObservableList<String> getSurfaceConditions() {
        ObservableList<String> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM project.surfacecondition ";
        ResultSet rs = null;
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (true) {

                //going through each row of results
                if (!rs.next()) break;
                list.add(rs.getString(2));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public static boolean addSurfaceCondition(String string) {
        String query = "SELECT * FROM project.surfacecondition WHERE surfacecondition = '" + string + "';";
        ResultSet rs = null;
        int counter = 0;
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (true) {

                //going through each row of results
                if (!rs.next()) break;
                counter++;
            }

        } catch (Exception e) {
        }
        if (counter == 0) {
            try {
                String query2 = "INSERT INTO project.surfacecondition (surfacecondition) VALUES ( '" + string + "')";
                DatabaseHandler.getDbConnection().prepareStatement(query2).executeUpdate();
                return true;
            } catch (Exception e) {
            }

        }
        return false;
    }

    public static ObservableList<String> getStageOfExaminatinos() {
        ObservableList<String> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM project.stageofexamination ";
        ResultSet rs;
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (true) {

                //going through each row of results
                if (!rs.next()) break;
                list.add(rs.getString(2));
            }

        } catch (Exception e) {

        }
        return list;
    }

    public static boolean addStageOfExamination(String string) {

        String query = "SELECT * FROM project.stageofexamination WHERE stageofexamination = " + string + " ;";
        ResultSet rs = null;
        int counter = 0;
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (true) {

                //going through each row of results
                if (!rs.next()) break;
                counter++;
            }
        } catch (Exception e) {
        }
        if (counter == 0) {
            try {
                String query2 = "INSERT INTO project.stageofexamination (stageofexamination) VALUES ( '" + string + "')";
                DatabaseHandler.getDbConnection().prepareStatement(query2).executeUpdate();
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    ////equip

    public static boolean addEquipment(Equipment equipment) {
        String query = " INSERT INTO " + Consts.EQUIPMENT_TABLE + " ( " +
                Consts.EQUIPMENT_NAME + ", " +
                Consts.EQUIPMENT_POLE_DISTANCE + ", " +
                Consts.EQUIPMENT_M_P_CARRIER_MEDIUM + ", " +
                Consts.EQUIPMENT_UV_DENSITY + ", " +
                Consts.EQUIPMENT_DISTANCE_OF_LIGHT + ", " +
                Consts.EQUIPMENT_M_G_TEC + ")" +
                " VALUES( '" + equipment.getEquipmentName() + "' , '" + equipment.getPoleDistance() + " ' , '" + equipment.getMpCarrierMedium() +
                "' , '" + equipment.getDoubleuVLightIntensity() + "' , '" + equipment.getDistanceOfLight() + "' , '" + equipment.getMagTech() + "') ";
        try {
            DatabaseHandler.getDbConnection().prepareStatement(query).executeUpdate();
            System.out.println("added!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static int isThereSuchEquipment(Equipment equipment) {
        //returns: table Index of found equipment, or -1, if there's no such Equipment...
        ResultSet rs = null;
        int i = -1;
        String query = " SELECT * FROM " + Consts.EQUIPMENT_TABLE + " WHERE " + Consts.EQUIPMENT_NAME + "= '" + equipment.getEquipmentName() + "' AND " +
                Consts.EQUIPMENT_POLE_DISTANCE + "= '" + equipment.getPoleDistance() + "' AND " +
                Consts.EQUIPMENT_M_P_CARRIER_MEDIUM + "= '" + equipment.getMpCarrierMedium() + "' AND " +
                Consts.EQUIPMENT_UV_DENSITY + "= '" + equipment.getDoubleuVLightIntensity() + "' AND " +
                Consts.EQUIPMENT_DISTANCE_OF_LIGHT + "= '" + equipment.getDistanceOfLight() + "' AND " +
                Consts.EQUIPMENT_M_G_TEC + "= '" + equipment.getMagTech() + "' ;";
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int counter = 0;
        while (true) {

            try {
                //going through each row of results
                if (!rs.next()) break;
                i = rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("equipment found! #" + counter);
            counter++;
        }
        return i;
    }

    public static ResultSet getEquipmentResultSet() {
        String query = " SELECT * " + " FROM " + Consts.EQUIPMENT_TABLE + ";";
        try {
            return DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static boolean addWeldPieceOfCompany(Company company, WeldedPiece weldedPiece, int no) {
        String query = "SELECT * FROM project.weldedpiece WHERE weldPieceNo = '" + weldedPiece.getWeldPieceNo() + "'AND companyid = '" + company.getCompanyid()
                + "'AND reportno = '" + no + "';";
        ResultSet rs;
        int counter = 0;
        try {
            rs = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (true) {

                //going through each row of results
                if (!rs.next()) break;
                System.out.println(counter);
                counter++;
            }

        } catch (Exception e) {
        }
        if (counter == 0) {
            String query2 = "INSERT INTO " +
                    "project.weldedpiece" +
                    " (serialNo," +
                    "weldPieceNo," +
                    "testLength," +
                    "weldingProcess," +
                    "thickness," +
                    "diameter," +
                    "defectType," +
                    "defectLoc," +
                    "result," +
                    "reportno," +
                    " companyid) " +
                    "VALUES('" +
                    weldedPiece.getSerialNo() + "', '" +
                    weldedPiece.getWeldPieceNo() + "', '" +
                    weldedPiece.getTestLength() + "', '" +
                    weldedPiece.getWeldingProcess() + "', '" +
                    weldedPiece.getThickness() + "', '" +
                    weldedPiece.getDiameter() + "', '" +
                    weldedPiece.getDefectType() + "', '" +
                    weldedPiece.getDefectLoc() + "', '" +
                    weldedPiece.getResult() + "', '" +
                    no + "', '" +
                    company.getCompanyid() + "' );";
            System.out.println(query2);
            try {
                int i = DatabaseHandler.getDbConnection().prepareStatement(query2).executeUpdate();
                System.out.println(i);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static ResultSet getWeldPiece(Company company, WeldedPiece weldedPiece, int no) {
        String query = "SELECT * FROM project.weldedpiece WHERE weldPieceNo = '" + weldedPiece.getWeldPieceNo() + "'AND companyid = '" + company.getCompanyid()
                + "'AND reportno = '" + no + "';";
        try {
            return DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();

        } catch (Exception e) {
        }
        return null;
    }

    public static ResultSet getWeldPieceOfCompany(Company company, int no) {
        String query = "SELECT * FROM project.weldedpiece WHERE companyid = '" + company.getCompanyid()
                + "'AND reportno = '" + no + "';";
        try {
            return DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();

        } catch (Exception e) {
        }
        return null;
    }

    public static void deleteWeldPiece(Company company, WeldedPiece weldedPiece, int no) {

        String query = "DELETE FROM project.weldedpiece WHERE weldPieceNo = '" + weldedPiece.getWeldPieceNo() + "' AND companyid = '" + company.getCompanyid()
                + "' AND reportno = '" + no + "';";
        System.out.println(query);
        try {
            DatabaseHandler.getDbConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void addOperatorSignature(Employee employee, int no) {
        String query = "INSERT INTO project.signatures (operator, reportno) VALUES ('" + employee.getUsername() + "' , '" + no + "');";
        try {
            DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
        } catch (Exception e) {

        }

    }

    public static void addConfirmationMaker(Employee employee, int no) {
        String query = "UPDATE project.signatures SET confirmationmaker = '" +
                employee.getUsername() + "'" +


                " WHERE reportno = " + no + ";";
        try {
            DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
        } catch (Exception e) {

        }

    }

    public static String getOperatorSignature(int no) {
        String query = "SELECT operator FROM project.signatures WHERE repotno = " + no;
        ResultSet resultSet;
        String string = "";
        try {
            resultSet = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (!resultSet.next()) {
                string = resultSet.getString(1);
            }
        } catch (Exception e) {

        }
        return string;

    }

    public static String getEvaluatorSignature(int no) {
        String query = "SELECT evaluator FROM project.signatures WHERE repotno = " + no;
        ResultSet resultSet;
        String string = "";
        try {
            resultSet = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (!resultSet.next()) {
                string = resultSet.getString(2);
            }
        } catch (Exception e) {

        }
        return string;

    }

    public static String getConfirmationMakerSignature(int no) {
        String query = "SELECT confirmationmaker FROM project.signatures WHERE repotno = " + no;
        ResultSet resultSet;
        String string = "";
        try {
            resultSet = DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
            while (!resultSet.next()) {
                string = resultSet.getString(3);
            }
        } catch (Exception e) {

        }
        return string;

    }

    public void addEvaluatorSignature(Employee employee, int no) {
        String query = "UPDATE project.signatures SET evaluator = '" +
                employee.getUsername() + "'" +


                " WHERE reportno = " + no + ";";
        try {
            DatabaseHandler.getDbConnection().prepareStatement(query).executeQuery();
        } catch (Exception e) {

        }

    }


}
