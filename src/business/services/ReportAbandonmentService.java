package business.services;

import business.Validates;
import comuns.access.ReportAbandonment;
import comuns.access.User;
import dao.access.ReportAbandonmentSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;

public class ReportAbandonmentService {
    DAO dao = new ReportAbandonmentSqlServerDAO();
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES

    public boolean insert(ReportAbandonment reportAbandonment) throws Exception {
        if (Validates.validateRequiredField(reportAbandonment.getAddress()) ||
                Validates.validateRequiredField(String.valueOf(reportAbandonment.getLastSeen())) ||
                Validates.validateRequiredField(String.valueOf(reportAbandonment.getUserId()))) {
            throw new Exception("Preencha todos os campos!");
        }
        validateHost();
        return dao.insert(reportAbandonment);
    }

    public User validateId(int id) throws SQLException {
        User validated = (User) dao.selectID(id);
        return validated;
    }

    private void validateHost() throws Exception {
        ReportAbandonment host = new ReportAbandonment();
        if (host.getTemporaryHome() == true) {
            Validates.validateRequiredField(host.getHostName());
            Validates.validateRequiredField(host.getHostContact());
            throw new Exception("Preencha todos os campos!");
        }
    }
}
