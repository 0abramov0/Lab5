package parsers;

import IO.Console;
import models.Country;
import models.HairColor;
import models.EyeColor;
import models.Person;
import utils.ValidationException;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Парсер персоны
 */
public class PersonParser implements Parser<Person>{
    /**
     * Парсер персоны из словаря
     * @param person - словарь
     * @return объект Person со значениями из словаря
     */
    public Person fromMap(Map<String, Object> person){
        Person p = new Person();

        String name;
        String  birthday;
        String eyeColor;
        String hairColor;
        String nationality;
        try{
            name = (String) person.get("name");
            p.setName(name);
            eyeColor = (String) person.get("eyecolor");
            p.setEyeColor(eyeColor);
            hairColor = (String) person.get("haircolor");
            p.setHairColor(hairColor);
            nationality = (String) person.get("nationality");
            p.setNationality(nationality);
            birthday = (String) person.get("birthday");
            p.setBirthday(birthday);
        }catch (Exception e){
            return null;
        }
        return p;
    }

    public Person fromConsole(Console console, HashMap<String, String> args){
        Person p = new Person();
        String line;
        while(true){
            console.print("Введите имя");
            line = console.input();
            try {
                p.setName(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        while (true){
            console.print("Введите дату рождения (yyyy-mm-dd)");
            line = console.input();
            try {
                p.setBirthday(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        console.println("Введите цвет глаз (пустая строка если null)");
        EyeColor[] eyeColors = EyeColor.values();
        for(var v: eyeColors){
            console.println("\t" + v);
        }
        while (true){
            line = console.input();
            try{
                p.setEyeColor(line);
                break;
            }catch (ValidationException e){
                console.print(e.getMessage());
            }
        }

        console.println("Введите цвет волос (пустая строка если null)");
        HairColor[] hairColors = HairColor.values();
        for(var v: hairColors){
            console.println("\t" + v);
        }
        while (true){
            line = console.input();
            try{
                p.setHairColor(line);
                break;
            }catch (ValidationException e){
                console.print(e.getMessage());
            }
        }

        console.println("Введите национальность (пустая строка если null)");
        Country[] countries = Country.values();
        for(var v: countries){
            console.println("\t" + v);
        }
        while (true){
            line = console.input();
            try{
                p.setNationality(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }
        return p;
    }
}
