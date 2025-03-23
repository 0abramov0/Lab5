package utils.savers;

import java.io.IOException;
import java.util.Map;

/**
 * Интерфейс для создания классов, сохраняющих коллекцию в файл
 */
public interface Saver {
    /**
     * Сохраняет словарь в файл
     * @throws IOException при невозможности найти/изменить файл
     */
    void save() throws IOException;
}
