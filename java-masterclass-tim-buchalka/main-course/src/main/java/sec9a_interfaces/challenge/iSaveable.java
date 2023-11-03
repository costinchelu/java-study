package sec9a_interfaces.challenge;

import java.util.List;

public interface iSaveable {

    List write();

    void read(List<String> savedValues);
}
