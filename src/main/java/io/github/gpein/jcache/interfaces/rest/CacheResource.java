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

import io.github.gpein.jcache.interfaces.rest.model.Cache;
import io.github.gpein.jcache.service.JCacheService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST end point for single cache management
 */
@Path("caches/{cacheName}")
public class CacheResource {

    @Inject
    private JCacheService cacheService;

    /**
     * Modify state of a single cache
     *
     * @param cacheName name of cache to modify
     * @param cache     data to modify
     * @return response with status code 200
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("cacheName") String cacheName, Cache cache) {

        if (!cacheService.isPresent(cacheName)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        cacheService.update(cacheName, cache);

        return Response.ok().build();
    }
}
