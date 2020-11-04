package dao.enums;

import business.singleton.config.Config;
import comuns.enums.RepositoryType;
import dao.access.AuditSqlServerDAO;
import dao.access.AdoptionRequirementSqlServerDAO;
import dao.access.UserSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;


public enum EntityDAO {
    /*
    DAO entityDAO;

    EntityDAO(DAO dao) {
        entityDAO = dao;
    }

    public DAO getEntityDAO() {
        return entityDAO;
    }

    static private DAO getAuditDAO() throws SQLException {
        if (Config.getInstance().getRepositoryType() == RepositoryType.SQLSERVER)
            return new AuditSqlServerDAO<>();
        return null;
    }

    static private DAO getUserDAO() throws SQLException {
        if(Config.getInstance().getRepositoryType() == RepositoryType.SQLSERVER)
            return new UserSqlServerDAO<>();
        return null;
    }

    static private DAO getAdoptionRequirementDAO() throws SQLException {
        if(Config.getInstance().getRepositoryType() == RepositoryType.SQLSERVER)
            return new AdoptionRequirementSqlServerDAO<>();
        return null;
    }*/
}