package dao.access;

import comuns.access.Adoption;
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

public class AdoptionSqlServerDAO <E extends Entity> extends SqlServerDAO {

    public AdoptionSqlServerDAO() {
        super(Adoption.class);
        setTable("Adoption");
    }

    @Override
    protected E fillEntity(ResultSet rs) {
        Adoption entity = new Adoption();
        try {
            entity.setAnimalId(Integer.parseInt(rs.getString("AnimalId")));
            entity.setUserId(Integer.parseInt(rs.getString("UserId")));
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
        return true;
    }

    @Override
    public E select(String userId) throws SQLException {
        E entity = null;
        return entity;
    }

    @Override
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
