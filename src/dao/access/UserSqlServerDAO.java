package dao.access;

import business.Validates;
import comuns.access.User;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserSqlServerDAO<E extends Entity> extends SqlServerDAO {
    public UserSqlServerDAO() {
        super(User.class);
        setTable("User");
    }

    @Override
    protected E fillEntity(ResultSet rs) {
        User entity = new User();
        try {
            entity.setId(Integer.parseInt(rs.getString("Id")));
            entity.setName(rs.getString("Name"));
            entity.setLastName(rs.getString("LastName"));
            entity.setEmail(rs.getString("Email"));
            entity.setPasswordHash(rs.getString("PasswordHash"));
            entity.setImage(rs.getString("Image"));
            entity.setDateOfBirth(rs.getDate("DateOfBirth"));
            entity.setUserRoleld(rs.getInt("UserRoleId"));
            entity.setAdoptionRequirementId(rs.getInt("AdoptionRequirementId"));
            entity.setUpdatedAt(rs.getDate("UpdatedAt"));
            entity.setDeletedAt(rs.getDate("DeletedAt"));
        } catch (SQLException ex) {
            Logger.getLogger(UserSqlServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E) entity;
    }

    @Override
    public boolean insert(Entity entity) throws SQLException {
        User user = (User) entity;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(Instant.now());
        try (Connection con = getConnection()) {
            System.out.println(con);
            if (Validates.validateRequiredField(user.getName()) || Validates.validateRequiredField(user.getLastName()) ||
                    Validates.validateRequiredField(user.getEmail()) || Validates.validateRequiredField(user.getPasswordHash()) ||
                    Validates.validateRequiredField(user.getDateOfBirth().toString())) {
                throw new Exception("Preencha todos os campos!");
            }

            String SQL = "INSERT INTO [" + super.getTable() + "] (Name, LastName, Email, PasswordHash, Image, DateOfBirth, " +
                    "UserRoleId, CreatedAt) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getLastName());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getPasswordHash());
                stmt.setString(5, dateFormat.format(user.getDateOfBirth()));
                stmt.setInt(6, user.getUserRoleld());
                stmt.setString(7, Instant.now().toString());

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
    public boolean update(Entity entity) throws SQLException {
        User user = (User) entity;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(Instant.now());
        try (Connection con = getConnection()) {
            System.out.println(con);
            if (Validates.validateRequiredField(user.getName()) || Validates.validateRequiredField(user.getLastName()) ||
                    Validates.validateRequiredField(user.getEmail()) || Validates.validateRequiredField(user.getPasswordHash())) {
                throw new Exception("Preencha todos os campos!");
            }

            String SQL = "UPDATE [" + super.getTable() + "] set Name = ? , LastName = ?, Email = ?, PasswordHash = ?, " +
                    "UpdatedAt = ? WHERE Id = ?";

            try (PreparedStatement stmt = con.prepareStatement(SQL)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getLastName());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getPasswordHash());
                stmt.setString(5, Instant.now().toString());
                stmt.setInt(6, user.getId());
                stmt.execute();
            } catch(Exception error){
                error.printStackTrace();
            }
            System.out.println(Instant.now());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public E select(String email) throws SQLException {
        E entity = null;
        try (Connection con = getConnection()) {
            System.out.println(con);

            String query = "SELECT * FROM [" + super.getTable() + "] WHERE Email = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setString(1, email);

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

    @Override
    public E selectID(int id) throws SQLException {
        E entity = null;
        try (Connection con = getConnection()) {
            System.out.println(con);

            String query = "SELECT * FROM [" + super.getTable() + "] WHERE Id = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setInt(1, id);

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
}
