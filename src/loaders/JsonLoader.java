package loaders;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import IO.FileReader;
import IO.Reader;
import managers.CollectionManager;
import models.Movie;
import parsers.MovieParser;

/**
 * Загрузчик данных из файла в коллекцию
 */
public class JsonLoader implements Loader {
    private int index = 0;
    private String fileName;
    private String json;
    private CollectionManager collectionManager = new CollectionManager();
    private Reader reader;

    public JsonLoader(String fileName){
        this.fileName = fileName;
    }

    /**
     * Загружает данные из файла и добавляет в коллекцию
     */
    public void load(){
        Map<String, Object> data;
        try {
            data = parse();
        } catch (FileNotFoundException e) {
            System.out.println("Файла с таким названием не существует");
            return;
        }

        collectionManager.clear();
        for(String key: data.keySet()){
            if(!(data.get(key) instanceof Map)){
                return;
            }

            Map<String, Object> currentMap = (Map<String, Object>) data.get(key);
            currentMap.put("id", key);
            MovieParser movieParser = new MovieParser();
            Movie movie = movieParser.fromMap(currentMap);
            if(movie != null && movie.validate()){
                collectionManager.insert(movie);
            }
        }
    }

    /**
     * Создает объект для чтения файла и запускает парсер
     * @return json переведенный в map
     * @throws FileNotFoundException в случае если файл не существует
     */
    private Map<String, Object> parse() throws FileNotFoundException{
        reader = new FileReader(fileName);
        json = reader.read().replace("\n", "");
        if(json.isEmpty()){
            return new HashMap<>();
        }
        return parseObject();
    }

    /**
     * Переводит json объект в Map
     * @return json конвертированный в словарь
     */
    private  Map<String, Object> parseObject() {
        HashMap<String, Object> parsedMap = new HashMap<>();

        while (index < json.length()) {
            char currentChar = json.charAt(index);
            if(currentChar == '{' || currentChar == ','){
                index++;
            }
            if(json.charAt(index) == '}') {
                index++;
                return parsedMap;
            }
            skipSpaces();
            String key = parseString().toLowerCase();
            skipSpaces();

            if (json.charAt(index) != ':') {
                throw new RuntimeException("Excepted ':' after key");
            }

            index++;
            skipSpaces();
            Object value = parseValue();
            parsedMap.put(key, value);
            skipSpaces();

            if (json.charAt(index) == ',') {
                index++;
            }
        }
        throw new RuntimeException("Excepted '}' in the end of file");
    }

    /**
     * Парсер значения по ключу
     * @return значение
     */
    private Object parseValue() {
        char currentChar = json.charAt(index);
        if (currentChar == '{') {
            return parseObject();
        } else if (currentChar == '"') {
            return parseString();
        } else if (json.startsWith("null", index)) {
            index += 4;
            return null;
        } else if (Character.isDigit(currentChar)) {
            return parseNumber();
        } else {
            throw new RuntimeException("Invalid type");
        }
    }

    /**
     * Парсер строки из json
     * @return строка
     */
    private String parseString() {
        index++;
        String s = "";
        while (index < json.length()) {
            char currentChar = json.charAt(index);
            if (currentChar == '"') {
                index++;
                return s;
            } else {
                s += currentChar;
            }
            index++;
        }
        throw new RuntimeException("String must end with double quotes");
    }

    /**
     * Парсер числа из json
     * @return число
     */
    private Number parseNumber() {
        String s = "";
        while (index < json.length()) {
            char currentChar = json.charAt(index);
            if (Character.isDigit(currentChar) || currentChar == '.') {
                s += currentChar;
                index++;
            } else {
                break;
            }
        }
        if (s.contains(".")) {
            return Double.parseDouble(s);
        }
        return Long.parseLong(s);
    }

    /**
     * Пропускает пробелы в строке
     */
    private void skipSpaces() {
        while (index < json.length() && Character.isWhitespace(json.charAt(index))) {
            index++;
        }
    }
}