package models.parsers;

import utils.IO.Console;
import models.Country;
import models.HairColor;
import models.EyeColor;
import models.Person;
import utils.exceptions.ValidationException;
import models.builders.PersonBuilder;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.Map;

/**
 * Парсер персоны
 */
public class PersonParser implements Parser<Person>{
    /**
     * Парсер персоны из словаря
     * @param person словарь
     * @return объект Person со значениями из словаря
     */
    public Response<Person> fromMap(Map<String, Object> person){
        PersonBuilder pb = new PersonBuilder();
        Response<Person> response = new Response<>();

        String name;
        String  birthday;
        String eyeColor;
        String hairColor;
        String nationality;
        try{
            name = (String) person.get("name");
            pb.setName(name);
            eyeColor = (String) person.get("eyecolor");
            pb.setEyeColor(eyeColor);
            hairColor = (String) person.get("haircolor");
            pb.setHairColor(hairColor);
            nationality = (String) person.get("nationality");
            pb.setNationality(nationality);
            birthday = (String) person.get("birthday");
            pb.setBirthday(birthday);

            response.setStatus(ResponseStatus.OK);
            response.setMessage(pb.build());
        }catch (Exception e){
            response.setStatus(ResponseStatus.EXCEPTION);
        }
        return response;
    }

    /**
     * Парсер person из консоли
     * @return объект person
     */
    public Response<Person> fromConsole(Console console, boolean isNullAllowed){
        PersonBuilder pb = new PersonBuilder(isNullAllowed);
        Response<Person> response = new Response<>();

        String line;
        ResponseStatus inputStatus;
        Response<String> inputResponse;
        while(true){
            console.print("Введите имя");
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try {
                pb.setName(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        while (true){
            console.print("Введите дату рождения (yyyy-mm-dd)");
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try {
                pb.setBirthday(line);
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
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try{
                pb.setEyeColor(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        console.println("Введите цвет волос (пустая строка если null)");
        HairColor[] hairColors = HairColor.values();
        for(var v: hairColors){
            console.println("\t" + v);
        }
        while (true){
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try{
                pb.setHairColor(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        console.println("Введите национальность (пустая строка если null)");
        Country[] countries = Country.values();
        for(var v: countries){
            console.println("\t" + v);
        }
        while (true){
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try{
                pb.setNationality(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }
        response.setStatus(ResponseStatus.OK);
        response.setMessage(pb.build());
        return response;
    }
}
