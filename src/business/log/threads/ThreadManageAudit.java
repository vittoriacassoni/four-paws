package business.log.threads;

import comuns.access.Audit;
import dao.access.AuditSqlServerDAO;
import dao.bases.DAO;
import dao.enums.EntityDAO;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadManageAudit extends Thread {
    private boolean status;
    private final DAO<Audit> dao ;

    public ThreadManageAudit(DAO dao){
        this.dao = dao;
    }

    @Override
    public void run() {
        setStatus(true);
        while (status) {
            try {
                Audit audit = ManageAudit.getInstance().removeAudit();
                if (audit != null) {
                    System.out.println("caiu thread");
                    dao.insert(audit);
                    Thread.sleep(1);
                } else{
                    ManageAudit.getInstance().disable();
                    break;
                }
            } catch (SQLException | InterruptedException ex) {
                Logger.getLogger(ThreadManageAudit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setStatus(boolean value) {
        status = value;
    }

}
