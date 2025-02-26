package parsers;
import IO.Console;
import models.Coordinates;

import java.util.Map;

/**
 * Парсер координат
 */
public class CoordinatesParser implements Parser<Coordinates>{
    /**
     * @param coordinates - словарь
     * @return объект Coordinates со значениями из словаря
     */
    public Coordinates fromMap(Map<String, Object> coordinates){
        try{
            Double x = Double.parseDouble(coordinates.get("x").toString());
            Float y = Float.parseFloat(coordinates.get("y").toString());
            return new Coordinates(x, y);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Парсер координат из консоли
     * @param console консоль из которой парсятся данные
     * @return объект координат
     */
    public Coordinates fromConsole(Console console, String[] args){
        double x;
        float y;
        while(true){
            console.print("Введите координату x (>= -306)");
            try{
                x = Double.parseDouble(console.input());
                if(x <= -306){
                    console.println("Значение должно быть больше -306");
                }else{
                    break;
                }
            }catch (NumberFormatException e){
                console.println("Неверный тип данных");
            }
        }

        while(true){
            console.print("Введите координату y");
            try {
                y = Float.parseFloat(console.input());
                break;
            }catch (NumberFormatException e){
                console.println("Неверный тип данных");
            }
        }

        return new Coordinates(x, y);
    }
}
