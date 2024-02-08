package howto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WorkingWithJson {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonTest jsonTest = new JsonTest(1, "first", true);

        try {
            String jsonString = objectMapper.writeValueAsString(jsonTest);
            System.out.println(jsonString);

            JsonTest objFromString = objectMapper.readValue(jsonString, JsonTest.class);
            System.out.println(objFromString);

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }


    }
}

record JsonTest(int intKey, String strKey, boolean boolKey) { }
