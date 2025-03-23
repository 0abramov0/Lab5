package commands;

import utils.IO.Console;
import managers.CollectionManager;
import models.Movie;
import models.parsers.MovieParser;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.NoSuchElementException;

/**
 * Обновляет значение по заданному ключу
 */
public class Update implements Command{
    private final Console console;
    private final CollectionManager collectionManager;
    private String[] args;

    public Update(Console console, CollectionManager collectionManager){
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
     * @param args id элемента, который будет обновляться
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
        Response<Movie> movieResponse = movieParser.fromConsole(console, true);
        if (movieResponse.getStatus() == ResponseStatus.FATAL_ERROR){
            response.setStatus(ResponseStatus.FATAL_ERROR);
            return response;
        }

        try {
            collectionManager.update(id, movieResponse.getMessage());
            response.setStatus(ResponseStatus.OK);
            response.setMessage("Объект обновлен");
        }catch (NoSuchElementException e){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Объекта с таким id не существует");
        }
        return response;
    }
}
