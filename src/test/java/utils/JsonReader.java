package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class JsonReader {
    public static UserData readUserData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), UserData.class);
    }
}