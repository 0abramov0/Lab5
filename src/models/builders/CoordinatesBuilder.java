package models.builders;

import models.Coordinates;
import utils.exceptions.ValidationException;

public class CoordinatesBuilder implements Builder<Coordinates>{
    private Double x;
    private Float y;
    private boolean isNullAllowed;

    public CoordinatesBuilder(){}

    public CoordinatesBuilder(boolean isNullAllowed){
        this.isNullAllowed = isNullAllowed;
    }

    public Coordinates build(){
        return new Coordinates(x, y);
    }

    /**
     * Сеттер поля
     * @param x
     * @throws ValidationException если x не валиден
     */
    public void setX(Double x){
        if(isNullAllowed && x == null){
            return;
        }

        if(x == null || x <= -306){
            throw new ValidationException("x должен быть больше -306");
        }
        this.x = x;
    }

    /**
     * Сеттер поля
     * @param y
     * @throws ValidationException если y не валиден
     */
    public void setY(Float y){
        if(isNullAllowed && y == null){
            return;
        }

        if(y == null){
            throw new ValidationException("y не может быть null");
        }
        this.y = y;
    }
}
