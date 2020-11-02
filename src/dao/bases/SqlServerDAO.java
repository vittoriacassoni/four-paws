package dao.bases;
import comuns.bases.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SqlServerDAO <E extends Entity> extends DAO {
    public Connection con;
    private String url = "jdbc:sqlserver://sql5092.site4now.net;" +
            "databaseName=DB_A6939A_4paws;";  ;
    private String username = "DB_A6939A_4paws_admin";
    private String password = "4paws@123";
    private String table;


    protected SqlServerDAO(Class<E> Entity) {
        super(Entity);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            this.con = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Não foi possível encontrar a classe" + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return con;
    }

    protected void setTable(String value) { table = value; }

    @Override
    public E select(String email) throws SQLException {
        return null;
    }

    @Override
    public ArrayList list() throws SQLException {
        return null;
    }

    @Override
    public void insert(Entity entity) throws SQLException {

    }

    protected abstract E fillEntity(ResultSet rs);

}
