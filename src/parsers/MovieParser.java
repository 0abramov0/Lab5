package parsers;

import IO.Console;
import models.*;
import utils.ValidationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Парсер фильма
 */
public class MovieParser implements Parser<Movie>{
    /**
     * Парсер Movie из Map
     * @param movie - словарь
     * @return объект Coordinates со значениями из словаря
     */
    public Movie fromMap(Map<String, Object> movie){
        Movie m = new Movie();
        String name;
        Coordinates coordinates;
        Long oscarsCount;
        Long budget;
        String tagline;
        String genre;
        Person operator;

        CoordinatesParser coordinatesParser = new CoordinatesParser();
        PersonParser personParser = new PersonParser();

        try{
            name = (String) movie.get("name");
            m.setName(name);
            coordinates = coordinatesParser.fromMap((Map<String, Object>)movie.get("coordinates"));
            m.setCoordinates(coordinates);
            oscarsCount = Long.parseLong(movie.get("oscarscount").toString());
            m.setOscarsCount(oscarsCount);
            budget = Long.parseLong(movie.get("budget").toString());
            m.setBudget(budget);
            tagline = (String) movie.get("tagline");
            m.setTagline(tagline);
            genre = (String) movie.get("genre");
            m.setGenre(genre);
            operator = personParser.fromMap((Map<String, Object>)movie.get("operator"));
            m.setOperator(operator);
            return m;
        }catch (Exception e){
            return null;
        }
    }

    public Movie fromConsole(Console console, boolean isNullAllowed){
        Movie m = new Movie();

        Coordinates coordinates;
        Long oscarsCount;
        Long budget;
        Person operator;

        String line;
        while(true){
            console.print("Введите название фильма");
            try {
                m.setName(console.input());
                break;
            }catch (ValidationException e){
                if(isNullAllowed){
                    break;
                }
                console.println(e.getMessage());
            }
        }

        CoordinatesParser coordinatesParser = new CoordinatesParser();
        coordinates = coordinatesParser.fromConsole(console, isNullAllowed);
        m.setCoordinates(coordinates);

        while (true){
            console.print("Введите количество Оскаров фильма ( > 0)");
            line = console.input();
            try {
                oscarsCount = Long.parseLong(line);
                m.setOscarsCount(oscarsCount);
                break;
            }catch (NumberFormatException e){
                if(isNullAllowed && line == null){
                    break;
                }
                console.println("Неверный формат ввода");
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        while (true){
            console.print("Введите бюджет фильма ( > 0)");
            line = console.input();
            try {
                budget = Long.parseLong(line);
                m.setBudget(budget);
                break;
            }catch (NumberFormatException e){
                if(isNullAllowed && line == null){
                    break;
                }
                console.println("Неверный формат ввода");
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }

        while (true){
            console.print("Введите слоган фильма (возможно null)");
            line = console.input();
            try {
                m.setTagline(line);
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
            line = console.input();
            try{
                m.setGenre(line);
                break;
            }catch (ValidationException e){
                if(isNullAllowed){
                    break;
                }
                console.println(e.getMessage());
            }
        }
        PersonParser personParser = new PersonParser();
        operator = personParser.fromConsole(console, isNullAllowed);
        m.setOperator(operator);

        return m;
    }
}
