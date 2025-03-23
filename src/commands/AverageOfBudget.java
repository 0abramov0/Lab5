package commands;

import utils.IO.Console;
import managers.CollectionManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.Set;

/**
 * Возвращает среднее значение бюджетов
 */
public class AverageOfBudget implements Command{
    private final CollectionManager collectionManager;
    private String [] args;

    public AverageOfBudget(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * Проверка аргументов команды
     * @return если аргументы валидны
     */
    public boolean checkArgs(){
        return args.length == 0;
    }

    /**
     * Выполняет команду
     * @param args не принимает аргументов
     */
    public Response<String> execute(String[] args){
        this.args = args;
        Response<String> response = new Response<>();
        if(!checkArgs()){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Команда average_of_budget не принимает аргументов");
            return response;
        }
        Long sumOfBudgets = 0L;
        for(long id: collectionManager.getKeys()){
            sumOfBudgets += collectionManager.getById(id).getBudget();
        }

        response.setStatus(ResponseStatus.OK);
        if (collectionManager.getSize() == 0){
            response.setMessage("Средний бюджет: 0");
        }else {
            response.setMessage("Средний бюджет: " + sumOfBudgets / collectionManager.getSize());
        }
        return response;
    }
}
