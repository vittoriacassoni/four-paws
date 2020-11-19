package dao.access;

import comuns.access.Donation;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            entity.setValue(rs.getFloat("Value"));
            entity.setDescription(rs.getString("Description"));
            entity.setCreatedAt(rs.getDate("CreatedAt"));
            entity.setUpdatedAt(rs.getDate("UpdatedAt"));
            entity.setDeletedAt(rs.getDate("DeletedAt"));
            entity.setUserId(Integer.parseInt(rs.getString("UserId")));
        } catch (SQLException ex) {
            Logger.getLogger(UserSqlServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E) entity;
    }

    @Override
    public boolean insert(Entity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Entity entity) throws SQLException {
        return false;
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
            con.commit();
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
