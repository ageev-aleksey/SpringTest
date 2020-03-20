package ml.socshared.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ml.socshared.vkadapter.clients.VKJsonConverter;
import ml.socshared.vkadapter.models.User;
import ml.socshared.vkadapter.models.VKResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class ServiceConfigure implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<VKResponse<User>>(){}.getType(),
                new VKJsonConverter())
                .create();
        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
        gsonConverter.setGson(gson);
        converters.add(gsonConverter);
        //configureMessageConverters(converters);
    }
}
