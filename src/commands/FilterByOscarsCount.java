package commands;

import utils.IO.Console;
import managers.CollectionManager;
import models.Movie;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import javax.xml.transform.OutputKeys;
import java.util.Set;

/**
 * Выбирает фильмы по количеству Оскаров
 */
public class FilterByOscarsCount implements Command{
    private final CollectionManager collectionManager;
    private String [] args;

    public FilterByOscarsCount(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * Проверка аргументов команды
     * @return если аргументы валидны
     */
    public boolean checkArgs(){
        return args.length == 1;
    }

    /**
     * Выполняет команду
     * @param args количество Оскаров для фильтрации
     */
    public Response<String> execute(String[] args){
        this.args = args;
        Response<String> response = new Response<>();
        StringBuilder message = new StringBuilder();
        if(checkArgs()){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Требуется один аргумент (количество Оскаров)");
            return response;
        }

        long oscarsCount;
        try{
            oscarsCount = Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Требуется целое число");
            return response;
        }
        Set<Long> moviesId = collectionManager.getKeys();
        for(long id: moviesId){
            Movie movie = collectionManager.getById(id);
            if(movie.getOscarsCount() == oscarsCount){
                message.append(movie);
            }
        }
        response.setStatus(ResponseStatus.OK);
        response.setMessage(message.toString());
        return response;
    }
}
