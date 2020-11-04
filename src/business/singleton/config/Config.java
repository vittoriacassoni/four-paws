package business.singleton.config;

import comuns.enums.RepositoryType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static Config uniqueInstance;

    public static Connection con;
    private String url = "jdbc:sqlserver://sql5092.site4now.net;" +
            "databaseName=DB_A6939A_4paws;";
    private String username = "DB_A6939A_4paws_admin";
    private String password = "4paws@123";

    private Config() throws SQLException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            this.con = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Config getInstance() throws SQLException {
        System.out.println("a instancia é " + uniqueInstance);
        if (uniqueInstance == null) {
            uniqueInstance = new Config();
            System.out.println("criando config");
        }

        System.out.println(uniqueInstance);
        return uniqueInstance;
    }

    private RepositoryType repositoryType;

    public RepositoryType getRepositoryType() {
        return repositoryType;
    }

    public void setDataBase(RepositoryType repositoryType) {
        this.repositoryType = repositoryType;
    }

    public Connection getConnection() {
        return con;
    }
}