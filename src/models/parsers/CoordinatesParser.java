package models.parsers;
import utils.IO.Console;
import models.Coordinates;
import utils.exceptions.ValidationException;
import models.builders.CoordinatesBuilder;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.Map;

/**
 * Парсер координат
 */
public class CoordinatesParser implements Parser<Coordinates>{
    /**
     * @param coordinates словарь
     * @return объект Coordinates со значениями из словаря
     */
    public Response<Coordinates> fromMap(Map<String, Object> coordinates){
        CoordinatesBuilder cb = new CoordinatesBuilder();
        Response<Coordinates> response = new Response<>();
        try{
            Double x = Double.parseDouble(coordinates.get("x").toString());
            Float y = Float.parseFloat(coordinates.get("y").toString());
            cb.setX(x);
            cb.setY(y);
            response.setStatus(ResponseStatus.OK);
            response.setMessage(cb.build());
        }catch (Exception e){
            response.setStatus(ResponseStatus.EXCEPTION);
        }
        return response;
    }

    /**
     * Парсер coordinates из консоли
     *
     * @return объект coordinates
     */
    public Response<Coordinates> fromConsole(Console console, boolean isNullAllowed){
        CoordinatesBuilder cb = new CoordinatesBuilder(isNullAllowed);
        Response<Coordinates> response = new Response<>();

        Double x;
        Float y;
        String line;
        ResponseStatus inputStatus;
        Response<String> inputResponse;
        while(true){
            console.print("Введите координату x (>= -306)");
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try{
                x = console.parseDouble(line);
                cb.setX(x);
                break;
            }catch (NumberFormatException e){
                console.println("Неверный формат ввода");
            } catch (ValidationException e){
                console.println(e.getMessage());
            }
        }
        while(true){
            console.print("Введите координату y");
            inputResponse = console.input();
            line = inputResponse.getMessage();
            inputStatus = inputResponse.getStatus();
            if (inputStatus == ResponseStatus.FATAL_ERROR){
                response.setStatus(ResponseStatus.FATAL_ERROR);
                return response;
            }
            try {
                y = console.parseFloat(line);
                cb.setY(y);
                break;
            }catch (NumberFormatException e){
                console.println("Неверный формат ввода");
            } catch (ValidationException e){
                console.println(e.getMessage());
            }
        }
        response.setStatus(ResponseStatus.OK);
        response.setMessage(cb.build());
        return response;
    }
}
