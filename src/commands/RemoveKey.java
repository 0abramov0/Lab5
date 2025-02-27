package commands;

import IO.Console;
import managers.CollectionManager;

/**
 * Удаляет элемент по ключу
 */
public class RemoveKey implements Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveKey(Console console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @param args id значения
     */
    public void execute(String[] args){
        if(args.length != 1){
            console.println("Требуется один аргумент (id)");
            return;
        }
        long id;
        while (true){
            try{
                id = Integer.parseInt(args[0]);
                if(!collectionManager.removeKey(id)){
                    console.println("Элемента с таким id не существует");
                };
                console.println("Объект успешно удален");
                break;
            }catch (NumberFormatException e){
                console.println("Id должен быть целым числом");
            }
        }
    }
}
