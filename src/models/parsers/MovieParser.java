package models.parsers;

import utils.IO.Console;
import models.*;
import utils.exceptions.ValidationException;
import models.builders.MovieBuilder;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.Map;

/**
 * Парсер фильма
 */
public class MovieParser implements Parser<Movie>{
    /**
     * Парсер Movie из Map
     * @param movie словарь
     * @return объект Coordinates со значениями из словаря
     */
    public Response<Movie> fromMap(Map<String, Object> movie){
        MovieBuilder mb = new MovieBuilder();
        Response<Movie> response = new Response<>();

        String name;
        Response<Coordinates> coordinatesResponse;
        Long oscarsCount;
        Long budget;
        String tagline;
        String genre;
        Response<Person> operatorResponse;

        CoordinatesParser coordinatesParser = new CoordinatesParser();
        PersonParser personParser = new PersonParser();

        try{
            name = (String) movie.get("name");
            mb.setName(name);
            oscarsCount = Long.parseLong(movie.get("oscarscount").toString());
            mb.setOscarsCount(oscarsCount);
            budget = Long.parseLong(movie.get("budget").toString());
            mb.setBudget(budget);
            tagline = (String) movie.get("tagline");
            mb.setTagline(tagline);
            genre = (String) movie.get("genre");
            mb.setGenre(genre);

            operatorResponse = personParser.fromMap((Map<String, Object>)movie.get("operator"));
            coordinatesResponse = coordinatesParser.fromMap((Map<String, Object>)movie.get("coordinates"));
            if(coordinatesResponse.getStatus() == ResponseStatus.FATAL_ERROR
                    || operatorResponse.getStatus() == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            mb.setCoordinates(coordinatesResponse.getMessage());
            mb.setOperator(operatorResponse.getMessage());

            response.setStatus(ResponseStatus.OK);
            response.setMessage(mb.build());
        }catch (Exception e){
            response.setStatus(ResponseStatus.EXCEPTION);
        }
        return response;
    }

    /**
     * Парсер movie из консоли
     * @return объект movie
     */
    public Response<Movie> fromConsole(Console console, boolean isNullAllowed){
        MovieBuilder mb = new MovieBuilder(isNullAllowed);
        Response<Movie> response = new Response<>();

        Long oscarsCount;
        Long budget;
        Response<Coordinates> coordinatesResponse;
        Response<Person> operatorResponse;

        String line;
        ResponseStatus inputStatus;
        Response<String> inputResponse;
        while(true){
            console.print("Введите название фильма");
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try {
                mb.setName(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        CoordinatesParser coordinatesParser = new CoordinatesParser();
        coordinatesResponse = coordinatesParser.fromConsole(console, isNullAllowed);
        if(coordinatesResponse.getStatus() == ResponseStatus.FATAL_ERROR){
            response.setStatus(ResponseStatus.FATAL_ERROR);
            return response;
        }
        mb.setCoordinates(coordinatesResponse.getMessage());

        while (true){
            console.print("Введите количество Оскаров фильма ( > 0)");
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try {
                oscarsCount = console.parseLong(line);
                mb.setOscarsCount(oscarsCount);
                break;
            }catch (NumberFormatException e){
                console.println("Неверный формат ввода");
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        while (true){
            console.print("Введите бюджет фильма ( > 0)");
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try {
                budget = console.parseLong(line);
                mb.setBudget(budget);
                break;
            }catch (NumberFormatException e){
                console.println("Неверный формат ввода");
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        while (true){
            console.print("Введите слоган фильма (возможно null)");
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try {
                mb.setTagline(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        console.println("Введите жанр фильма");
        MovieGenre[] movieGenres = MovieGenre.values();
        for(var v: movieGenres){
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
                mb.setGenre(line);
                break;
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }
        PersonParser personParser = new PersonParser();
        operatorResponse = personParser.fromConsole(console, isNullAllowed);
        if (operatorResponse.getStatus() == ResponseStatus.FATAL_ERROR){
            response.setStatus(ResponseStatus.FATAL_ERROR);
            return response;
        }
        mb.setOperator(operatorResponse.getMessage());

        response.setStatus(ResponseStatus.OK);
        response.setMessage(mb.build());
        return response;
    }
}
