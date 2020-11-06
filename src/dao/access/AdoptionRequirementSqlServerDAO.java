package dao.access;

import business.log.threads.ManageAudit;
import comuns.access.AdoptionRequirement;
import comuns.bases.Entity;
import dao.bases.SqlServerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdoptionRequirementSqlServerDAO<E extends Entity> extends SqlServerDAO {
    public AdoptionRequirementSqlServerDAO() {
        super(AdoptionRequirement.class);
        setTable("AdoptionRequirement");
    }

    @Override
    protected E fillEntity(ResultSet rs) {
        AdoptionRequirement entity = new AdoptionRequirement();
        try {
            entity.setMaxExpense(rs.getDouble("MaxExpense"));
            entity.setRequiredSpace(rs.getDouble("RequiredSpace"));
            entity.setRequiredSpace(rs.getDouble("AgePreference"));
            entity.setIsAngry(rs.getBoolean("IsAngry"));
            entity.setIsHappy(rs.getBoolean("IsHappy"));
            entity.setIsNeedy(rs.getBoolean("IsNeedy"));
            entity.setIsCaring(rs.getBoolean("IsCaring"));
            entity.setIsQuiet(rs.getBoolean("IsQuiet"));
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
        AdoptionRequirement adoptionRequirement = (AdoptionRequirement) entity;
        try (Connection con = getConnection()) {
            System.out.println(con);
            String query = "INSERT INTO [AdoptionRequirement] (MaxExpense, RequiredSpace, AgePreference, IsAngry, IsHappy, " +
                    "IsNeedy, IsCaring, IsQuiet, CreatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement add = con.prepareStatement(query);

            add.setDouble(1, adoptionRequirement.getMaxExpense());
            add.setDouble(2, adoptionRequirement.getRequiredSpace());
            add.setDouble(3, adoptionRequirement.getAgePreference());
            add.setBoolean(4, adoptionRequirement.getIsAngry());
            add.setBoolean(5, adoptionRequirement.getIsHappy());
            add.setBoolean(6, adoptionRequirement.getIsNeedy());
            add.setBoolean(7, adoptionRequirement.getIsCaring());
            add.setBoolean(8, adoptionRequirement.getIsQuiet());
            add.setString(9, Instant.now().toString());

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

    @Override
    public E select(String userId) throws SQLException {
        E entity = null;
        try (Connection con = getConnection()) {
            System.out.println(con);

            String query = "SELECT * FROM [AdoptionRequirement] WHERE UserId = ?";
            PreparedStatement add = con.prepareStatement(query);
            add.setString(1, userId);

            try (ResultSet rs = add.executeQuery()) {
                if (rs.next()) {
                    entity = fillEntity(rs);
                }
            } catch (Exception error) {
                con.rollback();
            }
            con.commit();
        }
        return entity;
    }
}