package commands;

import utils.IO.Console;
import managers.CollectionManager;
import models.Movie;
import models.parsers.MovieParser;
import utils.responses.Response;
import utils.responses.ResponseStatus;

/**
 * Добавляет элемент в коллекцию
 */
public class Insert implements Command{
    private final Console console;
    private final CollectionManager collectionManager;
    private String[] args;

    public Insert(Console console, CollectionManager collectionManager){
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
     * @param args аргументы команды
     */
    public Response<String> execute(String[] args){
        this.args = args;
        Response<String> response = new Response<>();
        if(!checkArgs()){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Команда insert принимает ожин аргумент (id)");
            return response;
        }
        MovieParser movieParser = new MovieParser();
        Response<Movie> movieResponse = movieParser.fromConsole(console, false);
        if(movieResponse.getStatus() != ResponseStatus.FATAL_ERROR){
            collectionManager.insert(movieResponse.getMessage());
            response.setStatus(ResponseStatus.OK);
            response.setMessage("Объект успешно добавлен");
        }else{
            response.setStatus(ResponseStatus.FATAL_ERROR);
        }
        return response;
    }
}
