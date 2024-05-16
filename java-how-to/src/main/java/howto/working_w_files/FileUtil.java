package howto.working_w_files;

import lombok.Getter;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FileUtil {

    public static final List<String> STRING_LIST;

    public static final String IO_PATH = setAFilePath().toString();

    static {
        STRING_LIST = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            StringBuilder sb = new StringBuilder();
            for (int it = 1; it <= i; it++) {
                sb.append("A");
            }
            STRING_LIST.add(sb.toString());
        }
    }

    public static Path setAFilePath() {
        Path path = FileSystems.getDefault().getPath("");
        Path insideProject = Paths.get("java-how-to/src/main/java/howto/working_w_files/list.txt");
        return path.resolve(insideProject);
    }
}
