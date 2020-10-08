package com.softvider.controller;

import com.softvider.model.LoginReqModel;
import com.softvider.service.CacheService;
import com.softvider.service.HomeService;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cache")
public class CacheController {

    CacheService cacheService = new CacheService();

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Cacheable(cacheNames = "softvider_cache",
            keyGenerator = "getKeyGenerator")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Map<String, Object> Home() throws InterruptedException {
        return cacheService.GetData(null);
    }

    @Cacheable(cacheNames = "softvider_cache",
            keyGenerator = "getWithParamKeyGenerator")
    @RequestMapping(value = "/param/{param}", method = RequestMethod.GET)
    public Map<String, Object> Param(@PathVariable(value="param") String param) {
        return cacheService.GetData(param);
    }

    @Cacheable(cacheNames = "softvider_cache",
            keyGenerator = "postKeyGenerator")
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Map<String, Object> Post(@RequestBody LoginReqModel loginReqModel) {
        return cacheService.PostData(loginReqModel);
    }

    @CacheEvict(cacheNames = "softvider_cache", allEntries = true) // clear cache
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public Map<String, Object> ClearCache() {

//        List<CacheManager> tempManagers = CacheManager.ALL_CACHE_MANAGERS;
//        for (CacheManager tempCM : tempManagers) {
//            String[] cacheNames = tempCM.getCacheNames();
//            for (int i = 0; i < cacheNames.length; i++) {
//                String cacheName = cacheNames[i];
//                log.info(cacheName+" - "+ tempCM.getEhcache(cacheName).getKeys().size());
//                log.info(tempCM.getEhcache(cacheName).getKeys().toString());
//            }
//        }

        Map<String, Object> jsonString = new HashMap<>();
        jsonString.put("cache", "cleared");
        log.info("Response => {} :" +jsonString);
        return jsonString;
    }
}
