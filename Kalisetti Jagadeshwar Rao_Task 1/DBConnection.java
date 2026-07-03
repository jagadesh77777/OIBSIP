import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String HOST     = "localhost";   
    private static final String PORT     = "3306";        
    private static final String DATABASE = "railway_db";  
    private static final String USERNAME = "root";       
    private static final String PASSWORD = "";          

    private static final String URL =
        "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
        + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    public static Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: MySQL JDBC Driver not found!");
            System.out.println("Fix : Add mysql-connector-j-x.x.x.jar to your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("ERROR: Cannot connect to database!");
            System.out.println("Fix : Check that MySQL is running and credentials are correct.");
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Warning: Could not close connection properly.");
                e.printStackTrace();
            }
        }
    }
}
