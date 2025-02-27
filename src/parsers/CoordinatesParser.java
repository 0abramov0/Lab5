package parsers;
import IO.Console;
import models.Coordinates;
import utils.ValidationException;

import java.util.HashMap;
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
        Coordinates c = new Coordinates();
        try{
            Double x = Double.parseDouble(coordinates.get("x").toString());
            Float y = Float.parseFloat(coordinates.get("y").toString());
            c.setX(x);
            c.setY(y);
        }catch (Exception e){
            return null;
        }
        return c;
    }

    /**
     * Парсер координат из консоли
     * @return объект координат
     */
    public Coordinates fromConsole(Console console, boolean isNullAllowed){
        Coordinates c = new Coordinates();
        double x;
        float y;
        while(true){
            console.print("Введите координату x (>= -306)");
            try{
                x = Double.parseDouble(console.input());
                c.setX(x);
                break;
            }catch (NumberFormatException e){
                console.println("Неверный формат ввода");
            }catch(NullPointerException e){
                if(!isNullAllowed){
                    console.println("Неверный формат ввода");
                }else{
                    break;
                }
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }
        while(true){
            console.print("Введите координату y");
            try {
                y = Float.parseFloat(console.input());
                c.setY(y);
                break;
            }catch (NumberFormatException e){
                console.println("Неверный формат ввода");
            }catch (NullPointerException e){
                if(!isNullAllowed){
                    console.println("Неверный формат ввода");
                }else{
                    break;
                }
            }catch (ValidationException e){
                console.println(e.getMessage());
            }
        }
        return c;
    }
}
