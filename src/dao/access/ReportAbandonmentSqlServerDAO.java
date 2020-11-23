package dao.access;

import business.Validates;
import comuns.access.ReportAbandonment;
import comuns.access.User;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportAbandonmentSqlServerDAO <E extends Entity> extends SqlServerDAO {
    public ReportAbandonmentSqlServerDAO() {
        super(User.class);
        setTable("ReportAbandonment");
    }

    @Override
    protected E fillEntity(ResultSet rs) {
        ReportAbandonment entity = new ReportAbandonment();
        try {
            entity.setId(Integer.parseInt(rs.getString("Id")));
            entity.setAddress(rs.getString("Address"));
            entity.setTemporaryHome(rs.getBoolean("TemporaryHome"));
            if(entity.getTemporaryHome() == true) {
                entity.setHostName(rs.getString("HostName"));
                entity.setHostContact(rs.getString("HostContact"));
            }
            entity.setLastSeen(rs.getDate("LastSeen"));
            entity.setUserId(Integer.parseInt(rs.getString("UserId")));
        } catch (SQLException ex) {
            Logger.getLogger(UserSqlServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E) entity;
    }

    @Override
    public boolean insert(Entity entity) throws SQLException {
        ReportAbandonment reportAbandonment = (ReportAbandonment) entity;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (Connection con = getConnection()) {
            String SQL = "INSERT INTO" + super.getTable() + "(Address, UserId, LastSeen, TemporaryHome, HostName, " +
                    "HostContact, CreatedAt) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setString(1, reportAbandonment.getAddress());
                stmt.setInt(2, reportAbandonment.getUserId());
                stmt.setString(3, dateFormat.format(reportAbandonment.getLastSeen()));
                stmt.setBoolean(4, reportAbandonment.getTemporaryHome());
                if(reportAbandonment.getTemporaryHome()) {
                    stmt.setString(5, reportAbandonment.getHostName());
                    stmt.setString(6, reportAbandonment.getHostContact());
                } else {
                    stmt.setString(5, null);
                    stmt.setString(6, null);
                }
                stmt.setString(7, Instant.now().toString());

                stmt.execute();
                return false;
            } catch(Exception error){
                error.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
