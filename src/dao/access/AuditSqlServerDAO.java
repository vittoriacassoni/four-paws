package dao.access;

import business.log.threads.ManageAudit;
import comuns.access.Audit;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuditSqlServerDAO<E extends Entity> extends SqlServerDAO {
    public AuditSqlServerDAO() {
        super(Audit.class);
        setTable("Audit");
    }

    @Override
    protected E fillEntity(ResultSet rs) {
        Audit entity = new Audit();
        try {
            entity.setUserId(rs.getString("UserId"));
            entity.setAction(rs.getString("Action"));
            entity.setCreatedAt(rs.getString("CreatedAt"));
        } catch (SQLException ex) {
            Logger.getLogger(AuditSqlServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)entity;
    }

    @Override
    public boolean insert(Entity entity) throws SQLException {
        Audit audit = (Audit) entity;
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
            con.commit();
            return true;
        }
        finally {
            ManageAudit.getInstance().disable();
        }

    }
}
