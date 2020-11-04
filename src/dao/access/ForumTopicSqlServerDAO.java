package dao.access;

import business.log.threads.ManageAudit;
import comuns.access.ForumTopic;
import comuns.access.User;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForumTopicSqlServerDAO <E extends Entity> extends SqlServerDAO {
    public ForumTopicSqlServerDAO() {
        super(ForumTopic.class);
        setTable("ForumTopic");
    }

    @Override
    protected Entity fillEntity(ResultSet rs) {
        ForumTopic entity = new ForumTopic();
        try {
            entity.setTitle(rs.getString("Title"));
            entity.setUserId(rs.getInt("UserId"));
            entity.setCreatedAt(rs.getDate("CreatedAt"));
            entity.setUpdatedAt(rs.getDate("UpdatedAt"));
            entity.setDeletedAt(rs.getDate("DeletedAt"));
        } catch (SQLException ex) {
            Logger.getLogger(UserSqlServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E) entity;
    }

    @Override
    public boolean insert(Entity entity) throws SQLException {
        ForumTopic topic = (ForumTopic) entity;
        System.out.println(con);
        try {
            String query = "INSERT INTO [ForumTopic] (Title, UserId, CreatedAt, UpdatedAt, DeletedAt) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement add = con.prepareStatement(query);

            add.setString(1, topic.getTitle());
            add.setInt(2, topic.getUserId());
            add.setString(3, Instant.now().toString());
            add.setString(4, Instant.now().toString());
            add.setString(5, Instant.now().toString());

            System.out.println(add);
            add.executeUpdate();

            add.close();
            con.close();
            return true;
        } finally {
            ManageAudit.getInstance().disable();
        }
    }

}




