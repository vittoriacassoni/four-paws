package business.services;

import comuns.access.Animal;
import dao.access.AnimalSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;
import java.util.List;

public class AnimalService {
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES
    DAO dao = new AnimalSqlServerDAO();

    public List<Animal> validateId(Integer userId) throws SQLException {
        return dao.selectList(userId);
    }
}
