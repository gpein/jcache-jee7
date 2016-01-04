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


import io.github.gpein.jcache.interfaces.rest.model.Cache;
import io.github.gpein.jcache.service.JCacheService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * A basic JSP controller
 */
@WebServlet(urlPatterns = "/jcache")
public class CacheManagementServlet extends HttpServlet {

    @Inject
    private JCacheService cacheService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("statistics", cacheService.all().stream().map(Cache::isStatisticsEnabled).reduce((b1, b2) -> b1 && b2).orElse(Boolean.FALSE));
        request.getRequestDispatcher("/jcache.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Optional<String> statistics = Optional.ofNullable(request.getParameter("statistics"));
        statistics.ifPresent(enabled -> cacheService.setStatistics(Boolean.valueOf(enabled)));

        response.setContentType("text/html");
        request.setAttribute("statistics", statistics.orElse("false"));
        request.getRequestDispatcher("/jcache.jsp").forward(request, response);
    }
}
