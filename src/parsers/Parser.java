package parsers;

import IO.Console;

import java.util.Map;

/**
 * Интерфейс для создания парсеров моделей
 * @param <T> объект который парсим
 */
public interface Parser<T> {
    T fromConsole(Console console, String [] args);
    T fromMap(Map<String, Object> map);
}
