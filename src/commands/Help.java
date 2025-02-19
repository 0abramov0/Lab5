package commands;

import console.Console;
import managers.CommandManager;

import java.util.Set;

/**
 * Возвращает справку о доступных командах
 */
public class Help implements Command{
    private final Console console;
    private final CommandManager commandManager;

    public Help(Console console, CommandManager commandManager){
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
        console.println("Список доступных команд:");
        Set<String> commands = commandManager.getCommands();
        for(String command: commands){
            console.println("\t" + command);
        }
    }
}
