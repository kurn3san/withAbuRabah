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

    ///write
    public static void SignUpUser(Admin admin) {
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

    //////////// add employyes
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
        int counter = 0;
        while (true) {
            Employee employee2 = new Employee();
            try {
                //going through each row of results
                if (!resultSet.next()) break;
                employee2.setLastName(resultSet.getString(3).toUpperCase());
                employee2.setFirstName(resultSet.getString(2));
                employee2.setLevel(resultSet.getInt(5));
                employee2.setTitle(resultSet.getString(6));
                employee2.setUsername(resultSet.getString(4));
                employee2.setCertificateDate(resultSet.getDate(7).toLocalDate());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Employee added to list!" + employee.toString() + " counter: " + counter);
            //adding each employee to the observable list  list
            // System.out.println("showing employee from oblist 'emps': "+ emps.get(counter).toString());
            counter++;
        }
        return counter != 0;
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


}
