package dao.access;

import comuns.access.Audit;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
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
        try (Connection con = getConnection()) {
            String SQL = "INSERT INTO " + super.getTable() + " (UserId, Action, CreatedAt)"
                    + " VALUES(?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setString(1, audit.getUserId());
                stmt.setString(2, audit.getAction());
                stmt.setString(3, Instant.now().toString());
                stmt.execute();
            } catch(Exception error){
                error.printStackTrace();
            }

            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
