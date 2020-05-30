package Home.DatabaseHandling;

import Home.model.Admin;
import Home.model.Employee;

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

    public static ResultSet getEmployee(Employee employee) {
        ResultSet resultSet = null;
        if (!employee.getLastName().equals("") || !employee.getFirstName().equals("")) {
            String query = "SELECT * FROM  " + Consts.EMPLOYEE_TABLE + " WHERE " + Consts.EMPLOYEE_FIRSTNAME + " =? " + " AND " + Consts.EMPLOYEE_LASTNAME + " =? ";
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, employee.getFirstName());
                preparedStatement.setString(2, employee.getLastName());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        } else if (!employee.getFirstName().equals("")) {
            String query = "SELECT * FROM project.employees " + " WHERE " + Consts.EMPLOYEE_FIRSTNAME + employee.getFirstName();
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
            //returns null... or that's what I thought
        } else {
            String query = "SELECT * FROM project.employees " + " WHERE " + Consts.EMPLOYEE_LASTNAME + employee.getLastName();
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
            //returns null... or that's what I thought
        }
    }

    ///write
    public void SignUpUser(Admin admin) {
        String insert2 = " INSERT INTO " + "project.users" + "("
                + Consts.USERS_FIRSTNAME + "," + Consts.USERS_LASTNAME + "," + Consts.USERS_USERNAME + "," + Consts.USERS_PASSWORD + ","
                + Consts.USERS_LEVEL+ ")" + "VALUES(?,?,?,?,?)";
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

    public ResultSet getUserByUserName(Admin admin) {
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
    //////////// add employyes

    public ResultSet getUser(Admin admin) {
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

    public void AddEmployee(Employee employee) {
        String insert2 = "INSERT INTO " + " project.employee " + "("
                + Consts.EMPLOYEE_FIRSTNAME + "," + Consts.EMPLOYEE_LASTNAME + "," + Consts.EMPLOYEE_USERNAME + "," + Consts.EMPLOYEE_JOBINFO + ","
                + Consts.EMPLOYEE_LEVEL+ ")" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(insert2);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUsername());
            preparedStatement.setString(4, employee.getJobinfo());
            preparedStatement.setInt(5,employee.getLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
