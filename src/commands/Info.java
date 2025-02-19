package commands;

import console.Console;
import managers.CollectionManager;

import java.time.LocalDate;

/**
 * Возвращает информацию о коллекции
 */
public class Info implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public Info(Console console, CollectionManager collectionManager){
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
        String collectionType = collectionManager.getCollectionType();
        LocalDate creationDate = collectionManager.getCreationDate();
        int collectionSize = collectionManager.getKeys().size();
        console.println("Collection type: " + collectionType + ", creation date: " + creationDate + ", size: " + collectionSize);
    }
}
