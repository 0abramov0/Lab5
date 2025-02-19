package models;

import parsers.CoordinatesParser;
import parsers.PersonParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Модель фильма
 */
public class Movie implements Model, Comparable<Movie>{
    private final Long id;
    private String name;
    private Coordinates coordinates;
    private final LocalDate creationDate;
    private Long oscarsCount;
    private Long budget;
    private String tagline;
    private MovieGenre genre;
    private Person operator;

    public Movie(long id, String name, Coordinates coordinates, long oscarsCount, long budget, String tagline, MovieGenre genre, Person operator){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.oscarsCount = oscarsCount;
        this.budget = budget;
        this.tagline = tagline;
        this.genre = genre;
        this.operator = operator;
    }

    public Movie(){
        this.id = null;
        this.name = null;
        this.coordinates = null;
        this.creationDate = null;
        this.oscarsCount = null;
        this.budget = null;
        this.tagline = null;
        this.genre = null;
        this.operator = null;
    }

    public long getId(){
        return id;
    }

    public long getBudget(){
        return budget;
    }

    public Person getOperator(){
        return operator;
    }

    public long getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Валидатор полей
     * @return true если поля валидны
     */
    public boolean validate(){
        if (name == null) {
            return false;
        }else if(coordinates == null || !coordinates.validate()){
            return false;
        }else if(oscarsCount <= 0){
            return false;
        }else if(budget <= 0){
            return false;
        }else if(operator == null || !operator.validate()){
            return false;
        }
        return true;
    }

    /**
     * Возвращает поля объекта в виде словаря
     * @return словарь полей объекта
     */
    public Map<String, Object> toMap(){
        HashMap<String, Object> fields = new HashMap<>();
        fields.put("name", name);
        fields.put("coordinates", coordinates.toMap());
        fields.put("creationDate", creationDate);
        fields.put("oscarsCount", oscarsCount);
        fields.put("budget", budget);
        fields.put("tagline", tagline);
        fields.put("genre", genre);
        fields.put("operator", operator.toMap());
        return fields;
    }

    @Override
    public String toString(){
        return "{id:" + id +
                ", name: " + name +
                ", coordinates: " + coordinates +
                ", creationDate: " + creationDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                ", oscarsCount: " + oscarsCount +
                ", budget: " + budget +
                ", tagline: " + tagline +
                ", genre: " + genre +
                ", operator: " + operator + "}";
    }

    @Override
    public int compareTo(Movie m) {
        if(this.oscarsCount > m.oscarsCount) {
            return 1;
        }
        return this.oscarsCount.equals(m.oscarsCount) ? 0 : -1;
    }
}
