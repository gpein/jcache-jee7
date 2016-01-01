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
package io.github.gpein.jcache.interfaces.rest;

import io.github.gpein.jcache.interfaces.rest.model.CacheStatistics;
import io.github.gpein.jcache.service.JCacheService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * REST end point for getting a specific cache statistics
 */
@Path("caches/{cacheName}/statistics")
public class CacheStatisticsResource {

    @Inject
    private JCacheService cacheService;

    /**
     * @param cacheName name of cache
     * @return jcache use stastitics for this cache or 404 if not found
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("cacheName") String cacheName) {

        Optional<CacheStatistics> statistics = cacheService.getStatistics(cacheName);

        if (statistics.isPresent()) {
            return Response.ok(statistics.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
