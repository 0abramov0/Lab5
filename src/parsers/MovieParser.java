package parsers;

import IO.Console;
import models.*;

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
        Long id;
        String name;
        Coordinates coordinates;
        Long oscarsCount;
        Long budget;
        String tagline;
        MovieGenre genre;
        Person operator;

        CoordinatesParser coordinatesParser = new CoordinatesParser();
        PersonParser personParser = new PersonParser();

        try{
            id = Long.parseLong((String) movie.get("id"));
            name = (String) movie.get("name");
            coordinates = coordinatesParser.fromMap((Map<String, Object>)movie.get("coordinates"));
            oscarsCount = Long.parseLong(movie.get("oscarscount").toString());
            budget = Long.parseLong(movie.get("budget").toString());
            tagline = (String) movie.get("tagline");
            genre = MovieGenre.valueOf(movie.get("genre").toString().toUpperCase());
            operator = personParser.fromMap((Map<String, Object>)movie.get("operator"));
            return new Movie(id, name, coordinates, oscarsCount, budget, tagline, genre, operator);
        }catch (Exception e){
            return null;
        }
    }

    public Movie fromConsole(Console console, String [] args){
        long id = Long.parseLong(args[0]);
        String name;
        Coordinates coordinates;
        long oscarsCount;
        long budget;
        String tagline;
        MovieGenre movieGenre;
        Person operator;

        String line;

        while(true){
            console.print("Введите название фильма");
            line = console.input();
            if(!line.isEmpty()){
                name = line;
                break;
            }else{
                console.println("Название не может быть пустым");
            }
        }

        CoordinatesParser coordinatesParser = new CoordinatesParser();
        String [] parserArgs = {};
        coordinates = coordinatesParser.fromConsole(console, parserArgs);

        while (true){
            console.print("Введите количество Оскаров фильма ( > 0)");
            line = console.input();
            try {
                oscarsCount = Long.parseLong(line);
                if (oscarsCount > 0){
                    break;
                }else{
                    console.println("Количество должно быть больше нуля");
                }
            }catch (NumberFormatException e){
                console.println("Неверный формат ввода");
            }
        }

        while (true){
            console.print("Введите бюджет фильма ( > 0)");
            line = console.input();
            try {
                budget = Long.parseLong(line);
                if (budget > 0){
                    break;
                }else{
                    console.println("Бюджет должен быть больше нуля");
                }
            }catch (NumberFormatException e){
                console.println("Неверный формат ввода");
            }
        }

        console.print("Введите слоган фильма (возможно null)");
        line = console.input();
        if(!line.isEmpty()){
            tagline = line;
        }else{
            tagline = null;
        }

        console.println("Введите жанр фильма");
        MovieGenre[] movieGenres = MovieGenre.values();
        for(var v: movieGenres){
            console.println("\t" + v);
        }
        while (true){
            line = console.input();
            try{
                if(!line.isEmpty()) {
                    movieGenre = MovieGenre.valueOf(line.toUpperCase());
                    break;
                }
                else{
                    console.println("Жанр не может быть null");
                }
            }catch (IllegalArgumentException e){
                console.print("Такого аргумента не существует");
            }
        }

        PersonParser personParser = new PersonParser();
        operator = personParser.fromConsole(console, parserArgs);

        return new Movie(id, name, coordinates, oscarsCount, budget, tagline, movieGenre, operator);
    }
}
