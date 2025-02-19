package commands;

import console.Console;
import managers.CollectionManager;

import java.util.Set;

/**
 * Показывает содержимое коллекции
 */
public class Show implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public Show(Console console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @param args не принимает аргументов
     */
    public void execute(String[] args){
        if(args.length > 0){
            console.println("Команда не принимает аргументов");
            return;
        }

        Set<Long> movieIds = collectionManager.getKeys();
        for(long id: movieIds){
            console.println(collectionManager.getById(id));
        }
    }
}
