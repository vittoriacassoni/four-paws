package business.log.threads;

import comuns.access.Audit;
import dao.bases.DAO;
import dao.enums.EntityDAO;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadManageAudit extends Thread {

    private boolean status;

    @Override
    public void run() {
        setStatus(true);
        while (status) {
            try {
                Audit audit = ManageAudit.getInstance().removeAudit();
                if (audit != null) {
                    DAO dao = EntityDAO.AUDIT.getEntityDAO();
                    dao.insert(audit);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThreadManageAudit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setStatus(boolean value) {
        status = value;
    }

}
