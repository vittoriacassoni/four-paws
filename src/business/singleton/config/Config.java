package business.singleton.config;

import comuns.enums.RepositoryType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static Config uniqueInstance;

    private Config()  { }

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
}