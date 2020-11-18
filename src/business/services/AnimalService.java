package business.services;

import comuns.access.Animal;
import comuns.access.User;
import dao.access.AnimalSqlServerDAO;
import dao.access.UserSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;

public class AnimalService {
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES
    DAO dao = new AnimalSqlServerDAO();

    public Animal validateId(String userId) throws SQLException {
        Animal validated = (Animal) dao.select(userId);
        return validated;
    }
}
