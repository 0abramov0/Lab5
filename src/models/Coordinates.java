package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Модель координат
 */
public class Coordinates implements Mapable, Mergeable<Coordinates> {
    private Double x;
    private Float y;

    public Coordinates(Double x, Float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Геттер x
     * @return x
     */
    public Double getX() {
        return x;
    }

    /**
     * Геттер Y
     * @return y
     */
    public Float getY() {
        return y;
    }

    /**
     * Устанавливает объекту поле из coordinates если оно не null
     * @param coordinates объединенные координаты
     */
    public void merge(Coordinates coordinates){
        Double newX = coordinates.getX();
        Float newY = coordinates.getY();
        if(newX != null){
            this.x = newX;
        }
        if(newY != null){
            this.y = newY;
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
        for (var k : fields.keySet()) {
            sb.append("\t");
            sb.append('"').append(k).append('"');
            sb.append(": ").append(fields.get(k)).append(",\n");
        }
        sb.setLength(sb.length() - 2);
        sb.append("\n  }");
        return sb.toString();
    }
}
