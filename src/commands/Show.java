package commands;

import utils.IO.Console;
import managers.CollectionManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.Set;

/**
 * Показывает содержимое коллекции
 */
public class Show implements Command{
    private final CollectionManager collectionManager;
    private String[] args;

    public Show(CollectionManager collectionManager){
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
            response.setMessage("Команда не принимает аргументов");
            return response;
        }

        Set<Long> movieIds = collectionManager.getKeys();
        response.setStatus(ResponseStatus.OK);
        for(long id: movieIds){
            message.append(collectionManager.getById(id)).append("\n");
        }

        if (!message.isEmpty()){
            message.setLength(message.length() - 1);
        }
        response.setMessage(message.toString());
        return response;
    }
}
