package business.services;

import business.Validates;
import comuns.access.Donation;
import dao.access.DonationSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;
import java.util.List;

public class DonationService {
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES
    DAO dao = new DonationSqlServerDAO();

    public List<Donation> validateId(Integer userId) throws SQLException {
        return dao.selectList(userId);
    }

    public boolean insert(Donation entity) throws Exception {
        if(Validates.validateRequiredField(String.valueOf(entity.getValue())) || Validates.validateRequiredField(entity.getDescription())){
            throw new Exception("Preencha todos os campos!");
        }
        return dao.insert(entity);
    }
}
