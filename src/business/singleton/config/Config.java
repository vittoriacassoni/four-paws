package business.singleton.config;

import java.sql.*;

public class Config {

    private static Config uniqueInstance;
    private static Connection con;
    private String url = "jdbc:sql://127.0.0.1:3306/fourpaws";
    private String username = "root";
    private String password = " ";

    private Config() throws SQLException {

        try {

            Class.forName("com.sql.jdbc.Driver");

            this.con = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possível encontrar a classe" + ex.getMessage());
        }
    }

    public static Connection getConnection() {

        return con;
    }

    public static synchronized Config getInstance() throws SQLException {

        if (uniqueInstance == null) {
            uniqueInstance = new Config();

        } else if (uniqueInstance.getConnection().isClosed()) {
            uniqueInstance = new Config();
        }

        return uniqueInstance;
    }
}