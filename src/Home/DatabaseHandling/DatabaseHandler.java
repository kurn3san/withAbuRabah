package Home.DatabaseHandling;

import Home.model.Employee;
import Home.model.User;

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
    public void SignUpUser(User user) {
        String insert2 = " INSERT INTO " + "project.users" + "("
                + Consts.USERS_FIRSTNAME + "," + Consts.USERS_LASTNAME + "," + Consts.USERS_USERNAME + "," + Consts.USERS_PASSWORD + ","
                + Consts.USERS_LEVEL+ ")" + "VALUES(?,?,?,?,?)";
        String insert = "INSERT INTO " + Consts.USERS_TABLE + " ("
                + Consts.USERS_FIRSTNAME + "," + Consts.USERS_LASTNAME + "," + Consts.USERS_USERNAME + "," + Consts.USERS_PASSWORD + ","
                + Consts.USERS_LEVEL + ")" + "VALUES(?,?,?,?,?)";
        System.out.println(insert);

        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5,user.getLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUserByUserName(User user) {
        ResultSet resultSet = null;
        if (!user.getUserName().equals("")) {
            String query = "SELECT * FROM project.users " + " WHERE " + Consts.USERS_USERNAME + " =? ";
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
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

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM project.users " + " WHERE " + Consts.USERS_USERNAME + " =? " + " AND " + Consts.USERS_PASSWORD + " =? ";

            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
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
    //////////// add employyes

    public void AddEmployee(Employee employee) {
        String insert2 = "INSERT INTO " + " project.employee " + "("
                + Consts.EMPLOYEE_FIRSTNAME + "," + Consts.USERS_LASTNAME + "," + Consts.EMPLOYEE_USERNAME + "," + Consts.EMPLOYEE_JOBINFO + ","
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

    public ResultSet getEmployee(Employee employee) {
        ResultSet resultSet = null;
        if (!employee.getLastName().equals("") || !employee.getFirstName().equals("")) {
            String query = "SELECT * FROM project.employees " + " WHERE " + Consts.EMPLOYEE_FIRSTNAME + "=?" +" AND "+ Consts.EMPLOYEE_LASTNAME + "=?";
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, employee.getFirstName());
                preparedStatement.setString(2, employee.getLastName());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        } else {
            return resultSet;
            //returns null... or that's what I thought
        }
    }

}
