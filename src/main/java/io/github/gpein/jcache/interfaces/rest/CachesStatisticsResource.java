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
import io.github.gpein.jcache.interfaces.rest.model.CachesStatistics;
import io.github.gpein.jcache.service.JCacheService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST end point for operations on all caches
 */
@Path("caches-statistics")
public class CachesStatisticsResource {

    @Inject
    private JCacheService cacheService;

    /**
     * @return change statistics activation for all caches
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(CachesStatistics cachesStatistics) {
        cacheService.all().forEach(cache -> cacheService.update(cache.getName(), new Cache(cache.isManagementEnabled(), cachesStatistics.isEnabled())));
        return Response.ok().build();
    }
}
