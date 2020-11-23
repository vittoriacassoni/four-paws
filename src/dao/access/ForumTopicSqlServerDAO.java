package dao.access;

import business.Validates;
import comuns.access.ForumTopic;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.*;
import java.time.Instant;
import java.util.*;
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
            entity.setId(rs.getInt("Id"));
            entity.setTitle(rs.getString("Title"));
            entity.setDiscussion(rs.getString("Discussion"));
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
        ForumTopic topic = (ForumTopic) entity;
        try (Connection con = getConnection()) {
            String SQL = "INSERT INTO " + super.getTable() + " (Title, Discussion, UserId, CreatedAt)"
                    + " VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setString(1, topic.getTitle());
                stmt.setString(2, topic.getDiscussion());
                stmt.setInt(3, topic.getUserId());
                stmt.setString(4, Instant.now().toString());
                stmt.execute();
            } catch (Exception error) {
                error.printStackTrace();
                return false;
            }
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
                    rs.close();
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

    public ArrayList<E> listTopics(String titleFilter) {
        ArrayList<E> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            System.out.println(con);
            PreparedStatement add = null;
            String query = "SELECT F.*, U.Name, U.LastName FROM [ForumTopic] F INNER JOIN [User] U on U.Id = F.UserId " +
                    "WHERE F.DeletedAt is null";
            if(titleFilter.isBlank()){
                query += " ORDER BY F.CreatedAt desc";
                add = con.prepareStatement(query);
            } else{
                query += " and F.Title like ? ORDER BY F.CreatedAt desc";
                add = con.prepareStatement(query);
                add.setString(1, "%" + titleFilter + "%");
            }

            try (ResultSet rs = add.executeQuery()) {
                while (rs.next()) {
                    Entity entity = (E) fillEntity(rs);
                    list.add((E) entity);
                }
            } catch (Exception error) {
                con.rollback();
            }
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}




