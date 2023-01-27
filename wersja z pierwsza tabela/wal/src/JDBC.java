import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static JDBC instance = null;
    private static Connection connection = null;
    private  final String dbUrl = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
    private  final String user = "ziibd17";
    private  final String pass = "haslo2022";
    private JDBC() {
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static JDBC getInstance() {
        if (instance == null) {
            instance = new JDBC();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

    }



}

