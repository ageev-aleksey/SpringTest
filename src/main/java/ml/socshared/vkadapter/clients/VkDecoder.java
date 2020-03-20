package ml.socshared.vkadapter.clients;

import com.google.gson.Gson;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import ml.socshared.vkadapter.models.User;

public class VkDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        InputStreamReader reader = new InputStreamReader(response.body().asInputStream(), "UTF-8");
        char[] tmp = new char[10];
        StringBuilder body = new StringBuilder();
        int readed = 0;
        while((readed = reader.read(tmp) ) > 0) {
            for(int i = 0; i < readed; i++) {
                body.append(tmp[i]);
            }
        }
        System.out.println("**************");
        System.out.println(body.toString());
        System.out.println("**************");
        Type hash_map_type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = new Gson().fromJson(body.toString(), hash_map_type);
        if(myMap.containsKey("error")) {
            return new Gson().fromJson(reader, Error.class);
        } else {
            return new Gson().fromJson(reader, User.class);
        }
    }
}
