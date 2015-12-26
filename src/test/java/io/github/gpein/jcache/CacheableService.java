package io.github.gpein.jcache;

import io.github.gpein.jcache.cdi.NamedJCache;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import javax.enterprise.event.Event;
import javax.inject.Inject;


@CacheDefaults(cacheName = Cache.CACHE_NAME)
public class CacheableService {

    @Inject
    private Event<Call> callCounterEvent;

    @Inject @NamedJCache(name = Cache.CACHE_NAME)
    private javax.cache.Cache<Long, String> cache;

    @CacheResult
    public String get(@CacheKey Long id, String irrelevantParameter) {
        callCounterEvent.fire(new Call());
        return id + " " + irrelevantParameter;
    }

    @CacheRemove
    public void update(@CacheKey Long id, String irrelevantParameter) {

    }

    public void removeAll() {
        cache.removeAll();
    }
}
