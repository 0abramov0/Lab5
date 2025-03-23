package commands;

import utils.IO.Console;
import managers.CollectionManager;
import models.Movie;
import models.parsers.MovieParser;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.NoSuchElementException;

/**
 * Меняет элемент, если второй элемент больше него
 */
public class ReplaceIfGreater implements Command{
    private final Console console;
    private final CollectionManager collectionManager;
    private String[] args;

    public ReplaceIfGreater(Console console, CollectionManager collectionManager){
        this.console = console;
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
     * @param args id элемента, с которым будет проводиться сравнение
     */
    public Response<String> execute(String[] args){
        this.args = args;
        Response<String> response = new Response<>();
        if(!checkArgs()){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Требуется один аргумент (id)");
            return response;
        }

        Long id;
        try{
            id = Long.parseLong(args[0]);
        }catch (NumberFormatException e){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Id должен быть целым числом");
            return response;
        }

        MovieParser movieParser = new MovieParser();
        Response<Movie> movieResponse = movieParser.fromConsole(console, false);
        if(movieResponse.getStatus() == ResponseStatus.FATAL_ERROR){
            response.setStatus(ResponseStatus.FATAL_ERROR);
            return response;
        }

        try {
            boolean isUpdate = collectionManager.replaceIfGreater(id, movieResponse.getMessage());
            response.setStatus(ResponseStatus.OK);
            if(isUpdate){
                response.setMessage("Объект изменен");
            }else{
                response.setMessage("Объект не изменен");
            }
        }catch (NoSuchElementException e){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Элемента с таким id не существует");
        }
        return response;
    }
}
