package business.log.threads;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ManageAudit {
    ConcurrentLinkedQueue<String> queueAudit;

    private static ManageAudit instance;

    private ManageAudit() {
        queueAudit = new ConcurrentLinkedQueue<>();
    }

    public static ManageAudit getInstance() {
        if (instance == null) {
            instance = new ManageAudit();
        }
        return instance;
    }

    /*MARI: Chamar a Thread que salva aqui, Ex:
    * ThreadManageAudit thread; */

    public void addAudit(String addAudit) {
        queueAudit.add(addAudit);
    }

    String removeAudit() {
        String audit = queueAudit.poll();
        return audit;
    }

    /* thread = NOME DA SUA VARÍAVEL, PODE MUDAR E DESCOMENTAR O MÉTODO
    *  ThreadManageAudit = NOME DO SEU MÉTODO, PODE MUDAR E DESCOMENTAR O MÉTODO
    public void activate() {
        if (thread == null) {
            thread = new ThreadManageAudit();
            thread.start();
        }
    }
    */

    /* thread = NOME DA SUA VARÍAVEL, PODE MUDAR E DESCOMENTAR O MÉTODO
    * setStatus = NOME DA SUA VARÍAVEL, PODE MUDAR E DESCOMENTAR O MÉTODO
    public void disable() {
        if (thread != null) {
            thread.setStatus(false);
            try {
                thread.join(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ManageAudit.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (thread.isAlive())
                thread.interrupt();
        }
    }
    */

}
