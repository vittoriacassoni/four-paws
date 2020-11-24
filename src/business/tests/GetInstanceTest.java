package business.tests;

import business.singleton.LocalStorage;
import comuns.access.User;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class GetInstanceTest {

    @Test
    public void adoptionRequirement() throws IOException, SQLException {
        User user = new User();

        LocalStorage.getInstance().saveUserId(String.valueOf(22));
        LocalStorage.getInstance().saveUserEmail("admin@gmail.com");
        LocalStorage.getInstance().saveUserName("Admin");
        LocalStorage.getInstance().saveUserLastName("Master");
        LocalStorage.getInstance().saveUserRoleId(String.valueOf(1));

        int userId = LocalStorage.getInstance().getUserId();
        String email = LocalStorage.getInstance().getUserEmail();

        assertEquals(22, userId);
        assertEquals("admin@gmail.com", email);
    }
}

