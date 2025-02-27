package commands;

import IO.Console;
import managers.CommandManager;

import java.util.List;

/**
 * Возвращает последние 10 команд
 */
public class History implements Command{
    private final Console console;
    private final CommandManager commandManager;

    public History(Console console, CommandManager commandManager){
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду
     * @param args не принимает аргументов
     */
    public void execute(String[] args){
        if(args.length > 0){
            console.println("Команда не принимает аргументов");
            return;
        }
        List<String> history = commandManager.getHistory();
        for(String command: history){
            console.println(command);
        }
    }
}
