package commands;

import utils.IO.Console;
import managers.CollectionManager;
import models.Person;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.HashMap;
import java.util.Set;

/**
 * Группирует фильмы по оператору
 */
public class GroupCountingByOperator implements Command{
    private final CollectionManager collectionManager;
    private String [] args;

    public GroupCountingByOperator(CollectionManager collectionManager){
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
        StringBuilder message = new StringBuilder();
        if(!checkArgs()){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Команда group_counting_by_operator не принимает аргументов");
            return response;
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
            message.append(operator.getName()).append(" ").append(byOperator.get(operator)).append("\n");
        }

        if (!message.isEmpty()){
            message.setLength(message.length() - 1);
        }
        response.setStatus(ResponseStatus.OK);
        response.setMessage(message.toString());
        return response;
    }
}
