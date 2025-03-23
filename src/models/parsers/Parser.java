package models.parsers;

import utils.IO.Console;
import utils.responses.Response;

import java.util.Map;

/**
 * Интерфейс для создания парсеров моделей
 * @param <T> объект который парсится
 */
public interface Parser<T> {
    /**
     * Парсер из коноли
     * @param console консоль тз которой парсим
     * @param isNullAllowed true если не нужно проверять поля на null
     * @return объект T
     */
    Response<T> fromConsole(Console console, boolean isNullAllowed);

    /**
     * Парсер из словаря
     * @param map словарь из которого парсим
     * @return объект T
     */
    Response<T> fromMap(Map<String, Object> map);
}
