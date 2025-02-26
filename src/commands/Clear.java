package commands;

import IO.Console;
import managers.CollectionManager;

/**
 * Очищает коллекцию
 */
public class Clear implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public Clear(Console console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @param args не принимает аргументов
     */
    public void execute(String[] args){
        if(args.length > 0){
            console.println("Команда insert не принимает аргументов");
            return;
        }
        collectionManager.clear();
    }
}
