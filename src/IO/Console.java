package IO;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Ввод-вывод в консоль
 */
public class Console {
    private final Scanner scanner = new Scanner(System.in);

    public void print(Object obj){
        System.out.print(obj);
    }

    public void println(Object obj){
        System.out.println(obj);
    }

    public String input(){
        print("> ");
        try{
            String inp = scanner.nextLine().trim();
            return !inp.isEmpty() ? inp : null;
        }catch (NoSuchElementException e){
            return "exit";
        }

    }
}
