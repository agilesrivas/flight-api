package persistence;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connect {

    static Connection conexion;
    static Connect instance;

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = "root";
    private final String PASS = "";
    private final String nameDB = "db_tp5_flights"


    private Connect() {

        try {
            //STEP 2: Register JDBC driver
            Class.forName(this.JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Conectando a la base de datos...");
            conexion = (Connection) DriverManager.getConnection(this.nameDB, this.USER, this.PASS);

        } catch (SQLException se) {
            //Handle errors
            se.printStackTrace();

        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        }

    }
    public void closeConnect() {
        try {
            conexion.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
