package commands;

import console.Console;
import console.FileWriter;
import managers.CollectionManager;
import models.Model;

import java.io.IOException;
import java.util.*;

/**
 * Сохраняет коллекцию в файл
 */
public class Save implements Command{
    private final Console console;
    private final CollectionManager collectionManager;
    private int depth = 1;

    public Save(Console console, CollectionManager collectionManager){
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Конвертирует словарь в json
     * @param data - словарь значений
     * @return строку формата json
     */
    private String map2Json(Map<?, ?> data) {
        String json = "{\n";
        int index = 0;

        for (Object key: data.keySet()) {
            Object value = data.get(key);
            json += "\t".repeat(depth) + "\"" + key + "\": ";

            if (value instanceof Number || value == null) {
                json += value;
            }else if(value instanceof Model){
                depth++;
                json += map2Json(((Model) value).toMap());
            } else if (value instanceof Map) {
                depth++;
                json += map2Json((Map<?, ?>) value);
            } else {
                json += "\""
                        + value
                        + "\"";
            }
            if (index < data.size() - 1) {
                json += "," + "\n";
            }
            index++;
        }
        String result = "\t".repeat(depth - 1) + json;
        if(depth == 1){
            result += "\n}";
        }else{
            depth --;
            result += "\n" + "\t".repeat(depth) + "}";
        }
        return result;
    }

    /**
     * Записывает json строку в файл
     * @param data словрь значений
     * @throws IOException при ошибке записи в файл
     */
    public void save(Map<String, Object> data) throws IOException {
        String json = map2Json(data);
        String path = "output.json";
        FileWriter writer = new FileWriter(path, true);
        writer.write(json);
    }

    /**
     * Выполняет команду
     * @param args не принимает аргументы
     */
    public void execute(String[] args){
        if(args.length > 0){
            console.println("Команда не принимает аргументов");
            return;
        }
        try{
            HashMap<String, Object> data = new HashMap<>();
            Set<Long> keys = collectionManager.getKeys();
            for(Long key: keys){
                data.put(key.toString(), collectionManager.getById(key));
            }
            save(data);
        }catch (IOException e){
            console.println("Не удалось сохранить коллекцию в файл");
        }
    }
}
