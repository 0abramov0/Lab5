package parsers;

import IO.Console;

import java.util.HashMap;
import java.util.Map;

/**
 * Интерфейс для создания парсеров моделей
 * @param <T> объект который парсим
 */
public interface Parser<T> {
    T fromConsole(Console console, boolean isNullAllowed);
    T fromMap(Map<String, Object> map);
}
