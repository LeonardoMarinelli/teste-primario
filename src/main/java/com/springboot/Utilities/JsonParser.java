package com.springboot.Utilities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class JsonParser {
    public static Gson gson = new Gson();
    public static JsonArray Conversor(String json) {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray mainArray = jsonObject.getAsJsonArray("drinks");
        return mainArray;
    }
}
