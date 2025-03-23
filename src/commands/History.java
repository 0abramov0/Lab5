package commands;

import utils.IO.Console;
import managers.CommandManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.List;

/**
 * Возвращает последние 10 команд
 */
public class History implements Command{
    private final CommandManager commandManager;
    private String [] args;

    public History(CommandManager commandManager){
        this.commandManager = commandManager;
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
     * @param args не принимает аргументов
     */
    public Response<String> execute(String[] args){
        this.args = args;
        Response<String> response = new Response<>();
        StringBuilder message = new StringBuilder();
        if(!checkArgs()){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Команда history не принимает аргументов");
            return response;
        }
        List<String> history = commandManager.getHistory();
        for(String command: history){
            message.append(command).append("\n");
        }
        if (!message.isEmpty()){
            message.setLength(message.length() - 1);
        }
        response.setStatus(ResponseStatus.OK);
        response.setMessage(message.toString());
        return response;
    }
}
