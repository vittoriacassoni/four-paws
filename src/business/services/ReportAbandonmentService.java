package business.services;

import business.Validates;
import comuns.access.ReportAbandonment;
import dao.access.ReportAbandonmentSqlServerDAO;

import java.util.Date;

public class ReportAbandonmentService {
    ReportAbandonmentSqlServerDAO dao = new ReportAbandonmentSqlServerDAO();
    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES

    public boolean insert(ReportAbandonment reportAbandonment, String date) throws Exception {
        if (Validates.validateRequiredField(reportAbandonment.getAddress()) ||
                Validates.validateRequiredField(String.valueOf(reportAbandonment.getLastSeen())) ||
                Validates.validateRequiredField(String.valueOf(reportAbandonment.getUserId()))) {
            throw new Exception("Preencha todos os campos!");
        }
        Date lastSeen = Validates.validateDate(date);
        reportAbandonment.setLastSeen(lastSeen);
        validateHost(reportAbandonment);
        return dao.insert(reportAbandonment);
    }

    private boolean validateHost(ReportAbandonment host) throws Exception {
        if (host.getTemporaryHome() == true) {
            if(Validates.validateRequiredField(host.getHostName()) ||
                    Validates.validateRequiredField(host.getHostContact()))
            throw new Exception("Preencha as informações referentes ao anfitrião!");
        }
        return true;
    }


}
