package commands;

import utils.responses.Response;

/**
 * Интерфейс команд
 */
public interface Command {
    /**
     * Метод выполняющий команду
     * @param args аргументы команды
     */
    Response<String> execute(String[] args);
}
