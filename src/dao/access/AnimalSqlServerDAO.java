package dao.access;

import comuns.access.Animal;
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

public class AnimalSqlServerDAO<E extends Entity> extends SqlServerDAO {

    public AnimalSqlServerDAO() {
        super(Animal.class);
        setTable("Animal");
    }

    @Override
    protected E fillEntity(ResultSet rs) {
        Animal entity = new Animal();
        try {
            entity.setName(rs.getString("Name"));
            entity.setBreed(rs.getString("Breed"));
            entity.setColor(rs.getString("Color"));
            entity.setSize(rs.getDouble("Size"));
            entity.setWeight(rs.getDouble("Weight"));
            entity.setImage(rs.getString("Image"));
            entity.setDateOfBirth(rs.getDate("DateOfBirth"));
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
        Animal animal = (Animal) entity;
        try (Connection con = getConnection()) {
            System.out.println(con);

            String SQL = "INSERT INTO" + super.getTable() + "(Name, Breed, Color, Size, Weight, " +
                    "Image, DateOfBirth, CreadtedAt, UpdatedAt,DeletedAt) VALUES ('" + animal.getName() + "','" +
                    animal.getBreed() + "','" + animal.getColor() + "', '" + animal.getSize() + "' '" +
                    animal.getWeight() + "', '" + animal.getImage() + "' , '" + animal.getDateOfBirth();

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.execute();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

            String query = "SELECT * FROM [" + super.getTable() + "]";
            PreparedStatement add = con.prepareStatement(query);

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
