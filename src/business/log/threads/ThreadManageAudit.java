package business.log.threads;

import business.log.Audit;
import business.singleton.config.Config;

import javax.sound.midi.SysexMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
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
                    sendToDatabase(audit);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThreadManageAudit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setStatus(boolean value) {
        status = value;
    }

    private void sendToDatabase(Audit audit) throws SQLException {
        var con = Config.getInstance().getConnection();
        System.out.println(con);
        try {
            String query = "INSERT INTO Audit (UserId, Action, CreatedAt) VALUES (?, ?, ?)";
            PreparedStatement add = con.prepareStatement(query);

            add.setString(1, audit.getUserId());
            add.setString(2, audit.getAction());
            add.setString(3, Instant.now().toString());

            System.out.println(add);
            add.executeUpdate();

            add.close();
            con.close();
        } finally {
            ManageAudit.getInstance().disable();
        }

    }

}
