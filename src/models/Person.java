package models;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Модель персоны
 */
public class Person implements Mapable, Mergeable<Person> {
    private String name;
    private ZonedDateTime birthday;
    private HairColor hairColor;
    private EyeColor eyeColor;
    private Country nationality;

    public Person(String name, ZonedDateTime birthday, HairColor hairColor, EyeColor eyeColor, Country nationality){
        this.name = name;
        this.birthday = birthday;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.nationality = nationality;
    }

    /**
     * Геттер name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Геттер birthday
     * @return birthday
     */
    public ZonedDateTime getBirthday() {
        return birthday;
    }

    /**
     * Геттер hairColor
     * @return hairColor
     */
    public HairColor getHairColor(){
        return hairColor;
    }

    /**
     * Геттер nationality
     * @return nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Геттер eyeColor
     * @return eyeColor
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /**
     * Устанавливает объекту поле из operator если оно не null
     * @param operator объединенные координаты
     */
    public void merge(Person operator){
        String newName = operator.getName();
        ZonedDateTime newBirthday = operator.getBirthday();
        EyeColor newEyeColor = operator.getEyeColor();
        HairColor newHairColor = operator.getHairColor();
        Country newNationality = operator.getNationality();

        if(newName != null){
            this.name = newName;
        }
        if(newBirthday != null){
            this.birthday = newBirthday;
        }
        if(newEyeColor != null){
            this.eyeColor = newEyeColor;
        }
        if(newHairColor != null){
            this.hairColor = newHairColor;
        }
        if(newNationality != null){
            this.nationality = newNationality;
        }
    }

    /**
     * Возвращает поля объекта в виде словаря
     * @return словарь полей объекта
     */
    public Map<String, Object> toMap(){
        HashMap<String, Object> fields = new HashMap<>();
        fields.put("name", name);
        fields.put("birthday", birthday == null ? null : birthday.toString());
        fields.put("eyeColor", eyeColor == null ? null : eyeColor.toString());
        fields.put("hairColor", hairColor == null ? null : hairColor.toString());
        fields.put("nationality", nationality == null ? null : nationality.toString());
        return fields;
    }

    @Override
    public String toString(){
        Map<String, Object> fields = toMap();
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (var k : fields.keySet()) {
            sb.append("\t");
            sb.append('"').append(k).append('"');
            sb.append(": ");
            var value = fields.get(k);
            if(value instanceof String){
                sb.append('"').append(value).append('"');
            }else{
                sb.append(value);
            }
            sb.append(",\n");
        }
        sb.setLength(sb.length() - 2);
        sb.append("\n  }");
        return sb.toString();
    }
}
