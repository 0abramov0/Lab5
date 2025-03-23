package commands;

import utils.IO.Console;
import managers.CollectionManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;

/**
 * Удаляет элемент по ключу
 */
public class RemoveKey implements Command{
    private final CollectionManager collectionManager;
    private String[] args;

    public RemoveKey(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * Проверка аргументов команды
     * @return если аргументы валидны
     */
    public boolean checkArgs(){
        return args.length == 1;
    }

    /**
     * Выполняет команду
     * @param args id значения
     */
    public Response<String> execute(String[] args){
        this.args = args;
        Response<String> response = new Response<>();
        if(!checkArgs()){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Требуется один аргумент (id)");
            return response;
        }
        long id;
        try{
            id = Integer.parseInt(args[0]);
            if(!collectionManager.removeKey(id)){
                response.setStatus(ResponseStatus.EXCEPTION);
                response.setMessage("Элемента с таким id не существует");
            }else{
                response.setStatus(ResponseStatus.OK);
                response.setMessage("Объект успешно удален");
            }
        }catch (NumberFormatException e){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Id должен быть целым числом");
        }
        return response;
    }
}
