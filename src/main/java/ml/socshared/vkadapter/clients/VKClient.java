package ml.socshared.vkadapter.clients;

import feign.Param;
import ml.socshared.vkadapter.models.User;
import ml.socshared.vkadapter.models.VKResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name="VKClient", url="https://api.vk.com/method",
            configuration = ml.socshared.vkadapter.clients.ClientConfiguration.class)
public interface VKClient {
   // @RequestLine("GET /users.get&users_ids={users_ids}")
    

    @RequestMapping(method=RequestMethod.GET, value="/users.get?users_ids={users_ids}", produces = "application/json")
    VKResponse<User> getUser(@Param("users_ids") String users_ids);
}
