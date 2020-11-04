package business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validates {

    // Método para validar se o campo obrigatório possui algum valor
    // caso ele não possua retornará true e caso contrário false
    public static boolean validateRequiredField(String field){
        return field.isBlank();
    }

    // Método para validar se a string de data é uma data válida
    // caso ela não seja retornará null caso contrário retornará a data convertida para Date
    public static Date validateDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateConverted = null;

        dateFormat.setLenient(false);
        try {
            dateConverted = dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return dateConverted;
    }
}
