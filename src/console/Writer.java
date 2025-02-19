package console;

import java.io.IOException;

/**
 * Интерфейс для создания пишущих объектов
 */
public interface Writer {
    void write(String line) throws IOException;
}
