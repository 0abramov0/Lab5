package commands;

import utils.IO.Console;
import managers.CollectionManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;

/**
 * Очищает коллекцию
 */
public class Clear implements Command{
    private final CollectionManager collectionManager;
    private String [] args;

    public Clear(CollectionManager collectionManager){
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
            response.setMessage("Команда clear не принимает аргументов");
            return response;
        }
        collectionManager.clear();
        response.setStatus(ResponseStatus.OK);
        response.setMessage("Коллекция очищена");
        return response;
    }
}
