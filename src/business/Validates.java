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
