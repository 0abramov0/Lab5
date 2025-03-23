package commands;

import utils.IO.Console;
import managers.CommandManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.util.Set;

/**
 * Возвращает справку о доступных командах
 */
public class Help implements Command{
    private final CommandManager commandManager;
    private String[] args;

    public Help(CommandManager commandManager){
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
            response.setMessage("Команда help не принимает аргументов");
            return response;
        }
        message.append("Список доступных команд:\n");
        Set<String> commands = commandManager.getCommands();
        for(String command: commands){
            message.append("\t").append(command).append("\n");
        }

        if (!message.isEmpty()){
            message.setLength(message.length() - 1);
        }
        response.setStatus(ResponseStatus.OK);
        response.setMessage(message.toString());
        return response;
    }
}
