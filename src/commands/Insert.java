package commands;

import IO.Console;
import managers.CollectionManager;
import models.Movie;
import parsers.MovieParser;

import java.util.HashMap;

/**
 * Добавляет элемент в коллекцию
 */
public class Insert implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public Insert(Console console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @param args аргументы команды
     */
    public void execute(String[] args){
        if(args.length > 0){
            console.println("Команда insert не принимает аргументов");
            return;
        }
        MovieParser movieParser = new MovieParser();
        Movie movie = movieParser.fromConsole(console, false);
        collectionManager.insert(movie);
        console.println("Объект успешно добавлен");
    }
}
