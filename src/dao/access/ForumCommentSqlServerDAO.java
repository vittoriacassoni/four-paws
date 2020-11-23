package dao.access;

import comuns.access.ForumComment;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
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
            entity.setId(rs.getInt("Id"));
            entity.setDiscussion(rs.getString("Discussion"));
            entity.setForumTopicId(rs.getInt("ForumTopicId"));
            entity.setUserId(rs.getInt("UserId"));
            entity.setCreatedAt(rs.getDate("CreatedAt"));
            if(rs.findColumn("Name") > 0 && rs.findColumn("LastName") > 0){
                entity.setUserName(rs.getString("Name") + " " + rs.getString("LastName"));
            }
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
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<E> listComments(Integer topicId) throws SQLException {
        ArrayList<E> list = new ArrayList<E>();
        try (Connection con = getConnection()) {
            System.out.println(con);
            String query = "SELECT  F.*, U.Name, U.LastName FROM [ForumComment] F INNER JOIN [User] U on U.Id = F.UserId " +
                    "WHERE F.ForumTopicId = ? and F.DeletedAt is null";
            PreparedStatement add = con.prepareStatement(query);
            add.setInt(1, topicId);

            try (ResultSet rs = add.executeQuery()) {
                while (rs.next()) {
                    Entity entity = (E) fillEntity(rs);
                    list.add((E) entity);
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
