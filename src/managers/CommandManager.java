package managers;

import commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Управляет командами
 */
public class CommandManager {
    private HashMap<String, Command> commands = new HashMap<>();
    private ArrayList<String> history = new ArrayList<>();

    /**
     * Возвращает объект команды по названию
     * @param name имя команды
     * @return объект команды
     */
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

    /**
     * Возвращает множество названий команд
     * @return названия команд
     */
    public Set<String> getCommands(){
        return commands.keySet();
    }

    /**
     * Возвращает историю команд
     * @return история
     */
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
