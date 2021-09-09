import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class FileHandler {

    public static byte[] getByteFromFile(String filename) {
        byte[] bytes;
        InputStream is = FileHandler.class.getClassLoader().getResourceAsStream(filename);
        try {
            bytes = IOUtils.readAllBytes(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return bytes;
    }
}
