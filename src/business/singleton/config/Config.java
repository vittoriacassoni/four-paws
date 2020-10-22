package business.singleton.config;

import java.sql.*;

public class Config {

    private static Config uniqueInstance;
    public static Connection con;
    private String url = "jdbc:sqlserver://sql5092.site4now.net;" +
            "databaseName=DB_A6939A_4paws;";  ;
    private String username = "DB_A6939A_4paws_admin";
    private String password = "4paws@123";

    private Config() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            this.con = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possível encontrar a classe" + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static synchronized Config getInstance() throws SQLException {

        System.out.println("a instancia é " + uniqueInstance);
        if (uniqueInstance == null) {
            uniqueInstance = new Config();
            System.out.println("criando config");

        } else if (uniqueInstance.getConnection().isClosed()) {
            uniqueInstance = new Config();
            System.out.println("criando config 2");
        }


        System.out.println(uniqueInstance);
        return uniqueInstance;
    }
}