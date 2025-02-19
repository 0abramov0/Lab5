package commands;

import console.Console;
import managers.CollectionManager;

import java.util.Set;

/**
 * Возвращает среднее значение бюджетов
 */
public class AverageOfBudget implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public AverageOfBudget(Console console, CollectionManager collectionManager){
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
        double sumOfBudgets = 0D;
        for(long id: moviesId){
            sumOfBudgets += (double) (collectionManager.getById(id).getBudget());
        }
        if (moviesId.isEmpty()){
            console.println("Средний бюджет 0");
        }else {
            console.println("Средний бюджет " + sumOfBudgets / moviesId.size());
        }
    }
}
