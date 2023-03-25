package exercises.ch14;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Resources {
    private static final String projectRoot = Path.of("").toAbsolutePath().toString();
    private static final String imageDirPath = Paths.get(projectRoot, "src", "resources", "image").toString();
    private static final File imageDir = new File(imageDirPath);

    public static String get(String fileName) {
        File file = new File(imageDir, fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException();
        }
        return file.getPath();
    }

    public static String get(String dirName, String fileName) {
        File dir = new File(imageDir, dirName);
        if (!dir.exists()) {
            throw new IllegalArgumentException();
        }
        File file = new File(dir, fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException();
        }
        return file.getPath();
    }

}
