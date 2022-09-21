package com.ruoyi.system.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeatherService {
    @Value("${weather-url}")
    String url;

    private final Map<String, Map> caches = new ConcurrentHashMap<>(); // 缓存key
    private final Map<String, Long> cacheTime = new ConcurrentHashMap<>(); // 缓存时间
    // 最多缓存20个城市的数据(每个城市两个api)
    private final int MAX_SIZE = 40;
    private final Vector<String> keys = new Vector<>(); //线程安全
    private final long ttl = 1800000;//缓存30分钟

    public Map getWeather(Map<String, Object> params) {
        String city = (String) params.get("city");
        if (StringUtils.hasText(city)) {
            String version = (String) params.get("version");
            String key = version + city;
            Map cache = caches.get(key);
            if (cache == null) {
                return getData(params, key);
            } else {
                if (System.currentTimeMillis() - cacheTime.get(key) < ttl) {
                    return cache;
                }
                return getData(params, key);
            }
        }
        return getData(params, null);
    }

    private Map getData(Map<String, Object> params, String key) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder apiBuilder = new StringBuilder(url);
        params.forEach((k, v) -> apiBuilder.append("&").append(k).append("=").append(v));
        Map result = restTemplate.getForObject(apiBuilder.toString(), Map.class);
        if (keys.size() == MAX_SIZE) { // 缓存达到上线，清理一个缓存并缓存最新的一个
            caches.remove(keys.get(0));
            caches.remove(keys.get(0));
            keys.remove(0);
        }
        if (key != null) { // key不为null时缓存
            caches.put(key, result);
            Long createTime = System.currentTimeMillis();
            cacheTime.put(key, createTime);
        }
        return result;
    }

}
