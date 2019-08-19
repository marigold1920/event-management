package group2.candidates.tool;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var date = json.getAsString();
        var pattern = date.substring(date.lastIndexOf("-") + 1)
                                            .matches("\\d{4}") ? "d-MMM-yyyy" : "d-MMM-yy";

        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    @Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {

        return new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("d-MMM-yyyy")));
    }

    public static LocalDate serializable(JsonElement element) {
//       var formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(element.getAsString(), formatter);
    }
}



