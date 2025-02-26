package savers;

import IO.FileWriter;
import IO.Writer;
import models.Model;

import java.io.IOException;
import java.util.Map;

public class JsonSaver implements Saver {
    private int depth = 1;
    private Writer writer;

    /**
     * Записывает json строку в файл
     * @param data словрь значений
     * @throws IOException при ошибке записи в файл
     */
    public void save(Map<String, Object> data) throws IOException {
        String json = map2Json(data);
        String path = "output.json";
        writer = new FileWriter(path, true);
        writer.write(json);
    }

    /**
     * Конвертирует словарь в json
     * @param data - словарь значений
     * @return строку формата json
     */
    private String map2Json(Map<String, Object> data) {
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
                json += map2Json((Map<String, Object>) value);
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
}
