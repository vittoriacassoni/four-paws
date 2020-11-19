package business.services;

import comuns.access.Animal;
import comuns.bases.Entity;
import dao.access.AnimalSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;
import java.util.List;

public class AnimalService {
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES
    DAO dao = new AnimalSqlServerDAO();

    public Animal validateId(String id) throws SQLException {
        return (Animal) dao.select(id);
    }
}
