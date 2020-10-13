package com.softvider.controller;

import com.softvider.service.RedisCacheService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cache/redis")
public class RedisCacheController {

    RedisCacheService cacheService = new RedisCacheService();

    @RequestMapping(value = "/set/{key}/{value}", method = RequestMethod.GET)
    public String Home(@PathVariable(value="key") String key, @PathVariable(value="value") String value) {
        return cacheService.Home(key, value);
    }
}
