package ml.socshared.vkadapter.clients;

import com.google.gson.*;
import ml.socshared.vkadapter.models.User;
import ml.socshared.vkadapter.models.Error;
import ml.socshared.vkadapter.models.VKResponse;

import java.lang.reflect.Type;

public class VKJsonConverter implements JsonSerializer<VKResponse<User>>, JsonDeserializer<VKResponse<User>> {
    @Override
    public JsonElement serialize(VKResponse<User> src, Type typeOfSrc, JsonSerializationContext context) {
        if(src.isError()) {
            JsonObject json_error = new JsonObject();
            json_error.add("!error", context.serialize(src.getError()));
            json_error.add("=(", context.serialize("XD"));
            return json_error;
        } else {
            JsonArray ok = new JsonArray();
            ok.add(context.serialize(src.getResponse()));
            JsonObject ok_json = new JsonObject();
            ok_json.add("response", ok);
            return ok_json;
        }
    }


    @Override
    public VKResponse<User> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
       JsonObject jobj = json.getAsJsonObject();
        JsonElement error = jobj.get("error");
        if (error == null) {
            //Ошибка отсутствует. Пришел результат
            JsonElement okResponse = jobj.get("response");
            JsonArray uarray =  okResponse.getAsJsonArray();
            User user = context.deserialize(uarray.get(0), User.class);
            return new VKResponse<User>(user);
        } else {
            //Возникла ошибка. Json является описанием ошибки
            Error user_error = context.deserialize(error, Error.class);
            return new VKResponse<User>(user_error);
        }
    }


}
