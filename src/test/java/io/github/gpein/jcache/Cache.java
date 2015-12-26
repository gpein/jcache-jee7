package io.github.gpein.jcache;

import io.github.gpein.jcache.configuration.CacheConfiguration;

import javax.cache.CacheManager;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;


public class Cache {

    public static final String CACHE_NAME = "test cache";

    @Inject
    private CacheManager cacheManager;

    public void createCache() {
        cacheManager.createCache(CACHE_NAME, CacheConfiguration.<Long, String>newMutable(TimeUnit.MINUTES, 1));
    }
}
