package dao.access;

import business.Validates;
import comuns.access.Animal;
import comuns.access.User;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimalSqlServerDAO<E extends Entity> extends SqlServerDAO {

    private String spListAnimalsAdopt = "spFindAnimals";
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
            entity.setAge(rs.getInt("Age"));
            entity.setImage(rs.getString("Image"));
            entity.setHistory(rs.getString("History"));
            entity.setCreatedAt(rs.getDate("CreatedAt"));
            entity.setUpdatedAt(rs.getDate("UpdatedAt"));
            entity.setDeletedAt(rs.getDate("DeletedAt"));
            if(rs.findColumn("MaxExpense") > 0){
                entity.setMaxExpense(rs.getDouble("MaxExpense"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserSqlServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E) entity;
    }

    @Override
    public boolean insert(Entity entity) throws SQLException {
        Animal animal = (Animal) entity;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(Instant.now());
        try (Connection con = getConnection()) {
            System.out.println(con);
            if (Validates.validateRequiredField(animal.getName()) || Validates.validateRequiredField(animal.getBreed()) ||
                    Validates.validateRequiredField(animal.getColor()) || Validates.validateRequiredField(animal.getSize().toString()) ||
                    Validates.validateRequiredField(animal.getWeight().toString()) || Validates.validateRequiredField(animal.getHistory())) {
                throw new Exception("Preencha todos os campos!");
            }

            String SQL = "INSERT INTO [" + super.getTable() + "] (Name, Breed, Color, Size, Weight, Image, History, " +
                    "CreatedAt, Age, AdoptionRequirementId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setString(1, animal.getName());
                stmt.setString(2, animal.getBreed());
                stmt.setString(3, animal.getColor());
                stmt.setDouble(4, animal.getSize());
                stmt.setDouble(5, animal.getWeight());
                stmt.setString(6, animal.getImage());
                stmt.setString(7, animal.getHistory());
                stmt.setString(8, Instant.now().toString());
                stmt.setDouble(9, animal.getAge());
                stmt.setInt(10, animal.getAdoptionRequirementId());
                stmt.execute();
            } catch(Exception error){
                error.printStackTrace();
                return false;
            }
            System.out.println(Instant.now());

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

            String query = "SELECT * FROM [" + super.getTable() + "] WHERE Id = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setInt(1, Integer.parseInt(id));

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

    public List<E> selectAll() throws SQLException {
        List<E> entities = new ArrayList<>();
        try (Connection con = getConnection()) {
            System.out.println(con);

            String query = "SELECT * FROM [" +  super.getTable() + "]";
            PreparedStatement add = con.prepareStatement(query);

            try (ResultSet rs = add.executeQuery()) {
                while (rs.next()) {
                    entities.add(fillEntity(rs));
                }
            } catch (Exception error) {
                con.rollback();
            }
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public List<E> listAnimalsToAdopt(Integer adoptionRequirementId){
        List<E> entities = new ArrayList<>();
        try (Connection con = getConnection()) {

            String query = "EXEC " +  spListAnimalsAdopt + " @AdoptionRequirementId=?";
            PreparedStatement add = con.prepareStatement(query);
            add.setInt(1, adoptionRequirementId);

            try (ResultSet rs = add.executeQuery()) {
                while (rs.next()) {
                    entities.add(fillEntity(rs));
                }
            } catch (Exception error) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entities;
    }

    public boolean update(Integer animalId) throws Exception {
        if(animalId > 0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try (Connection con = getConnection()) {
                String SQL = "UPDATE [" + super.getTable() + "] set Adopted = 1 WHERE Id = ?";

                try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                    stmt.setInt(1, animalId);
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
        } else{
            throw new Exception("Selecione um animal válido!");
        }

    }
}
