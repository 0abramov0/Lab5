package parsers;

import console.Console;
import models.Country;
import models.EyeColor;
import models.HairColor;
import models.Person;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        String name;
        ZonedDateTime birthday;
        EyeColor eyeColor;
        HairColor hairColor;
        Country nationality;
        try{
            name = (String) person.get("name");
            eyeColor = (person.get("eyecolor") == null ? null : EyeColor.valueOf(((String) person.get("eyecolor")).toUpperCase()));
            hairColor = (person.get("haircolor") == null ? null : HairColor.valueOf(((String) person.get("haircolor")).toUpperCase()));
            nationality = (person.get("nationality") == null ? null : Country.valueOf(((String) person.get("nationality")).toUpperCase()));
            birthday = null;
            if(person.get("birthday") != null){
                try{
                    birthday = ZonedDateTime.parse((String) person.get("birthday"));
                } catch (DateTimeParseException e){
                    birthday = LocalDate.parse((String) person.get("birthday")).atStartOfDay(ZoneOffset.UTC);
                }
            }
            return new Person(name, birthday, eyeColor, hairColor, nationality);
        }catch (ClassCastException | IllegalArgumentException | NullPointerException e){
            return null;
        }
    }

    public Person fromConsole(Console console, String [] args){
        String name;
        ZonedDateTime birthday;
        EyeColor eyeColor;
        HairColor hairColor;
        Country nationality;

        String line;

        while(true){
            console.print("Введите имя");
            line = console.input();
            if (line.isEmpty()){
                console.println("Имя не может быть пустым");
            }else{
                name = line;
                break;
            }
        }

        while (true){
            console.print("Введите дату рождения (yyyy-mm-dd)");
            try{
                line = console.input();
                if(!line.isEmpty()){
                    try{
                        birthday = ZonedDateTime.parse(line, DateTimeFormatter.ISO_DATE_TIME);
                    }catch (DateTimeParseException e){
                        birthday = LocalDate.parse(line).atStartOfDay(ZoneOffset.UTC);
                    }
                }else{
                    birthday = null;
                }
                break;
            }catch (DateTimeParseException e){
                console.println("Неверный формат даты");
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
                if(!line.isEmpty()) {
                    eyeColor = EyeColor.valueOf(line.toUpperCase());
                }
                else{
                    eyeColor = null;
                }
                break;
                }catch (IllegalArgumentException e){
                console.print("Такого аргумента не существует");
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
                if(!line.isEmpty()) {
                    hairColor = HairColor.valueOf(line.toUpperCase());
                }
                else{
                    hairColor = null;
                }
                break;
            }catch (IllegalArgumentException e){
                console.print("Такого аргумента не существует");
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
                if(!line.isEmpty()) {
                    nationality = Country.valueOf(line.toUpperCase());
                }
                else{
                    nationality = null;
                }
                break;
            }catch (IllegalArgumentException e){
                console.print("Такого аргумента не существует");
            }
        }

        return new Person(name, birthday, eyeColor, hairColor, nationality);
    }
}
