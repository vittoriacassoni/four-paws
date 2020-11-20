package dao.access;

<<<<<<< HEAD
import comuns.access.AdoptionRequirement;
=======
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1
import comuns.access.Donation;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DonationSqlServerDAO<E extends Entity> extends SqlServerDAO {
=======
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DonationSqlServerDAO <E extends Entity> extends SqlServerDAO {

>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1
    public DonationSqlServerDAO() {
        super(Donation.class);
        setTable("Donation");
    }

<<<<<<< HEAD

=======
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1
    @Override
    protected E fillEntity(ResultSet rs) {
        Donation entity = new Donation();
        try {
<<<<<<< HEAD
            entity.setValue(rs.getDouble("Value"));
            entity.setDescription(rs.getString("Description"));
            entity.setUserId(rs.getInt("UserId"));
            entity.setCreatedAt(rs.getDate("CreatedAt"));
            entity.setUpdatedAt(rs.getDate("UpdatedAt"));
            entity.setDeletedAt(rs.getDate("DeletedAt"));
=======
            entity.setValue(rs.getFloat("Value"));
            entity.setDescription(rs.getString("Description"));
            entity.setCreatedAt(rs.getDate("CreatedAt"));
            entity.setUpdatedAt(rs.getDate("UpdatedAt"));
            entity.setDeletedAt(rs.getDate("DeletedAt"));
            entity.setUserId(Integer.parseInt(rs.getString("UserId")));
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1
        } catch (SQLException ex) {
            Logger.getLogger(UserSqlServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E) entity;
    }

    @Override
    public boolean insert(Entity entity) throws SQLException {
<<<<<<< HEAD
        Donation donation = (Donation) entity;
        try (Connection con = getConnection()) {
            System.out.println(con);

            String SQL = "INSERT INTO " + super.getTable() + " (Value, Description, UserId, CreatedAt )" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setDouble(1, donation.getValue());
                stmt.setString(2, donation.getDescription());
                stmt.setInt(3, donation.getUserId());
                stmt.setString(9, Instant.now().toString());
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

    @Override
    public E select(String userId) throws SQLException {
=======
        return false;
    }

    @Override
    public boolean update(Entity entity) throws SQLException {
        return false;
    }

    @Override
    public E select(String id) throws SQLException {
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1
        E entity = null;
        try (Connection con = getConnection()) {
            System.out.println(con);

<<<<<<< HEAD
            String query = "SELECT * FROM [Donation] WHERE UserId = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setString(1, userId);
=======
            String query = "SELECT * FROM [" + super.getTable() + "] WHERE UserId = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setString(1, id);
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1

            try (ResultSet rs = add.executeQuery()) {
                if (rs.next()) {
                    entity = fillEntity(rs);
                    rs.close();
                }
            } catch (Exception error) {
                con.rollback();
            }
            con.commit();
<<<<<<< HEAD
        }
        return entity;
    }
}
=======
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    public List<E> selectList(Integer userId) throws SQLException {
        List<E> entities = new ArrayList<>();

        try (Connection con = getConnection()) {
            System.out.println(con);

            String query = "SELECT * FROM [" + super.getTable() + "] WHERE UserId = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setInt(1, userId);

            try (ResultSet rs = add.executeQuery()) {
                while(rs.next()) {
                    entities.add(fillEntity(rs));
                }
            } catch (Exception error) {
                con.rollback();
            }
            con.commit();
        }
        return entities;
    }

}
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1
