import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        JsonDeserializer<LocalDate> deserializer = (jsonElement, type, jsonDeserializationContext)
                -> LocalDate.parse(jsonElement.getAsString());
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, deserializer).create();
        String json = new String(new FileInputStream("test.json").readAllBytes());
        List<Company> companies = gson.fromJson(json, new TypeToken<List<Company>>(){}.getType());


        System.out.println("\n\tВсе имеющиеся компании\nОрганизация\t\t\t\tДата основания\n");
        companies.stream()
                .map(company -> company.getNameShort() + "\t-\t" + company.getEgrulDate())
                .forEach(System.out::println);


        System.out.println("\n\t\t\tЦенные бумаги, просроченные на текущий день" +
                "\n\tКод\t\t\tДата истечения\t\t\tПолное название организации-владельца\n");
        List<Security> securities = new ArrayList<>();
        companies.stream()
                .map(Company::getSecurities)
                .forEach(securities::addAll);
        securities.stream()
                .filter(security -> security.getDateTo().isBefore(LocalDate.now()))
                .map(security -> security.getCode() + "\t" + security.getDateTo() + "\t\t\t" + security.getNameFull())
                .forEach(System.out::println);


        String date = "01/01/2005";     //Запрос пользователя в виде даты, варьировать можно здесь.

        List<String> patterns = Arrays.asList("dd.MM.yyyy", "dd.MM.yy", "dd/MM/yyyy", "dd/MM/yy");
        final LocalDate[] localDate = new LocalDate[1];
        patterns.forEach(pattern -> {
                    try {
                        localDate[0] = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
                    }
                    catch (DateTimeParseException ignored) {}
                });
        System.out.println("\nОрганизации, основанные после " + localDate[0] +"\n\tНазвание\t\t\tДата создания\n");
        companies.stream()
                .filter(company -> company.getEgrulDate().isAfter(localDate[0]))
                .map(company -> company.getNameShort() + "\t\t" + company.getEgrulDate())
                .forEach(System.out::println);


        String currency = "RUB";        //Запрос пользователя в виде кода валюты, варьировать можно здесь.

        System.out.println("\nЦенные бумаги, использующие " + currency +"\nid\t\tКод\n");
        securities.stream()
                .filter(security -> security.getCurrency().getCode().equals(currency))
                .map(security -> security.getCurrency().getId() + "\t\t" + security.getCurrency().getCode())
                .forEach(System.out::println);
    }
}
