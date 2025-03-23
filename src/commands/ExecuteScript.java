package commands;

import utils.IO.Console;
import utils.IO.FileReader;
import managers.CommandManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;

import java.io.FileNotFoundException;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;

/**
 * Исполняет скрипт
 */
public class ExecuteScript implements Command{
    private final CommandManager commandManager;
    private String [] args;

    public ExecuteScript(CommandManager commandManager){
        this.commandManager = commandManager;
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
     * @param args название скрипта
     */
    public Response<String> execute(String[] args){
        this.args = args;
        Response<String> response = new Response<>();
        StringBuilder message = new StringBuilder();
        if(!checkArgs()){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Требуется один аргумент (имя файла)");
            return response;
        }

        String fileName = args[0];
        FileReader reader;
        try{
            reader = new FileReader(fileName);
        }catch (FileNotFoundException e){
            response.setStatus(ResponseStatus.EXCEPTION);
            response.setMessage("Файл не существует");
            return response;
        }

        String line;
        while(true) {
            line = reader.nextLine();
            if(line == null || line.equals("exit")){
                break;
            }else if(line.isEmpty()){
                continue;
            }

            String[] tokens = line.split(" ");
            try{
                Command command = commandManager.getByName(tokens[0]);
                String[] arguments = {};
                if(tokens.length > 1){
                    arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
                    if(tokens[0].equals("execute_script") && arguments[0].equals(fileName)){
                        message.append("Рекурсия запрещена\n");
                        continue;
                    }
                }
                Response<String> commandResponse = command.execute(arguments);
                if (commandResponse.getStatus() == ResponseStatus.FATAL_ERROR){
                    break;
                }else{
                    if (commandResponse.getStatus() == ResponseStatus.OK) {
                        commandManager.addToHistory(tokens[0]);
                    }
                    if(!commandResponse.getMessage().isEmpty()){
                        message.append(commandResponse.getMessage()).append("\n");
                    }
                }
            }catch (NullPointerException e){
                message.append("Команда не найдена, используйте help для вывода списка команд\n");
            }
        }

        if (!message.isEmpty()){
            message.setLength(message.length() - 1);
        }
        response.setStatus(ResponseStatus.OK);
        response.setMessage(message.toString());
        return response;
    }
}
