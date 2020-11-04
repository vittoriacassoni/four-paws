package business.singleton.config;

import comuns.enums.RepositoryType;

public class Config {

    private static Config uniqueInstance;

    public static synchronized Config getInstance() {

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