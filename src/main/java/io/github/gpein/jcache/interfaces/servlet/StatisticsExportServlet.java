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
package io.github.gpein.jcache.interfaces.servlet;


import io.github.gpein.jcache.interfaces.rest.model.CacheStatistics;
import io.github.gpein.jcache.service.JCacheService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A basic JSP controller for CSV export
 */
@WebServlet(urlPatterns = "/jcache-stats-export")
public class StatisticsExportServlet extends HttpServlet {

    @Inject
    private JCacheService cacheService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OutputStream outputStream = response.getOutputStream();
        String delimiter = ";";
        final StringBuilder csv = new StringBuilder("name;hits;misses;hitPercentage;missPercentage;gets;puts;removals;evictions;averageGetTime;averagePutTime;averageRemoveTime\n");

        cacheService.allWithStatistics().stream().forEach(cache -> {
            CacheStatistics stats = cache.getStatistics();
            csv.append(cache.getName()).append(delimiter);
            if (stats != null) {
                csv.append(stats.getHits()).append(delimiter).append(stats.getMisses()).append(delimiter).append(stats.getHitPercentage()).append(delimiter).append(stats.getMissPercentage()).append(delimiter).append(stats.getGets()).append(delimiter).append(stats.getPuts()).append(delimiter).append(stats.getRemovals()).append(delimiter).append(stats.getEvictions()).append(delimiter).append(stats.getAverageGetTime()).append(delimiter).append(stats.getAveragePutTime()).append(delimiter).append(stats.getAverageRemoveTime());
            }
            csv.append("\n");
        });

        outputStream.write(csv.toString().getBytes());

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + request.getContextPath().replaceAll("/", "") + "-jcache-statistics-" + new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date()) + ".csv");
        outputStream.flush();
        outputStream.close();
    }
}
