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
package io.github.gpein.jcache.interfaces.model;

import io.github.gpein.jcache.interfaces.rest.model.Cache;
import io.github.gpein.jcache.service.JCacheService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Model for a set of caches in the MVC paradigm
 */
@Model
public class Caches {

    @Inject
    private JCacheService cacheService;

    public Collection<Cache> getAllWithStatistics() {
        return cacheService.allWithStatistics();
    }
}
