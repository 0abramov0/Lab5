package commands;

import console.Console;
import managers.CollectionManager;
import models.Person;

import java.util.HashMap;
import java.util.Set;

/**
 * Группирует фильмы по оператору
 */
public class GroupCountingByOperator implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public GroupCountingByOperator(Console console, CollectionManager collectionManager){
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

        Set<Long> moviesId = collectionManager.getKeys();
        HashMap<Person, Long> byOperator = new HashMap<>();
        for(long id: moviesId){
            Person operator = collectionManager.getById(id).getOperator();
            if(!byOperator.containsKey(operator)){
                byOperator.put(operator, 0L);
            }
            byOperator.put(operator, byOperator.get(operator) + 1);
        }
        for(Person operator: byOperator.keySet()){
            console.println(operator.getName() + " " + byOperator.get(operator));
        }
    }
}
