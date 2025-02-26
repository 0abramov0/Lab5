package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Модель координат
 */
public class Coordinates implements Model{
    private Double x;
    private Float y;

    public Coordinates(Double x, Float y){
        this.x = x;
        this.y = y;
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

    /**
     * Валидатор полей
     * @return true если поля валидны
     */
    public boolean validate(){
        if(x != null && x <= -306){
            return false;
        }
        return y != null;
    }

    @Override
    public String toString(){
        return "{x: " + x + ", y: " + y + "}";
    }
}
