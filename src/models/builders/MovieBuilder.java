package models.builders;

import models.Coordinates;
import models.Movie;
import models.MovieGenre;
import models.Person;
import utils.exceptions.ValidationException;

public class MovieBuilder implements Builder<Movie>{
    private Long id;
    private String name;
    private Coordinates coordinates;
    private Long oscarsCount;
    private Long budget;
    private String tagline;
    private MovieGenre genre;
    private Person operator;

    private boolean isNullAllowed;

    public MovieBuilder(){}

    public MovieBuilder(boolean isNullAllowed){
        this.isNullAllowed = isNullAllowed;
    }

    public Movie build(){
        return new Movie(id, name, coordinates, oscarsCount, budget, tagline, genre, operator);
    }

    /**
     * Сеттер id, ValidationException если id не валидный
     * @param id
     */
    public void setId(Long id){
        if(id == null || id <= 0){
            throw new ValidationException("Недопустимый id");
        }
        this.id = id;
    }

    /**
     * Сеттер name, ValidationException если name не валидный
     * @param name
     */
    public void setName(String name) {
        if(isNullAllowed && name == null){
            return;
        }
        if(name == null){
            throw new ValidationException("Имя не может быть null");
        }
        this.name = name;
    }

    /**
     * Сеттер coordinates
     * @param coordinates
     * @throws ValidationException если coordinates валидный
     */
    public void setCoordinates(Coordinates coordinates) {
        if(isNullAllowed && coordinates == null){
            return;
        }
        if (coordinates == null){
            throw new ValidationException("Координаты не могут быть null");
        }
        this.coordinates = coordinates;
    }

    /**
     * Сеттер oscarsCount
     * @param oscarsCount
     * @throws ValidationException если oscarsCount не валидный
     */
    public void setOscarsCount(Long oscarsCount) {
        if(isNullAllowed && oscarsCount == null){
            return;
        }
        if (oscarsCount != null && oscarsCount <= 0){
            throw new ValidationException("Количество Оскаров не может быть отрицательным");
        }
        this.oscarsCount = oscarsCount;
    }

    /**
     * Сеттер budget
     * @param budget
     * @throws ValidationException если budget не валидный
     */
    public void setBudget(Long budget) {
        if(isNullAllowed && budget == null){
            return;
        }
        if (budget != null && budget <= 0){
            throw new ValidationException("Бюджет не может быть отрицательным");
        }
        this.budget = budget;
    }

    /**
     * Сеттер tagline
     * @param tagline
     */
    public void setTagline(String tagline) {
        if(isNullAllowed && tagline == null){
            return;
        }
        this.tagline = tagline;
    }

    /**
     * Сеттер genre
     * @param line строковое представление genre
     * @throws ValidationException если line не валидный
     */
    public void setGenre(String line) {
        if(isNullAllowed && line == null){
            return;
        }
        MovieGenre genre;
        if(line != null){
            try {
                genre = MovieGenre.valueOf(line.toUpperCase());
            }catch (IllegalArgumentException e){
                throw new ValidationException("Недопустимый жанр");
            }
            this.genre = genre;
        }else{
            throw new ValidationException("Жанр не может быть null");
        }
    }

    /**
     * Сеттер operator
     * @param operator
     * @throws ValidationException если operator не валидный
     */
    public void setOperator(Person operator) {
        if(isNullAllowed && operator == null){
            return;
        }
        if(operator == null){
            throw new ValidationException("Оператор не может быть null");
        }
        this.operator = operator;
    }
}
