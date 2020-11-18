package business.services;


import comuns.access.Donation;
import dao.access.DonationSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;

public class DonationService {
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES
    DAO dao = new DonationSqlServerDAO();

    public Donation validateId(String userId) throws SQLException {
        Donation validated = (Donation) dao.select(userId);
        return validated;
    }
}
