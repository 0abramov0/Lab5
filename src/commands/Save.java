package commands;

import utils.IO.Console;
import managers.CollectionManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;
import utils.savers.Saver;

import java.io.IOException;

/**
 * Сохраняет коллекцию в файл
 */
public class Save implements Command{
    private final Saver saver;
    private String [] args;

    public Save(Saver saver){
        this.saver = saver;
    }

    /**
     * Проверка аргументов команды
     * @return если аргументы валидны
     */
    public boolean checkArgs(){
        return args.length == 0;
    }

    /**
     * Выполняет команду
     * @param args не принимает аргументы
     */
    public Response<String> execute(String[] args){
        this.args = args;
        Response<String> response = new Response<>();
        if(!checkArgs()){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Команда не принимает аргументов");
            return response;
        }
        try{
            saver.save();
            response.setStatus(ResponseStatus.OK);
            response.setMessage("Коллекция успешно сохранена");
        }catch (IOException e){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Не удалось сохранить коллекцию в файл");
        }
        return response;
    }
}
