package hr.karlovrbic.notify.network.deserializers;

import android.text.TextUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String jsonDate = json.getAsString();
        if (!TextUtils.isEmpty(jsonDate)) {
            try {
                return dateFormat.parse(jsonDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(dateFormat.format(src));
    }
}
