package howto.working_w_properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class GetPropertiesValues {

    public static void main(String[] args) throws IOException {
        getPropertyValues();
    }

    public static void getPropertyValues() {
        Properties prop = new Properties();
        String propFileName = "config.properties";
        try (InputStream inputStream =
                     GetPropertiesValues.class.getClassLoader().getResourceAsStream(propFileName)) {
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Properties file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());
            String user = prop.getProperty("user");
            String company1 = prop.getProperty("company1");
            String company2 = prop.getProperty("company2");
            String company3 = prop.getProperty("company3");
            String result = "Company List = " + company1 + ", " + company2 + ", " + company3;
            System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
