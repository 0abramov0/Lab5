package utils.IO;

import java.io.IOException;

/**
 * Интерфейс для создания пишущих объектов
 */
public interface Writer {
    /**
     * Записывает строку в поток вывода
     * @param line строка
     * @throws IOException если не удалось открыть/записать в файл
     */
    void write(String line) throws IOException;
}
