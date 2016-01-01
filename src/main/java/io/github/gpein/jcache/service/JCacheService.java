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
package io.github.gpein.jcache.service;

import com.hazelcast.cache.ICache;
import io.github.gpein.jcache.interfaces.rest.model.Cache;
import io.github.gpein.jcache.interfaces.rest.model.CacheStatistics;

import javax.cache.CacheManager;
import javax.cache.configuration.CompleteConfiguration;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Provides a set of basic utilities for interacting with cache manager
 */
public class JCacheService {

    @Inject
    private CacheManager cacheManager;

    /**
     * @param cacheName name of cache
     * @return true if  cache exists, false otherwise
     */
    public boolean isPresent(String cacheName) {
        return cacheManager.getCache(cacheName) != null;
    }

    /**
     * Update mutable information of cache configuration such as management and statistics support
     *
     * @param cacheName name of cache to update
     * @param cache     containing new values
     */
    public void update(String cacheName, Cache cache) {
        cacheManager.enableManagement(cacheName, cache.isManagementEnabled());
        cacheManager.enableStatistics(cacheName, cache.isStatisticsEnabled());
    }

    /**
     * @return a more detailed view of all caches currently running
     */
    public Collection<Cache> allWithStatistics() {
        Collection<Cache> all = all();
        all.stream().forEach(cache -> cache.setStatistics(getStatistics(cache.getName()).orElse(null)));
        return all;
    }

    /**
     * @return a basic overview of all caches currently running
     */
    public Collection<Cache> all() {
        return StreamSupport.stream(cacheManager.getCacheNames().spliterator(), false)
                .map(cacheName -> {
                    CompleteConfiguration configuration = cacheManager.getCache(cacheName).getConfiguration(CompleteConfiguration.class);
                    Cache cache = new Cache();
                    cache.setName(cacheName);
                    cache.setManagementEnabled(configuration.isManagementEnabled());
                    cache.setStatisticsEnabled(configuration.isStatisticsEnabled());
                    return cache;
                })
                .collect(Collectors.toList());
    }

    /**
     * Get a cache statistic information if available
     *
     * @param cacheName name of cache
     * @return cache statistics or <code>Optional.empty()</code> if either cache does exist or does not provide statistics datas
     */
    public Optional<CacheStatistics> getStatistics(String cacheName) {

        javax.cache.Cache cache = cacheManager.getCache(cacheName);

        if (cache == null) {
            return Optional.empty();
        }

        if (((CompleteConfiguration) cache.getConfiguration(CompleteConfiguration.class)).isStatisticsEnabled() && cache instanceof ICache) {
            com.hazelcast.cache.CacheStatistics stats = ((ICache) cache).getLocalCacheStatistics();
            CacheStatistics statistics = new CacheStatistics(
                    stats.getCacheHits(),
                    stats.getCacheMisses(),
                    stats.getCacheHitPercentage(),
                    stats.getCacheMissPercentage(),
                    stats.getCacheGets(),
                    stats.getCachePuts(),
                    stats.getCacheRemovals(),
                    stats.getCacheEvictions(),
                    stats.getAverageGetTime(),
                    stats.getAveragePutTime(),
                    stats.getAverageRemoveTime());

            return Optional.of(statistics);
        }

        return Optional.empty();
    }
}
