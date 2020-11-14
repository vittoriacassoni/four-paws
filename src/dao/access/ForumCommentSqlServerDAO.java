package dao.access;

import comuns.access.ForumComment;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForumCommentSqlServerDAO<E extends Entity> extends SqlServerDAO {
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
        try (Connection con = getConnection()) {
            System.out.println(con);

            String SQL = "INSERT INTO " + super.getTable() + " (Discussion, ForumTopicId, UserId, CreatedAt)"
                    + " VALUES(?, ?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setString(1, comment.getDiscussion());
                stmt.setInt(2, comment.getForumTopicId());
                stmt.setInt(3, comment.getUserId());
                stmt.setString(4, Instant.now().toString());
                stmt.execute();
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
