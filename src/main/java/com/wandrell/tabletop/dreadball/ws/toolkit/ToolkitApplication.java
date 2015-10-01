/**
 * Copyright 2015 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wandrell.tabletop.dreadball.ws.toolkit;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.beanvalidation.MvcBeanValidationFeature;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

@ApplicationPath("/")
public final class ToolkitApplication extends ResourceConfig {

    public ToolkitApplication() {
        super();

        // Spring configuration
        register(RequestContextFilter.class);

        // Resource packages
        packages("com.wandrell.tabletop.dreadball.ws.toolkit.resource");

        // Propagate validation errors to client
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Enable MVC FreeMarker templating engine
        register(FreemarkerMvcFeature.class);
        register(MvcBeanValidationFeature.class);
    }

}
