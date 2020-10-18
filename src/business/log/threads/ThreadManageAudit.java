package business.log.threads;

import business.singleton.config.Config;

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
                String audit = ManageAudit.getInstance().removeAudit();
                if (audit != null) {
                    sendToDatabase();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThreadManageAudit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setStatus(boolean value) {
        status = value;
    }

    private void sendToDatabase() throws SQLException {
        Connection con = Config.getConnection();
        try {
            String query = "INSERT INTO logAudit (date, login, action) VALUES (?, ?, ?)";
            PreparedStatement add = con.prepareStatement(query);

            /*add.setString(1, Instant.now().toString());
            add.setString(2, txtID.getText());
            add.setString((3, ));*/

            add.executeUpdate();

            add.close();
            con.close();
        } finally {
            ManageAudit.getInstance().disable();
        }

    }

}
