package models;

/**
 * Перечисление стран
 */
public enum Country {
    FRANCE("France"),
    INDIA("India"),
    VATICAN("Vatican"),
    THAILAND("Thailand"),
    NORTH_KOREA("North_Korea");

    private final String name;
    Country(String name){
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
