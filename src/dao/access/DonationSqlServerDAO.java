package dao.access;


import comuns.access.Donation;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DonationSqlServerDAO <E extends Entity> extends SqlServerDAO {

    public DonationSqlServerDAO() {
        super(Donation.class);
        setTable("Donation");
    }

    @Override
    protected E fillEntity(ResultSet rs) {
        Donation entity = new Donation();
        try {
            entity.setValue(rs.getDouble("Value"));
            entity.setDescription(rs.getString("Description"));
            entity.setUserId(rs.getInt("UserId"));
            entity.setCreatedAt(rs.getDate("CreatedAt"));

        } catch (SQLException ex) {
            Logger.getLogger(UserSqlServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E) entity;
    }

    @Override
    public boolean insert(Entity entity) throws SQLException {
        Donation donation = (Donation) entity;
        try (Connection con = getConnection()) {
            System.out.println(con);

            String SQL = "INSERT INTO " + super.getTable() + " (Value, Description, UserId, CreatedAt)" +
                    "VALUES(?, ?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setDouble(1, donation.getValue());
                stmt.setString(2, donation.getDescription());
                stmt.setInt(3, donation.getUserId());
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

    @Override
    public E select(String id) throws SQLException {
        E entity = null;
        try (Connection con = getConnection()) {
            System.out.println(con);

            String query = "SELECT * FROM [" + super.getTable() + "] WHERE UserId = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setString(1, id);

            try (ResultSet rs = add.executeQuery()) {
                if (rs.next()) {
                    entity = fillEntity(rs);
                    rs.close();
                }
            } catch (Exception error) {
                con.rollback();
            }
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
        }
        return entities;
    }

}

