package business.log.threads;

import comuns.access.Audit;
import dao.access.AuditSqlServerDAO;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageAudit {
    ConcurrentLinkedQueue<Audit> queueAudit;

    private static ManageAudit instance;

    private ManageAudit() {
        queueAudit = new ConcurrentLinkedQueue<>();
    }

    public static synchronized ManageAudit getInstance() {
        if (instance == null) {
            instance = new ManageAudit();
        }
        return instance;
    }

    ThreadManageAudit thread;

    public void addAudit(Audit addAudit) {
        queueAudit.add(addAudit);
    }

    Audit removeAudit() {
        Audit audit = queueAudit.poll();
        return audit;
    }

    public void activate() {
        if (thread == null) {
            AuditSqlServerDAO dao = new AuditSqlServerDAO();
            thread = new ThreadManageAudit(dao);
            thread.start();
        }
    }

    public void disable() {
        if (thread != null) {
            thread.setStatus(false);
            thread = null;
        }
    }
}
