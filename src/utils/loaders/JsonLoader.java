package utils.loaders;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import utils.IO.Console;
import utils.IO.FileReader;
import utils.IO.Reader;
import managers.CollectionManager;
import models.Movie;
import models.parsers.MovieParser;
import utils.responses.Response;
import utils.responses.ResponseStatus;

/**
 * Загрузчик данных из файла в коллекцию
 */
public class JsonLoader implements Loader {
    private int index = 0;
    private String fileName;
    private String json;
    private CollectionManager collectionManager = new CollectionManager();
    private Console console = new Console();
    private Reader reader;

    public JsonLoader(String fileName){
        this.fileName = fileName;
    }

    /**
     * Загружает данные из файла и добавляет в коллекцию
     */
    public void load(){
        Map<String, Object> data;
        MovieParser movieParser = new MovieParser();
        Response<Movie> movieResponse;
        try {
            openFile();
        } catch (FileNotFoundException e) {
            console.println("Файла с таким названием не существует");
            return;
        }

        collectionManager.clear();
        while (index < json.length()){
            data = parseObject();
            movieResponse = movieParser.fromMap(data);
            if(movieResponse.getStatus() != ResponseStatus.EXCEPTION){
                collectionManager.insert(movieResponse.getMessage());
                console.println("id " + data.get("id") + " успешно добавлен");
            }
        }
    }

    /**
     * Читает json
     * @throws FileNotFoundException если файл не существует
     */
    private void openFile() throws FileNotFoundException{
        reader = new FileReader(fileName);
        json = reader.read().replace("\n", "");
    }

    /**
     * Переводит json объект в Map
     * @return json конвертированный в словарь
     */
    private  Map<String, Object> parseObject() {
        HashMap<String, Object> parsedMap = new HashMap<>();

        while (index < json.length()) {
            while(json.charAt(index) == '{' || json.charAt(index) == ',' || json.charAt(index) == '['){
                index++;
            }
            while(json.charAt(index) == '}' || json.charAt(index) == ']') {
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
        }else if(currentChar == '-'){
            index++;
            return "-" + parseNumber();
        }
        else {
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