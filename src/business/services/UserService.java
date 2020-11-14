package business.services;

import comuns.access.User;
import dao.access.UserSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;

public class UserService {
    DAO dao = new UserSqlServerDAO();

    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES

    //Método para validar o email e senha de login do usuário
    public boolean validateLogin(String email, String passwordHash) throws SQLException {
        User validated = (User) dao.select(email);
        if(validated != null){
            return passwordHash.equals(validated.getPasswordHash());
        }
        else {
            return false;
        }
    }

    //Método para validar qual o id do usuário
    public User validateId(int id) throws SQLException {
        User validated = (User) dao.selectID(id);
        return validated;
    }
}
