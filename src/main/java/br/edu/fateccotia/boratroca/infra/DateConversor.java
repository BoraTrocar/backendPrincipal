package br.edu.fateccotia.boratroca.infra;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DateConversor {
    private static DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formatoAmericano = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDate converterParaFormatoAmericano (String data) {
        return LocalDate.parse(data, formatoAmericano);
    }

    //Não retornamos data em qualquer momento, por isso essa conversão ainda não é necessária... Mas já está criada
    public LocalDate converterParaFormatoBrasileiro (Date data) {
        return LocalDate.parse(data.toString(), formatoBrasileiro);
    }
}
