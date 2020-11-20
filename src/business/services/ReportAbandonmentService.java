package business.services;

import business.Validates;
import comuns.access.ReportAbandonment;
import dao.access.ReportAbandonmentSqlServerDAO;
import dao.bases.DAO;

public class ReportAbandonmentService {
    DAO dao = new ReportAbandonmentSqlServerDAO();
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES

    public boolean insert(ReportAbandonment reportAbandonment) throws Exception {
        if (Validates.validateRequiredField(reportAbandonment.getAddress()) ||
                Validates.validateRequiredField(String.valueOf(reportAbandonment.getLastSeen())) ||
                Validates.validateRequiredField(String.valueOf(reportAbandonment.getUserId()))) {
            throw new Exception("Preencha todos os campos!");
        }
        return true;
    }
}
