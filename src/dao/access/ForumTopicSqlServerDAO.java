package dao.access;

import business.Validates;
import business.log.threads.ManageAudit;
import comuns.access.ForumTopic;
import comuns.access.User;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForumTopicSqlServerDAO<E extends Entity> extends SqlServerDAO {
    public ForumTopicSqlServerDAO() {
        super(ForumTopic.class);
        setTable("ForumTopic");
    }

    @Override
    protected Entity fillEntity(ResultSet rs) {
        ForumTopic entity = new ForumTopic();
        try {
            entity.setTitle(rs.getString("Title"));
            entity.setDiscussion(rs.getString("Discussion"));
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
        try (Connection con = getConnection()) {
            System.out.println(con);
            if (Validates.validateRequiredField(topic.getTitle()) || Validates.validateRequiredField(topic.getDiscussion())) {
                throw new Exception("Preencha todos os campos!");
            }
            String query = "INSERT INTO [ForumTopic] (Title, Discussion, UserId, CreatedAt, UpdatedAt, DeletedAt) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement add = con.prepareStatement(query);

            add.setString(1, topic.getTitle());
            add.setString(2, topic.getDiscussion());
            add.setInt(3, topic.getUserId());
            add.setString(4, Instant.now().toString());
            add.setString(5, Instant.now().toString());
            add.setString(6, Instant.now().toString());

            System.out.println(add);
            add.executeUpdate();

            add.close();
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public E select(Integer id) throws SQLException {
        E entity = null;
        try (Connection con = getConnection()) {
            System.out.println(con);
            String query = "SELECT * FROM [ForumTopic] WHERE UserId = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setInt(1, id);

            try (ResultSet rs = add.executeQuery()) {
                if (rs.next()) {
                    entity = (E) fillEntity(rs);
                }
            } catch (Exception error) {
                con.rollback();
            }
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

}




