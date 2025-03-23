package models;

import java.util.Map;

/**
 * Интерфейс для моделей
 */
public interface Mapable {
    /**
     * Конвертирует модель в словарь
     * @return словарь
     */
    Map<String, Object> toMap();
}
