package savers;

import java.io.IOException;
import java.util.Map;

public interface Saver {
    void save(Map<String, Object> savedStuff) throws IOException;
}
