package ml.socshared.services;

import com.google.gson.Gson;
import ml.socshared.db.models.Pair;
import ml.socshared.db.repositories.PairRepository;
import ml.socshared.vkadapter.models.Error;
import ml.socshared.vkadapter.clients.VKClient;

import ml.socshared.vkadapter.models.User;
import ml.socshared.vkadapter.models.VKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainService {

    @Autowired
    private VKClient httpClient;

    @Autowired
    private PairRepository rep;


    @RequestMapping("/users")
    public VKResponse<User> userPage(@RequestParam(value="id", required=true) String id) {

        VKResponse<User> response =  httpClient.getUser(id);
        return response;
    }

    @RequestMapping("value/add")
    public String addValue(@RequestParam(value="key", required=true) String key,
                           @RequestParam(value="value", required=true)String value) {
        Pair p = new Pair();
        p.key = key;
        p.value = value;
        rep.save(p);
        return "Ok";
    }

    @RequestMapping("value/get")
    public String getValue(@RequestParam(value="key", required=true) String key) {
        Pair  p = rep.findByKey(key);
        return p.value;
    }
}
