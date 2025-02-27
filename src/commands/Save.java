package commands;

import IO.Console;
import managers.CollectionManager;
import parsers.savers.Saver;

import java.io.IOException;
import java.util.*;

/**
 * Сохраняет коллекцию в файл
 */
public class Save implements Command{
    private final Console console;
    private final CollectionManager collectionManager;
    private final Saver saver;

    public Save(Console console, CollectionManager collectionManager, Saver saver){
        this.console = console;
        this.collectionManager = collectionManager;
        this.saver = saver;
    }

    /**
     * Выполняет команду
     * @param args не принимает аргументы
     */
    public void execute(String[] args){
        if(args.length > 0){
            console.println("Команда не принимает аргументов");
            return;
        }
        try{
            HashMap<String, Object> data = new HashMap<>();
            Set<Long> keys = collectionManager.getKeys();
            for(Long key: keys){
                data.put(key.toString(), collectionManager.getById(key));
            }
            saver.save(data);
            console.println("Коллекция успешно сохранена");
        }catch (IOException e){
            console.println("Не удалось сохранить коллекцию в файл");
        }
    }
}
