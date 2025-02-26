package models;

import utils.ValidationException;

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
    private HairColor hairColor;
    private EyeColor eyeColor;
    private Country nationality;

    public Person(){}

    public void setName(String name) {
        if(name == null){
            throw new ValidationException("Имя не может быть null");
        }
        this.name = name;
    }

    public void setBirthday(String line) {
        ZonedDateTime birthday;
        if(line != null){
            try{
                birthday = ZonedDateTime.parse(line, DateTimeFormatter.ISO_DATE_TIME);
            }catch (DateTimeParseException e){
                try{
                    birthday = LocalDate.parse(line).atStartOfDay(ZoneOffset.UTC);
                }catch (DateTimeParseException err){
                    throw new ValidationException("Неверный формат даты");
                }
            }
            this.birthday = birthday;
        }else{
            this.birthday = null;
        }
    }

    public void setNationality(String line) {
        Country nationality;
        if(line != null){
            try {
                nationality = Country.valueOf(line.toUpperCase());
            }catch (IllegalArgumentException e){
                throw new ValidationException("Недопустимая национальность");
            }
            this.nationality = nationality;
        }else{
            this.nationality = null;
        }
    }

    public void setHairColor(String line) {
        HairColor hairColor;
        if(line != null){
            try {
                hairColor = HairColor.valueOf(line.toUpperCase());
            }catch (IllegalArgumentException e){
                throw new ValidationException("Недопустимый цвет волос");
            }
            this.hairColor = hairColor;
        }else{
            this.hairColor = null;
        }
    }

    public void setEyeColor(String line) {
        EyeColor eyeColor;
        if(line != null){
            try {
                eyeColor = EyeColor.valueOf(line.toUpperCase());
            }catch (IllegalArgumentException e){
                throw new ValidationException("Недопустимый цвет глаз");
            }
            this.eyeColor = eyeColor;
        }else{
            this.eyeColor = null;
        }
    }

    public String getName(){
        return name;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public HairColor getHairColor(){
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
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
