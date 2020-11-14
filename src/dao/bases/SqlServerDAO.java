package dao.bases;
import business.singleton.config.Config;
import comuns.bases.Entity;

import java.sql.*;
import java.util.ArrayList;

public abstract class SqlServerDAO <E extends Entity> extends DAO {
   // public  Connection con;
    private String url = "jdbc:sqlserver://sql5092.site4now.net;" +
            "databaseName=DB_A6939A_4paws;sendStringParametersAsUnicode=false;";
    private String username = "DB_A6939A_4paws_admin";
    private String password = "4paws@123";

    private String table;

    protected SqlServerDAO(Class<E> Entity) {
        super(Entity);
    }

    public Connection getConnection() {
        Connection con = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Não foi possível encontrar a classe" + ex.getMessage());
        }
        return con;
    }

    protected void setTable(String value) { table = value; }

    protected String getTable() {
        return table;
    }
    @Override
    public E select(String email) throws SQLException {
        return null;
    }

    @Override
    public ArrayList list() throws SQLException {
        return null;
    }

    @Override
    public boolean insert(Entity entity) throws SQLException {
        return true;
    }

    protected abstract E fillEntity(ResultSet rs);


    public E selectID(int id) throws SQLException{
        E entity = null;
        try (Connection con = getConnection()) {
            System.out.println(con);

            String query = "SELECT * FROM [" + table + "] WHERE Id = ?";
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
