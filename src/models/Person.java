package models;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Модель персоны
 */
public class Person implements Model{
    private String name;
    private ZonedDateTime birthday;
    private EyeColor eyeColor;
    private HairColor hairColor;
    private Country nationality;

    public Person(String name, ZonedDateTime birthday, EyeColor eyeColor, HairColor hairColor, Country nationality){
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public Person(){
        this.name = null;
        this.birthday = null;
        this.eyeColor = null;
        this.hairColor = null;
        this.nationality = null;
    }

    public String getName(){
        return name;
    }

    /**
     * Валидатор полей
     * @return true если поля валидны
     */
    public boolean validate(){
        return name != null;
    }

    /**
     * Возвращает поля объекта в виде словаря
     * @return словарь полей объекта
     */
    public Map<String, Object> toMap(){
        HashMap<String, Object> fields = new HashMap<>();
        fields.put("name", name);
        fields.put("birthday", birthday);
        fields.put("eyeColor", eyeColor);
        fields.put("hairColor", hairColor);
        fields.put("nationality", nationality);
        return fields;
    }

    @Override
    public String toString(){
        return "{name: " + name + ", " +
                "birthday: " + (birthday == null ? null : birthday.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) + ", " +
                "eyecolor: " + eyeColor +
                ", hairColor: " + hairColor +
                ", nationality: " + nationality + "}";
    }
}
