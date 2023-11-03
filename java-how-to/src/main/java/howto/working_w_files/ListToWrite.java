package howto.working_w_files;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListToWrite {

    private final List<String> stringList;

    public ListToWrite() {
        stringList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            StringBuilder sb = new StringBuilder();
            for (int it = 1; it <= i; it++) {
                sb.append("A");
            }
            stringList.add(sb.toString());
        }
    }
}
