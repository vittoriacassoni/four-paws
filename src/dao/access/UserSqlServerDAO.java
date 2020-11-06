package dao.access;

import business.Validates;
import business.log.threads.ManageAudit;
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
        try (Connection con = getConnection()) {
            System.out.println(con);
            if (Validates.validateRequiredField(user.getName()) || Validates.validateRequiredField(user.getLastName()) ||
                    Validates.validateRequiredField(user.getEmail()) || Validates.validateRequiredField(user.getPasswordHash()) ||
                    Validates.validateRequiredField(user.getDateOfBirth().toString())) {
                throw new Exception("Preencha todos os campos!");
            }

            String query = "INSERT INTO [User] (Name, LastName, Email, PasswordHash, DateOfBirth, " +
                    "UserRoleId, CreatedAt) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement add = con.prepareStatement(query);

            add.setString(1, user.getName());
            add.setString(2, user.getLastName());
            add.setString(3, user.getEmail());
            add.setString(4, user.getPasswordHash());
            add.setString(5, dateFormat.format(user.getDateOfBirth()));
            add.setInt(6, user.getUserRoleld());
            add.setString(7, Instant.now().toString());

            System.out.println(add);
            add.executeUpdate();

            add.close();
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

            String query = "SELECT * FROM [User] WHERE Email = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setString(1, email);

            try (ResultSet rs = add.executeQuery()) {
                if (rs.next()) {
                    entity = fillEntity(rs);
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
