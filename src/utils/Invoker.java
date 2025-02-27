package utils;

import java.util.*;

import commands.*;
import IO.Console;
import managers.CollectionManager;
import managers.CommandManager;
import parsers.savers.Saver;

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

    private void configureCommands(){
        commandManager.addCommand("help", new Help(console, commandManager));
        commandManager.addCommand("info", new Info(console, collectionManager));
        commandManager.addCommand("history", new History(console, commandManager));
        commandManager.addCommand("insert", new Insert(console, collectionManager));
        commandManager.addCommand("show", new Show(console, collectionManager));
        commandManager.addCommand("clear", new Clear(console, collectionManager));
        commandManager.addCommand("update", new Update(console, collectionManager));
        commandManager.addCommand("remove_key", new RemoveKey(console, collectionManager));
        commandManager.addCommand("replace_if_greater", new ReplaceIfGreater(console, collectionManager));
        commandManager.addCommand("replace_if_lower", new ReplaceIfLower(console, collectionManager));
        commandManager.addCommand("average_of_budget", new AverageOfBudget(console, collectionManager));
        commandManager.addCommand("group_counting_by_operator", new GroupCountingByOperator(console, collectionManager));
        commandManager.addCommand("filter_by_oscars_count", new FilterByOscarsCount(console, collectionManager));
        commandManager.addCommand("execute_script", new ExecuteScript(console, commandManager));
        commandManager.addCommand("save", new Save(console, collectionManager, saver));
    }

    /**
     * Запускает интерактивный режим программы
     */
    public void interactiveMode(){
        while(true) {
            String line = console.input();
            if(line == null){
                continue;
            }if(line.equals("exit")){
                break;
            }
            String[] tokens = line.split(" ");
            try{
                Command command = commandManager.getByName(tokens[0]);
                String[] args = {};
                if(tokens.length > 1){
                    args = Arrays.copyOfRange(tokens, 1, tokens.length);
                }
                command.execute(args);
                commandManager.addToHistory(tokens[0]);
            }catch (NullPointerException e){
                console.println("Команда не найдена, используйте help для вывода списка команд");
            }
        }
    }
}
