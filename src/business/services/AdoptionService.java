package business.services;

import comuns.access.Animal;
import dao.access.AnimalSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;

public class AdoptionService {
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES
    DAO dao = new AdoptionSq();

    public Animal validateId(String userId) throws SQLException {
        Animal validated = (Animal) dao.select(userId);
        return validated;
    }
}
