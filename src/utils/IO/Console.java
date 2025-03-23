package utils.IO;

import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Ввод-вывод в консоль
 */
public class Console {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Вывод без переноса
     * @param obj объект для вывода
     */
    public void print(Object obj){
        System.out.print(obj);
    }

    /**
     * Вывод с переносом
     * @param obj объект для вывода
     */
    public void println(Object obj){
        System.out.println(obj);
    }

    /**
     * Считывает строку из потока ввода
     * NoSuchElementException если введено ctrl+C
     * NullPointerException если ничего не введено
     * @return считанная строка
     */
    public Response<String> input(){
        Response<String> response = new Response<>();
        print("> ");
        try{
            String inp = scanner.nextLine();
            response.setStatus(ResponseStatus.OK);
            if(!inp.isEmpty()){
                response.setMessage(inp.trim());
            }
        }catch (NoSuchElementException e){
            response.setStatus(ResponseStatus.FATAL_ERROR);
        }catch (NullPointerException e){
            response.setStatus(ResponseStatus.EXCEPTION);
        }
        return response;
    }

    public Long parseLong(String line){
        if(line == null){
            return null;
        }
        return Long.parseLong(line);
    }

    public Double parseDouble(String line){
        if(line == null){
            return null;
        }
        return Double.parseDouble(line);
    }

    public Float parseFloat(String line){
        if(line == null){
            return null;
        }
        return Float.parseFloat(line);
    }

    public Integer parseInteger(String line){
        if(line == null){
            return null;
        }
        return Integer.parseInt(line);
    }
}
