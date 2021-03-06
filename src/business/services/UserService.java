package business.services;

import business.Validates;
import comuns.access.ReportAbandonment;
import comuns.access.User;
import comuns.bases.Entity;
import dao.access.UserSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    DAO dao = new UserSqlServerDAO();

    //TODO - CHAMADA DOS METODOS DA DAO E VALIDAÇÕES

    public boolean insert(User user) throws Exception {
        if (Validates.validateRequiredField(user.getName()) || Validates.validateRequiredField(user.getLastName()) ||
                Validates.validateRequiredField(user.getEmail()) || Validates.validateRequiredField(user.getPasswordHash()) ||
                Validates.validateRequiredField(user.getDateOfBirth().toString())) {
            throw new Exception("Preencha todos os campos!");
        }
        return dao.insert(user);
    }

    public boolean update(User user) throws Exception {
        if (Validates.validateRequiredField(user.getName()) || Validates.validateRequiredField(user.getLastName()) ||
                Validates.validateRequiredField(user.getEmail()) || Validates.validateRequiredField(user.getPasswordHash())) {
            throw new Exception("Preencha todos os campos!");
        }
        return dao.update(user);
    }

    //Método para validar o email e senha de login do usuário
    public boolean validateLogin(String email, String passwordHash) throws SQLException {
        User validated = (User) dao.select(email);
        if(validated != null){
            return passwordHash.equals(validated.getPasswordHash());
        }
        else {
            return false;
        }
    }

    // Método para validar o usuário do e-mail informado
    public Entity validatePassword(String email) throws SQLException {
        User validated = (User) dao.select(email);
        return validated;
    }

    //Método para validar qual o id do usuário
    public User validateId(int id) throws SQLException {
        User validated = (User) dao.selectID(id);
        return validated;
    }

    // Método para validar se a string é um nome completo
    // caso ela não seja retornará um erro, caso contrário devolverá o nome separado por meio de um vetor
    public static String[] validateFullName(String fullName) throws Exception {
        if (!fullName.isBlank()) {
            var name = fullName.split(" ", 2);

            if (name.length < 2) {
                throw new Exception("Nome está incompleto!");
            }

            return name;
        } else {
            throw new Exception("Preencha todos os campos!");
        }
    }

    // Método para validar se a string é um email válido
    // caso ela não seja retornará um erro, caso contrário devolverá true
    public static boolean validateEmail(String email) throws Exception {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return true;
        } else {
            throw new Exception("Email inválido!");
        }
    }

}
