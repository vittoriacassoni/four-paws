package dao.enums;

import business.singleton.config.Config;
import comuns.enums.RepositoryType;
import dao.access.AuditSqlServerDAO;
import dao.access.UserSqlServerDAO;
import dao.bases.DAO;


public enum EntityDAO {
    AUDIT(getAuditDAO()),
    USER(getUserDAO());

    DAO entityDAO;

    EntityDAO(DAO dao) {
        entityDAO = dao;
    }

    public DAO getEntityDAO() {
        return entityDAO;
    }

    static private DAO getAuditDAO() {
        if (Config.getInstance().getRepositoryType() == RepositoryType.SQLSERVER)
            return new AuditSqlServerDAO<>();
        return null;
    }

    static private DAO getUserDAO() {
        if(Config.getInstance().getRepositoryType() == RepositoryType.SQLSERVER)
            return new UserSqlServerDAO();
        return null;
    }
}
