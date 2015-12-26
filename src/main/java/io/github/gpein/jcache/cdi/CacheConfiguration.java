package io.github.gpein.jcache.cdi;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

import static javax.cache.expiry.AccessedExpiryPolicy.factoryOf;

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
public class CacheConfiguration {

    public static <K, V> MutableConfiguration newMutable(TimeUnit expiryTimeUnit, long expiryDurationAmount) {
        return new MutableConfiguration<K, V>().setExpiryPolicyFactory(factoryOf(new Duration(expiryTimeUnit, expiryDurationAmount)));
    }
}
