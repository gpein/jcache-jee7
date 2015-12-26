/**
 * Copyright (C) 2015 Guillaume Pein <guillaume.pein@gmail.com>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.gpein.jcache.cdi;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Allow easy injection of javax.cache main API
 */
public class JCacheProducers {

    /**
     * Allow @Inject CacheManager
     * @return static instance of CacheManager
     */
    @Produces
    public CacheManager produceCacheManager() {
        return Caching.getCachingProvider().getCacheManager();
    }

    /**
     * Allow @Inject of Cache identified by its unique name
     * @param ip to retrieve cache name
     * @param <K> Key type
     * @param <V> Value type
     * @return unique instance of requested named cache
     * @throws IllegalStateException if cache is not found in current context
     */
    @Produces
    @NamedJCache
    public <K, V> Cache<K, V> produceCache(InjectionPoint ip) {

        String cacheName = ip.getAnnotated().getAnnotation(NamedJCache.class).name();
        Cache<K, V> cache = produceCacheManager().getCache(cacheName);

        if (cache == null)
            throw new IllegalStateException("Cannot @Produces cache : Named JCache '" + cacheName + "' does not exists. Make sure you created it at startup");

        return cache;
    }
}
