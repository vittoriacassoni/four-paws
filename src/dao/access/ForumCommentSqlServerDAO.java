package dao.access;

import business.log.threads.ManageAudit;
import comuns.access.ForumComment;
import comuns.access.ForumTopic;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForumCommentSqlServerDAO <E extends Entity> extends SqlServerDAO {
    public ForumCommentSqlServerDAO() {
        super(ForumComment.class);
        setTable("ForumComment");
    }

    @Override
    protected Entity fillEntity(ResultSet rs) {
        ForumComment entity = new ForumComment();
        try {
            entity.setDiscussion(rs.getString("Discussion"));
            entity.setForumTopicId(rs.getInt("ForumTopicId"));
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
        ForumComment comment = (ForumComment) entity;
        System.out.println(con);
        try {
            String query = "INSERT INTO [ForumComment] (Discussion, ForumTopicId, UserId, CreatedAt, UpdatedAt, DeletedAt) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement add = con.prepareStatement(query);

            add.setString(1, comment.getDiscussion());
            add.setInt(2, comment.getForumTopicId());
            add.setInt(3, comment.getUserId());
            add.setString(4, Instant.now().toString());
            add.setString(5, Instant.now().toString());
            add.setString(6, Instant.now().toString());

            System.out.println(add);
            add.executeUpdate();

            add.close();
            con.close();
            return  true;
        } finally {
            ManageAudit.getInstance().disable();
        }
    }
}
