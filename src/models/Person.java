package models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Модель персоны
 */
public class Person implements Model{
    private String name;
    private ZonedDateTime birthday;
    private HairColor hairColor;
    private EyeColor eyeColor;
    private Country nationality;

    public Person(String name, ZonedDateTime birthday, HairColor eyeColor, EyeColor hairColor, Country nationality){
        this.name = name;
        this.birthday = birthday;
        this.hairColor = eyeColor;
        this.eyeColor = hairColor;
        this.nationality = nationality;
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
        fields.put("eyeColor", hairColor);
        fields.put("hairColor", eyeColor);
        fields.put("nationality", nationality);
        return fields;
    }

    @Override
    public String toString(){
        return "{name: " + name + ", " +
                "birthday: " + (birthday == null ? null : birthday.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) + ", " +
                "eyecolor: " + hairColor +
                ", hairColor: " + eyeColor +
                ", nationality: " + nationality + "}";
    }
}
