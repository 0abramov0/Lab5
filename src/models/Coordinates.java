package models;

import utils.ValidationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Модель координат
 */
public class Coordinates implements Model{
    private Double x;
    private Float y;

    public Coordinates(){}

    public void setX(Double x){
        if(x == null || x <= -306){
            throw new ValidationException("x должен быть больше -306");
        }
        this.x = x;
    }

    public void setY(Float y){
        if(y == null){
            throw new ValidationException("y не может быть null");
        }
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    /**
     * Возвращает поля объекта в виде словаря
     * @return словарь полей объекта
     */
    public Map<String, Object> toMap(){
        HashMap<String, Object> fields = new HashMap<>();
        fields.put("x", x);
        fields.put("y", y);
        return fields;
    }

    @Override
    public String toString(){
        return "{x: " + x + ", y: " + y + "}";
    }
}
