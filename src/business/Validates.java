package business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validates {

    // Método para validar se o campo obrigatório possui algum valor
    // caso ele não possua retornará true, caso contrário false
    public static boolean validateRequiredField(String field) {
        return field.isBlank();
    }

    // Método para validar se o valor é um double
    // caso ele não seja retornará um erro, caso contrário devolverá true
    public static boolean validateDoubleNumber(String number) throws Exception {
        try {
            Double result = Double.parseDouble(number);
            return true;
        } catch(NumberFormatException ex){
           throw new Exception("Número inválido!");
        }
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
}
