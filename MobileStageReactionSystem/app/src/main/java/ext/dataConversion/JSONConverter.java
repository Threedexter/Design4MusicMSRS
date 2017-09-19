package ext.dataConversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Rowan on 19/09/17.
 */

public abstract class JSONConverter {
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * @param json String, url or file to read and convert to class
     * @param c Class to convert to
     * @return An object of the class
     * @throws IOException
     */
    public static Object convertToClass(String json, Class c) throws IOException {
        return mapper.readValue(json, c);
    }

    /**
     * @param obj Class to convert to JSON
     * @return A string of JSON
     * @throws JsonProcessingException
     */
    public static String convertToJSON(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
