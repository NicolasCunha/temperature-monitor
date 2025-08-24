package com.temperature.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Type;
import java.time.Instant;

@UtilityClass
public class JsonUtil {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
            .serializeNulls()
            .create();

    public static <T> T fromJson(final String json, final Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * Type adapter for {@link Instant}.
     */
    private static class InstantTypeAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonNull()) {
                return null;
            }
            return Instant.parse(json.getAsString());
        }

        @Override
        public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive(src.toString());
        }
    }

}
