package business;

import comuns.access.User;
import dao.bases.DAO;
import dao.enums.EntityDAO;

import java.sql.SQLException;

public class Access {

    public static boolean validateLogin(String email, String passwordHash) throws SQLException {
        DAO dao = EntityDAO.USER.getEntityDAO();
        User validated = (User) dao.select(email);
        if(validated != null){
            return passwordHash.equals(validated.getPasswordHash());
        }
        else {
            return false;
        }
    }
}
