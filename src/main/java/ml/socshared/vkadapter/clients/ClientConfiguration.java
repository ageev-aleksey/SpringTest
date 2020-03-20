package ml.socshared.vkadapter.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.codec.Decoder;
import feign.gson.GsonEncoder;
import ml.socshared.vkadapter.models.User;
import ml.socshared.vkadapter.models.VKResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {
    @Bean
    Decoder setDecoder() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<VKResponse<User>>(){}.getType(),
                        new VKJsonConverter())
                .create();
        return new GsonDecoder(gson);
    }

    @Bean
    Encoder setEncoder() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<VKResponse<User>>(){}.getType(),
                        new VKJsonConverter())
                .create();
        return new GsonEncoder(gson);
    }
}
