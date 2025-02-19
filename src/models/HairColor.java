package models;

/**
 * Перечисление цветов волос
 */
public enum HairColor {
    GREEN("Green"),
    BLACK("Black"),
    YELLOW("Yellow"),
    ORANGE("Orange"),
    BROWN("Brown");

    private final String name;
    HairColor(String name){
        this.name = name;
    }

    /**
     * Строковое представление констант
     * @return название поля
     */
    @Override
    public String toString(){
        return name;
    }
}
