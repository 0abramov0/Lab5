package parsers.savers;

import IO.FileWriter;
import IO.Writer;
import models.Model;

import java.io.IOException;
import java.util.Map;

public class JsonSaver implements Saver {
    private int depth = 1;
    private Writer writer;
    private String filePath;

    public JsonSaver(String filePath){
        this.filePath = filePath;
    }

    public void save(Map<String, Object> data) throws IOException {
        String json = map2Json(data);
        writer = new FileWriter(filePath, true);
        writer.write(json);
    }

    /**
     * Конвертирует словарь в json
     * @param data - словарь значений
     * @return строку формата json
     */
    private String map2Json(Map<String, Object> data) {
        StringBuilder json = new StringBuilder("{\n");
        int index = 0;

        for (String key: data.keySet()) {
            Object value = data.get(key);
            json.append("\t".repeat(depth)).append("\"").append(key).append("\": ");

            switch (value) {
                case Number number:
                    json.append(value);
                    break;
                case null:
                    json.append(value);
                    break;
                case Model model:
                    depth++;
                    json.append(map2Json(model.toMap()));
                    break;
                case Map map:
                    depth++;
                    json.append(map2Json((Map<String, Object>) value));
                    break;
                default:
                    json.append("\"").append(value).append("\"");
            }
            if (index < data.size() - 1) {
                json.append("," + "\n");
            }
            index++;
        }
        StringBuilder result = new StringBuilder("\t".repeat(depth - 1) + json);
        if(depth == 1){
            result.append("\n}");
        }else{
            depth--;
            result.append("\n" + "\t".repeat(depth) + "}");
        }
        return result.toString();
    }
}
