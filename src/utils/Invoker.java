package utils;

import commands.*;
import utils.IO.Console;
import managers.CollectionManager;
import managers.CommandManager;
import utils.responses.Response;
import utils.responses.ResponseStatus;
import utils.savers.Saver;

import java.util.Arrays;

/**
 * Запускает интерактивный режим и создает команды
 */
public class Invoker {
    private Console console;
    private CommandManager commandManager;
    private CollectionManager collectionManager;
    private Saver saver;

    public Invoker(Console console, CollectionManager collectionManager, CommandManager commandManager, Saver saver){
        this.console = console;
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.saver = saver;
        configureCommands();
    }

    /**
     * Инициализирует команды
     */
    private void configureCommands(){
        commandManager.addCommand("help", new Help(commandManager));
        commandManager.addCommand("info", new Info(collectionManager));
        commandManager.addCommand("history", new History(commandManager));
        commandManager.addCommand("insert", new Insert(console, collectionManager));
        commandManager.addCommand("show", new Show(collectionManager));
        commandManager.addCommand("clear", new Clear(collectionManager));
        commandManager.addCommand("update", new Update(console, collectionManager));
        commandManager.addCommand("remove_key", new RemoveKey(collectionManager));
        commandManager.addCommand("replace_if_greater", new ReplaceIfGreater(console, collectionManager));
        commandManager.addCommand("replace_if_lower", new ReplaceIfLower(console, collectionManager));
        commandManager.addCommand("average_of_budget", new AverageOfBudget(collectionManager));
        commandManager.addCommand("group_counting_by_operator", new GroupCountingByOperator(collectionManager));
        commandManager.addCommand("filter_by_oscars_count", new FilterByOscarsCount(collectionManager));
        commandManager.addCommand("execute_script", new ExecuteScript(commandManager));
        commandManager.addCommand("save", new Save(saver));
    }

    /**
     * Запускает интерактивный режим программы
     */
    public void interactiveMode(){
        while(true) {
            Response<String> inputResponse = console.input();
            ResponseStatus status = inputResponse.getStatus();
            String line = inputResponse.getMessage();
            if(status == ResponseStatus.FATAL_ERROR || (line!= null && line.equals("exit"))){
                break;
            }else if(line == null){
                continue;
            }
            String[] tokens = line.split(" ");
            try{
                Command command = commandManager.getByName(tokens[0]);
                String[] args = {};
                if(tokens.length > 1){
                    args = Arrays.copyOfRange(tokens, 1, tokens.length);
                }
                Response<String> commandResponse = command.execute(args);
                if (commandResponse.getStatus() == ResponseStatus.FATAL_ERROR){
                    break;
                }else{
                    if (commandResponse.getStatus() == ResponseStatus.OK) {
                        commandManager.addToHistory(tokens[0]);
                    }
                    if(!commandResponse.getMessage().isEmpty()){
                        console.println(commandResponse.getMessage());
                    }
                }
            }catch (NullPointerException e){
                console.println("Команда не найдена, используйте help для вывода списка команд");
            }
        }
    }
}
