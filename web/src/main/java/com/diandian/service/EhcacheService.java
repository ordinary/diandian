package com.diandian.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by zhutao on 14/9/26.
 */
public class EhcacheService {

    public final static String ACCESS_TOKEN_CACHE_KEY="accessToken";
    public final static String ACCESS_TOKEN_KEY="accessTokenKey";



    public static Object getCache(String cacheKey,String elementKey){
        CacheManager cacheManager = CacheManager.create();
        Cache accessTokenCache = cacheManager.getCache(cacheKey);
        if (accessTokenCache==null){
            return null;
        }
        Element element= accessTokenCache.get(elementKey);
        if (element==null){
            return null;
        }
        return  element.getObjectValue();
    }

    public static void addCache(String cacheKey,String elementKey,String elementValue,int expire){
        CacheManager cacheManager = CacheManager.create();
        Cache memoryOnlyCache = new Cache(cacheKey, 5000, false, false, 0, 0);
        Element element=new Element(elementKey,elementValue,expire,expire);
        memoryOnlyCache.put(element);
        cacheManager.addCache(memoryOnlyCache);
    }
}
