package models.builders;

import models.Country;
import models.EyeColor;
import models.HairColor;
import models.Person;
import utils.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PersonBuilder implements Builder<Person>{
    private String name;
    private ZonedDateTime birthday;
    private HairColor hairColor;
    private EyeColor eyeColor;
    private Country nationality;

    private boolean isNullAllowed;

    public PersonBuilder(){}

    public PersonBuilder(boolean isNullAllowed){
        this.isNullAllowed = isNullAllowed;
    }

    public Person build(){
        return new Person(name, birthday, hairColor, eyeColor, nationality);
    }

    /**
     * Сеттер name
     * @param name имя
     * @throws ValidationException если name не валидный
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
     * Сеттер birthday
     * @param line строковое представление даты
     * @throws ValidationException если line не валидный
     */
    public void setBirthday(String line) {
        if(isNullAllowed && line == null){
            return;
        }
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

    /**
     * Сеттер nationality
     * @param line строковое представление даты
     * @throws ValidationException если line не валидный
     */
    public void setNationality(String line) {
        if(isNullAllowed && line == null){
            return;
        }
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

    /**
     * Сеттер hairColor
     * @param line строковое представление даты
     * @throws ValidationException если line не валидный
     */
    public void setHairColor(String line) {
        if(isNullAllowed && line == null){
            return;
        }
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

    /**
     * Сеттер eyeColor
     * @param line строковое представление даты
     * @throws ValidationException если line не валидный
     */
    public void setEyeColor(String line) {
        if(isNullAllowed && line == null){
            return;
        }
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
}
