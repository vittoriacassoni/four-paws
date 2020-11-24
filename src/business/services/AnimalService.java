package business.services;

import comuns.access.Adoption;
import comuns.access.Animal;
import comuns.bases.Entity;
import dao.access.AdoptionSqlServerDAO;
import dao.access.AnimalSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;
import java.util.List;

public class AnimalService {
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES
    AnimalSqlServerDAO dao = new AnimalSqlServerDAO();
    AdoptionSqlServerDAO adoptionDao = new AdoptionSqlServerDAO();

    public Animal validateId(String id) throws SQLException {
        return (Animal) dao.select(id);
    }

    public List<Animal> validateAll() throws SQLException {
        return dao.selectAll();
    }

    public List<Animal> listAnimalsToAdopt(Integer adoptionRequirementId) throws SQLException {
        return dao.listAnimalsToAdopt(adoptionRequirementId);
    }

    public boolean update(Integer animalId, Integer userId) throws Exception {
        Adoption adoption = new Adoption(animalId, userId);
        if(dao.update(animalId)){
            adoptionDao.insert(adoption);
            return true;
        }
        return false;
    }
}
