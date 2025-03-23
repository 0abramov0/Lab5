package models;

/**
 * Перечисление жанров кино
 */
public enum MovieGenre {
    MUSICAL("Musical"),
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science_fiction");

    private final String name;
    MovieGenre(String name){
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