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

    public void merge(Coordinates coordinates){
        Double newX = coordinates.getX();
        Float newY = coordinates.getY();
        if(newX != null){
            setX(newX);
        }
        if(newY != null){
            setY(newY);
        }
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
        Map<String, Object> fields = toMap();
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            sb.append("\t").append(entry.getKey()).append(": ").append(entry.getValue()).append(",\n");
        }
        sb.append("  }");
        return sb.toString();
    }
}
