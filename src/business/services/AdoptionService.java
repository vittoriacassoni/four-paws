package business.services;

import comuns.access.Adoption;
import dao.access.AdoptionSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;
import java.util.List;

public class AdoptionService {
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES
    DAO dao = new AdoptionSqlServerDAO();

    public List<Adoption> validateId(Integer userId) throws SQLException {
        return dao.selectList(userId);
    }

}
