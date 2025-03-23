package managers;

import models.Movie;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

/**
 * Проводит операции с коллекцией
 */
public class CollectionManager {
    private static long currentId = 1;
    private static TreeMap<Long, Movie> collection = new TreeMap<>();
    private final LocalDate creationDate;
    private final String collectionType;

    public CollectionManager(){
        this.creationDate = LocalDate.now();
        this.collectionType = collection.getClass().getSimpleName();
    }

    /**
     * Возвращает размер коллекции
     * @return размер коллекции
     */
    public long getSize(){
        return collection.size();
    }

    /**
     * Возвращает доступный id
     * @return свободный id
     */
    public Long getCurrentId(){
        while(getById(currentId) != null){currentId++;}
        return currentId;
    }

    /**
     * Возвращает элемент по id
     * @param targetId id элемента
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
        movie.updateId(getCurrentId());
        collection.put(movie.getId(), movie);
    }

    /**
     * Обновление элемента по id
     * @param movie элемент для обновления
     * @param id ключ обновляемого элемента
     * @throws NoSuchElementException если элемента с таким id не существует
     */
    public void update(Long id, Movie movie){
        if(getById(id) == null){
            throw new NoSuchElementException();
        }
        Movie mergedMovie = getById(id);
        mergedMovie.merge(movie);
        collection.put(id, mergedMovie);
    }

    /**
     * Обновление элемента по id если он меньше
     * @param movie элемент для обновления
     * @param id ключ обновляемого элемента
     * @throws NoSuchElementException если элемента с таким id не существует
     * @return true если элемент обновлен
     */
    public boolean replaceIfLower(Long id, Movie movie){
        if(getById(id) == null){
            throw new NoSuchElementException();
        }
        if(movie.compareTo(getById(id)) < 0){
            collection.put(id, movie);
            return true;
        }
        return false;
    }

    /**
     * Обновление элемента по id если он больше
     * @param movie элемент для обновления
     * @param id ключ обновляемого элемента
     * @throws NoSuchElementException если элемента с таким id не существует
     * @return true если элемент обновлен
     */
    public boolean replaceIfGreater(Long id, Movie movie){
        if(getById(id) == null){
            throw new NoSuchElementException();
        }
        if(movie.compareTo(getById(id)) > 0){
            collection.put(id, movie);
            return true;
        }
        return false;
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

    /**
     * Очищает коллекцию
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Возвращает множество ключей коллекции
     * @return ключи
     */
    public Set<Long> getKeys(){
        return collection.keySet();
    }

    /**
     * Возвращает тип коллекции
     * @return тип
     */
    public String getCollectionType(){
        return collectionType;
    }

    /**
     * Возвращает дату создания коллекции
     * @return дата создания
     */
    public LocalDate getCreationDate(){
        return creationDate;
    }
}
