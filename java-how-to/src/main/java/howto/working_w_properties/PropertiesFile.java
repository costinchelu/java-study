package howto.working_w_properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * A Properties file in Java is a list of key-value pairs that can be
 * parsed by java.util.Properties class.
 * <p>
 * Generally a Properties file has extension .properties e.g.
 * myapp.properties.
 * <p>
 * Properties files are used for many purposes in all kinds of Java
 * applications. Some of the uses are to store configuration, initial
 * data, application options etc.
 * <p>
 * When we change the value of a key in a properties file, there is no
 * need to recompile the Java application. So it provides benefit of
 * changing values at runtime.
 */
public class PropertiesFile {

    public static void main(String[] args) {

        Properties p = new Properties();
        try {
            p.load(new FileInputStream("./java-how-to/src/main/java/howto.working_w_properties/aPropertieFile.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String a = (String)p.get("key1");

        System.out.println(a);
    }
}
