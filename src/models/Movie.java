package models;

import utils.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Модель фильма
 */
public class Movie implements Model, Comparable<Movie>{
    private Long id;
    private String name;
    private Coordinates coordinates;
    private final LocalDate creationDate;
    private Long oscarsCount;
    private Long budget;
    private String tagline;
    private MovieGenre genre;
    private Person operator;

    public Movie(){
        creationDate = LocalDate.now();
    }

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public String getTagline() {
        return tagline;
    }

    public Long getBudget(){
        return budget;
    }

    public Person getOperator(){
        return operator;
    }

    public Long getOscarsCount() {
        return oscarsCount;
    }

    public void setId(Long id){
        if(id == null || id <= 0){
            throw new ValidationException("Недопустимый id");
        }
        this.id = id;
    }

    public void setName(String name) {
        if(name == null){
            throw new ValidationException("Имя не может быть null");
        }
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null){
            throw new ValidationException("Координаты не могут быть null");
        }
        this.coordinates = coordinates;
    }

    public void setOscarsCount(Long oscarsCount) {
        if (oscarsCount != null && oscarsCount <= 0){
            throw new ValidationException("Количество Оскаров не может быть отрицательным");
        }
        this.oscarsCount = oscarsCount;
    }

    public void setBudget(Long budget) {
        if (budget != null && budget <= 0){
            throw new ValidationException("Бюджет не может быть отрицательным");
        }
        this.budget = budget;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setGenre(String line) {
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

    public void setOperator(Person operator) {
        if(operator == null){
            throw new ValidationException("Оператор не может быть null");
        }
        this.operator = operator;
    }

    public void merge(Movie movie){
        String newName = movie.getName();
        Coordinates newCoordinates = movie.getCoordinates();
        Long newOscarsCount = movie.getOscarsCount();
        Long newBudget = movie.getBudget();
        String newTagline = movie.getTagline();
        MovieGenre newGenre = movie.getGenre();
        Person newOperator = movie.getOperator();

        if(newName != null){
            setName(movie.getName());
        }
        if(newCoordinates != null){
            coordinates.merge(newCoordinates);
        }
        if(newOscarsCount != null){
            setOscarsCount(movie.getOscarsCount());
        }
        if (newBudget != null){
            setBudget(newBudget);
        }
        if(newTagline != null){
            setTagline(tagline);
        }
        if(newGenre != null){
            setGenre(newGenre.toString());
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
        fields.put("name", name);
        fields.put("coordinates", coordinates);
        fields.put("creationDate", creationDate);
        fields.put("oscarsCount", oscarsCount);
        fields.put("budget", budget);
        fields.put("tagline", tagline);
        fields.put("genre", genre);
        fields.put("operator", operator);
        return fields;
    }

    @Override
    public String toString(){
        Map<String, Object> fields = toMap();
        StringBuilder sb = new StringBuilder();
        sb.append(getId() + ": {\n");
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append(",\n");
        }
        sb.append("}");
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
