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
package com.wandrell.tabletop.dreadball.ws.toolkit.endpoint;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

/**
 * Web service resource for querying the application version.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Path("/version")
@Service
public final class VersionInfoEndpoint {

    /**
     * Constructs a {@code VersionInfoResource}.
     */
    public VersionInfoEndpoint() {
        super();
    }

    /**
     * Returns the web service version as a plain text.
     * 
     * @return the web service version as a plain text
     */
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getVersion() {
        return "test";
    }

}
