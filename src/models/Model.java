package models;

import java.util.Map;

/**
 * Интерфейс для моделей
 */
public interface Model {
    Map<String, Object> toMap();
    boolean validate();
}
