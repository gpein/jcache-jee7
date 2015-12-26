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

/**
 * REST representation of a cache statistics
 */
public class CacheStatistics {

    private Long hits;
    private Long misses;
    private Float hitPercentage;
    private Float missPercentage;
    private Long gets;
    private Long puts;
    private Long removals;
    private Long evictions;
    private Float averageGetTime;
    private Float averagePutTime;
    private Float averageRemoveTime;

    public CacheStatistics(Long hits, Long misses, Float hitPercentage, Float missPercentage, Long gets, Long puts, Long removals, Long evictions, Float averageGetTime, Float averagePutTime, Float averageRemoveTime) {
        this();
        this.hits = hits;
        this.misses = misses;
        this.hitPercentage = hitPercentage;
        this.missPercentage = missPercentage;
        this.gets = gets;
        this.puts = puts;
        this.removals = removals;
        this.evictions = evictions;
        this.averageGetTime = averageGetTime;
        this.averagePutTime = averagePutTime;
        this.averageRemoveTime = averageRemoveTime;
    }

    public CacheStatistics() {
    }

    public Long getHits() {
        return hits;
    }

    public void setHits(Long hits) {
        this.hits = hits;
    }

    public Long getMisses() {
        return misses;
    }

    public void setMisses(Long misses) {
        this.misses = misses;
    }

    public Float getHitPercentage() {
        return hitPercentage;
    }

    public void setHitPercentage(Float hitPercentage) {
        this.hitPercentage = hitPercentage;
    }

    public Float getMissPercentage() {
        return missPercentage;
    }

    public void setMissPercentage(Float missPercentage) {
        this.missPercentage = missPercentage;
    }

    public Long getGets() {
        return gets;
    }

    public void setGets(Long gets) {
        this.gets = gets;
    }

    public Long getPuts() {
        return puts;
    }

    public void setPuts(Long puts) {
        this.puts = puts;
    }

    public Long getRemovals() {
        return removals;
    }

    public void setRemovals(Long removals) {
        this.removals = removals;
    }

    public Long getEvictions() {
        return evictions;
    }

    public void setEvictions(Long evictions) {
        this.evictions = evictions;
    }

    public Float getAverageGetTime() {
        return averageGetTime;
    }

    public void setAverageGetTime(Float averageGetTime) {
        this.averageGetTime = averageGetTime;
    }

    public Float getAveragePutTime() {
        return averagePutTime;
    }

    public void setAveragePutTime(Float averagePutTime) {
        this.averagePutTime = averagePutTime;
    }

    public Float getAverageRemoveTime() {
        return averageRemoveTime;
    }

    public void setAverageRemoveTime(Float averageRemoveTime) {
        this.averageRemoveTime = averageRemoveTime;
    }
}
