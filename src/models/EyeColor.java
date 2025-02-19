package models;

/**
 * Перечисление цветов глаз
 */
public enum EyeColor {
    BLACK("Black"),
    BLUE("Blue"),
    ORANGE("Orange"),
    WHITE("White");

    private final String name;
    EyeColor(String name){
        this.name = name;
    }

    /**
     * Строковое представление констант
     * @return название поля
     */
    @Override
    public String toString() {
        return name;
    }
}
