package business.singleton.config;
import java.sql.*;

public class Config {

    private static Config uniqueInstance;
    Connection con;

    private Config()  {

            try {
                Class.forName("com.sql.jdbc.Driver");

                con = DriverManager.getConnection("jdbc:sql://127.0.0.1:3306/fourpaws", "root", " ");

            } catch (ClassNotFoundException ex) {
                System.out.println("Não foi possível encontrar a classe");
            }catch (SQLException ex)
            {
                System.out.println("Ocorreu um erro de SQL.");
            }
        }

    public static synchronized Config getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Config();

        return uniqueInstance;
    }
}
