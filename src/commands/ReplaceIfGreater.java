package commands;

import IO.Console;
import managers.CollectionManager;
import models.Movie;
import parsers.MovieParser;

import java.util.HashMap;

/**
 * Меняет элемент, если второй элемент больше него
 */
public class ReplaceIfGreater implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public ReplaceIfGreater(Console console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @param args id элемента, с которым будет проводиться сравнение
     */
    public void execute(String[] args){
        if(args.length != 1){
            console.println("Требуется один аргумент (id)");
            return;
        }
        Long id;
        while (true){
            try{
                id = Long.parseLong(args[0]);
                break;
            }catch (NumberFormatException e){
                console.println("Id должен быть целым числом");
            }
        }

        MovieParser movieParser = new MovieParser();
        Movie movie = movieParser.fromConsole(console, false);
        movie.setId(id);
        Movie targetMovie = collectionManager.getById(id);

        if(targetMovie == null){
            console.println("Элемента с таким id не существует");
        }else{
            if(movie.compareTo(targetMovie) > 0){
                collectionManager.update(movie);
                console.println("Объект изменен");
            }else{
                console.println("Объект не изменен");
            }
        }
    }
}
