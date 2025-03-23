package models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Модель фильма
 */
public class Movie implements Mapable, Mergeable<Movie>, Comparable<Movie>{
    private Long id;
    private String name;
    private Coordinates coordinates;
    private final LocalDate creationDate;
    private Long oscarsCount;
    private Long budget;
    private String tagline;
    private MovieGenre genre;
    private Person operator;

    public Movie(Long id, String name, Coordinates coordinates, Long oscarsCount, Long budget, String tagline, MovieGenre genre, Person operator){
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

    public void updateId(Long id){
        this.id = id;
    }

    /**
     * Геттер id
     * @return id
     */
    public Long getId(){
        return id;
    }

    /**
     * Геттер name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер координат
     * @return coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Геттер creationDate
     * @return creationDate
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Геттер genre
     * @return genre
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * Геттер tagline
     * @return tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * Геттер budget
     * @return budget
     */
    public Long getBudget(){
        return budget;
    }

    /**
     * Геттер operator
     * @return operator
     */
    public Person getOperator(){
        return operator;
    }

    /**
     * Геттер oscarsCount
     * @return oscarsCount
     */
    public Long getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Устанавливает объекту поле из movie если оно не null
     * @param movie объединенные координаты
     */
    public void merge(Movie movie){
        String newName = movie.getName();
        Coordinates newCoordinates = movie.getCoordinates();
        Long newOscarsCount = movie.getOscarsCount();
        Long newBudget = movie.getBudget();
        String newTagline = movie.getTagline();
        MovieGenre newGenre = movie.getGenre();
        Person newOperator = movie.getOperator();

        if(newName != null){
            this.name = newName;
        }
        if(newCoordinates != null){
            coordinates.merge(newCoordinates);
        }
        if(newOscarsCount != null){
            this.oscarsCount = newOscarsCount;
        }
        if (newBudget != null){
            this.budget = newBudget;
        }
        if(newTagline != null){
            this.tagline = newTagline;
        }
        if(newGenre != null){
            this.genre = newGenre;
        }
        if(newOperator != null){
            operator.merge(newOperator);
        }
    }

    /**
     * Возвращает поля объекта в виде словаря
     * @return словарь полей объекта
     */
    public Map<String, Object> toMap(){
        HashMap<String, Object> fields = new HashMap<>();
        fields.put("id", id);
        fields.put("name", name);
        fields.put("coordinates", coordinates);
        fields.put("creationDate", creationDate.toString());
        fields.put("oscarsCount", oscarsCount);
        fields.put("budget", budget);
        fields.put("tagline", tagline);
        fields.put("genre", genre == null ? null : genre.toString());
        fields.put("operator", operator);
        return fields;
    }

    @Override
    public String toString(){
        Map<String, Object> fields = toMap();
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"id\": ").append(fields.get("id")).append(",\n");
        for (var k: fields.keySet()) {
            var value = fields.get(k);
            if (!k.equals("id")){
                sb.append("  ").append('"').append(k).append('"').append(": ");
                if(value instanceof String){
                    sb.append('"').append(value).append('"');
                }else{
                    sb.append(value);
                }
                sb.append(",\n");
            }
        }
        sb.setLength(sb.length() - 2);
        sb.append("\n}");
        return sb.toString();
    }

    @Override
    public int compareTo(Movie m) {
        if(this.oscarsCount.equals(m.oscarsCount)) {
            if(this.budget.equals(m.budget)){
                return 0;
            }
            return this.budget > m.budget ? 1 : -1;
        }
        return this.oscarsCount > m.oscarsCount ? 1 : -1;
    }
}
