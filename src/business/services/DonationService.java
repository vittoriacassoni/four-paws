package business.services;

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
}
