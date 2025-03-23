package utils.savers;

import utils.IO.FileWriter;
import utils.IO.Writer;
import managers.CollectionManager;

import java.io.IOException;

/**
 * Сохраняет коллекцию в Json
 */
public class JsonSaver implements Saver {
    private Writer writer;
    private String filePath;
    private CollectionManager collectionManager;

    public JsonSaver(String filePath, CollectionManager collectionManager){
        this.filePath = filePath;
        this.collectionManager = collectionManager;
    }

    /**
     * Сохраняет словарь в json
     * @throws IOException при не возможности открыть/получить доступ
     */
    public void save() throws IOException {
        long index = 1L;
        long size = collectionManager.getSize();
        writer = new FileWriter(filePath, true);
        writer.write("[\n");
        for (var k: collectionManager.getKeys()){
            writer.write(collectionManager.getById(k).toString());
            if(index != size){
                writer.write(",\n");
            }
            index++;
        }
        writer.write("\n]");
    }
}
