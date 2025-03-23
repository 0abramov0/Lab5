package commands;

import utils.IO.Console;
import managers.CollectionManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.time.LocalDate;

/**
 * Возвращает информацию о коллекции
 */
public class Info implements Command{
    private final CollectionManager collectionManager;
    private String [] args;

    public Info(CollectionManager collectionManager){
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
            response.setMessage("Команда info не принимает аргументов");
            return response;
        }

        String collectionType = collectionManager.getCollectionType();
        LocalDate creationDate = collectionManager.getCreationDate();
        long collectionSize = collectionManager.getSize();
        response.setStatus(ResponseStatus.OK);
        response.setMessage("Collection type: " + collectionType + ", creation date: " + creationDate + ", size: " + collectionSize);
        return response;
    }
}
