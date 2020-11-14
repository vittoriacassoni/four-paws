package business;

import comuns.access.User;
import dao.access.UserSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;

public class Access {

    public static boolean validateLogin(String email, String passwordHash) throws SQLException {
        DAO dao = new UserSqlServerDAO();
        User validated = (User) dao.select(email);
        if(validated != null){
            return passwordHash.equals(validated.getPasswordHash());
        }
        else {
            return false;
        }
    }
}
