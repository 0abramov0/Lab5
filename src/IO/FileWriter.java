package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Организует запись данных в файл
 */
public class FileWriter implements Writer{
    private File file;
    private FileOutputStream writer;

    public FileWriter(String fileName, boolean clearFile) throws FileNotFoundException {
        this.file = new File(fileName);
        this.writer = new FileOutputStream(file, !clearFile);
    }

    public void write(String line) throws IOException {
        writer.write(line.getBytes());
    }
}
