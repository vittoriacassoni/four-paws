package business;

import comuns.access.User;
import dao.access.UserSqlServerDAO;
import dao.bases.DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validates {

    // Método para validar se o campo obrigatório possui algum valor
    // caso ele não possua retornará true, caso contrário false
    public static boolean validateRequiredField(String field) {
        return field.isBlank();
    }

    // Método para validar se a string de data é uma data válida
    // caso ela não seja retornará um erro, caso contrário retornará a data convertida para Date
    public static Date validateDate(String date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateConverted = null;

        dateFormat.setLenient(false);
        try {
            dateConverted = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new Exception("Data inválida!");
        }
        return dateConverted;
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

    // Método para validar se o valor é um double
    // caso ele não seja retornará um erro, caso contrário devolverá true
    public static Double validateDoubleNumber(String number) throws Exception {
        try {
            Double result = Double.parseDouble(number);

            return  result;
        } catch(NumberFormatException ex){
           throw new Exception("Número inválido!");
        }
    }

}
