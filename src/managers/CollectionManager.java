package managers;

import models.Movie;
import parsers.MovieParser;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Проводит операции с коллекцией
 */
public class CollectionManager {
    private static long currentId = 0;
    private static TreeMap<Long, Movie> collection = new TreeMap<>();
    private final LocalDate creationDate;
    private final String collectionType;

    public CollectionManager(){
        this.creationDate = LocalDate.now();
        this.collectionType = "TreeMap";
    }

    /**
     * Возвращает доступный id
     * @return свободный id
     */
    public Long getCurrentId(){
        currentId++;
        return currentId;
    }

    /**
     * Возвращает элемент по id
     * @param targetId - id элемента
     * @return элемент с данным id
     */
    public Movie getById(Long targetId){
        return collection.get(targetId);
    }

    /**
     * Вставка элемента в коллекцию
     * @param movie фильм
     */
    public void insert(Movie movie){
        movie.setId(getCurrentId());
        collection.put(movie.getId(), movie);
    }

    /**
     * Обновление элемента по id
     * @param movie элемент для обновления
     */
    public void update(Movie movie){
        Movie mergedMovie = getById(movie.getId());
        mergedMovie.merge(movie);
        collection.put(movie.getId(), mergedMovie);
    }

    /**
     * Удаление по id
     * @param id по которому удаляется элемент
     * @return true если удаление произошло
     */
    public boolean removeKey(Long id){
        if(collection.containsKey(id)){
            collection.remove(id);
            return true;
        }
        return false;
    }

    public void clear() {
        collection.clear();
    }

    public Set<Long> getKeys(){
        return collection.keySet();
    }

    public String getCollectionType(){
        return collectionType;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }
}
