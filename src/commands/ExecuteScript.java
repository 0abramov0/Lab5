package commands;

import IO.Console;
import IO.FileReader;
import managers.CommandManager;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Исполняет скрипт
 */
public class ExecuteScript implements Command{
    private final Console console;
    private final CommandManager commandManager;

    public ExecuteScript(Console console, CommandManager commandManager){
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду
     * @param args название скрипта
     */
    public void execute(String[] args){
        if(args.length != 1){
            console.println("Требуется один аргумент (file name)");
            return;
        }

        String fileName = args[0];
        FileReader reader;
        try{
            reader = new FileReader(fileName);
        }catch (FileNotFoundException e){
            console.println("Такого файла не существует");
            return;
        }

        String line = reader.nextLine();
        while(!(line.equals("exit")) && line != null) {
            String[] tokens = line.split(" ");
            try{
                Command command = commandManager.getByName(tokens[0]);
                String[] arguments = {};
                if(tokens.length > 1){
                    arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
                    if(tokens[0].equals("execute_script") && arguments[0].equals(fileName)){
                        console.println("Запуск скрипта внутри его же не допустим");
                        line = reader.nextLine();
                        continue;
                    }
                }
                command.execute(arguments);
                commandManager.addToHistory(tokens[0]);
            }catch (NullPointerException e){
                console.println("Команда не найдена, используйте help для вывода списка команд");
            }
            line = reader.nextLine();
        }
    }
}
