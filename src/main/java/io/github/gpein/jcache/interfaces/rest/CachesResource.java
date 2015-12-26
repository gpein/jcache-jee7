package io.github.gpein.jcache.interfaces.rest;

import javax.cache.CacheManager;
import javax.cache.configuration.CompleteConfiguration;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
@Path("caches")
public class CachesResource {

    @Inject
    private CacheManager cacheManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RestCache> get() {
        return StreamSupport.
                stream(cacheManager.getCacheNames().spliterator(), false)
                .map(cacheName -> {
                    CompleteConfiguration configuration = cacheManager.getCache(cacheName).getConfiguration(CompleteConfiguration.class);
                    RestCache cache = new RestCache();
                    cache.setName(cacheName);
                    cache.setManagementEnabled(configuration.isManagementEnabled());
                    cache.setStatisticsEnabled(configuration.isStatisticsEnabled());
                    return cache;
                })
                .collect(Collectors.toList());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(RestCache cacheToUpdate) {

        String cacheName = cacheToUpdate.getName();

        if (cacheManager.getCache(cacheName) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        cacheManager.enableManagement(cacheName, cacheToUpdate.isManagementEnabled());
        cacheManager.enableStatistics(cacheName, cacheToUpdate.isStatisticsEnabled());

        return Response.ok().build();
    }
}
