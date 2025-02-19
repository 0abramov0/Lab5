package managers;

import commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Управляет командами
 */
public class CommandManager {
    HashMap<String, Command> commands = new HashMap<>();
    ArrayList<String> history = new ArrayList<>();

    public Command getByName(String name){
        return commands.get(name);
    }

    /**
     * Добавление команды
     * @param name имя команды
     * @param command объект команды
     */
    public void addCommand(String name, Command command){
        commands.put(name, command);
    }

    public Set<String> getCommands(){
        return commands.keySet();
    }

    public ArrayList<String> getHistory(){
        return history;
    }

    /**
     * Добавляет команд в историю
     * @param command - команда для добавления
     */
    public void addToHistory(String command){
        if(history.size() == 10){
            history.remove(history.getFirst());
        }
        history.add(command);
    }
}
