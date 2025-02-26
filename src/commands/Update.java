package commands;

import IO.Console;
import managers.CollectionManager;
import models.Movie;
import parsers.MovieParser;

import java.util.HashMap;

/**
 * Обновляет значение по заданному ключу
 */
public class Update implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @param args id элемента, который будет обновляться
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
        HashMap<String, String> parserArgs = new HashMap<>();
        parserArgs.put("id", collectionManager.getCurrentId().toString());
        Movie movie = movieParser.fromConsole(console, parserArgs);
        collectionManager.update(movie);
    }
}
