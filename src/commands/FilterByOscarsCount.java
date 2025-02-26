package commands;

import IO.Console;
import managers.CollectionManager;
import models.Movie;

import java.util.Set;

/**
 * Выбирает фильмы по количеству Оскаров
 */
public class FilterByOscarsCount implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterByOscarsCount(Console console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @param args количество Оскаров для фильтрации
     */
    public void execute(String[] args){
        if(args.length != 1){
            console.println("Требуется один аргумент (количество Оскаров)");
            return;
        }
        long oscarsCount;
        while (true){
            try{
                oscarsCount = Integer.parseInt(args[0]);
                break;
            }catch (NumberFormatException e){
                console.println("Число Оскаров должен быть целым числом");
            }
        }
        Set<Long> moviesId = collectionManager.getKeys();
        for(long id: moviesId){
            Movie movie = collectionManager.getById(id);
            if(movie.getOscarsCount() == oscarsCount){
                console.println(movie);
            }
        }
    }
}
