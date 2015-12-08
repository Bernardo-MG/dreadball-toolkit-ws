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

package com.wandrell.tabletop.dreadball.ws.toolkit.endpoint.availability;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.dreadball.model.availability.asset.TeamTypeAssetsAvailability;
import com.wandrell.tabletop.dreadball.ws.toolkit.business.service.availability.TeamTypeAssetsAvailabilityDataService;
import com.wandrell.tabletop.dreadball.ws.toolkit.business.validation.ValidId;

/**
 * Web service resource for querying Dreadball assets availabilities.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Path("/teams/assets")
@Service
public final class TeamTypeAssetsAvailabilityEndpoint {

    /**
     * Service being used by the resource to handle the
     * {@code TeamTypeAssetsAvailability} instances.
     */
    private final TeamTypeAssetsAvailabilityDataService avaService;

    /**
     * Constructs a {@code TeamTypeAssetsAvailabilityResource} with the
     * specified service.
     * 
     * @param service
     *            the service to be used by the resource.
     */
    @Autowired
    public TeamTypeAssetsAvailabilityEndpoint(
            final TeamTypeAssetsAvailabilityDataService service) {
        super();

        avaService = checkNotNull(service,
                "Received a null pointer as assetss service");
    }

    /**
     * Returns the {@code TeamTypeAssetsAvailability} with the specified id, to
     * be transformed into an HTML response.
     * <p>
     * This will be transformed into HTML by using the Freemarker template in
     * the path '/team/asset/detail-html'.
     * 
     * @param id
     *            id of the queried {@code TeamTypeAssetsAvailability}
     * @return {@code TeamTypeAssetsAvailability} to transform into an HTML
     *         response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/team/asset/detail-html")
    @ErrorTemplate(name = "/errors/404")
    public final TeamTypeAssetsAvailability getTeamTypeAssetsAvailabilityHtml(
            @ValidId @PathParam("id") final String id) {
        return getTeamTypeAssetsAvailabilityService()
                .getTeamTypeAssetsAvailabilityById(Integer.parseInt(id));
    }

    /**
     * Returns the {@code TeamTypeAssetsAvailability} with the specified id, to
     * be transformed into a JSON response.
     * <p>
     * This will be transformed into JSON by using a JSON provider.
     * 
     * @param id
     *            id of the queried {@code TeamTypeAssetsAvailability}
     * @return {@code TeamTypeAssetsAvailability} to transform into a JSON
     *         response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public final TeamTypeAssetsAvailability getTeamTypeAssetsAvailabilityJSON(
            @ValidId @PathParam("id") final String id) {
        return getTeamTypeAssetsAvailabilityService()
                .getTeamTypeAssetsAvailabilityById(Integer.parseInt(id));
    }

    /**
     * Returns the assets availabilities to be transformed into an HTML
     * response.
     * <p>
     * These will be transformed into HTML by using the Freemarker template in
     * the path '/team/asset/list-html'.
     * 
     * @return the assets availabilities to be transformed into an HTML response
     *         through a Freemarker template
     */
    @GET
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/team/asset/list-html")
    public final Collection<TeamTypeAssetsAvailability>
            getTeamTypeAssetsAvailabilitysHTML() {
        return getTeamTypeAssetsAvailabilityService()
                .getAllTeamTypeAssetsAvailabilities();
    }

    /**
     * Returns the assets availabilities to be transformed into a JSON response.
     * <p>
     * These will be transformed into JSON through the use of a JSON provider.
     * 
     * @return the assets availabilities to be transformed into a JSON response
     *         through a JSON provider
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public final Collection<TeamTypeAssetsAvailability>
            getTeamTypeAssetsAvailabilitysJSON() {
        return getTeamTypeAssetsAvailabilityService()
                .getAllTeamTypeAssetsAvailabilities();
    }

    /**
     * Returns a string with all the assets availabilities.
     * 
     * @return a string with all the assets availabilities
     */
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getTeamTypeAssetsAvailabilitysText() {
        final Collection<TeamTypeAssetsAvailability> avas;
        final StringBuilder result;

        avas = getTeamTypeAssetsAvailabilityService()
                .getAllTeamTypeAssetsAvailabilities();

        result = new StringBuilder();
        for (final TeamTypeAssetsAvailability ava : avas) {
            // TODO: Extend to show all data
            result.append(ava.getTeamType().getName()).append('\n');
        }

        return result.toString();
    }

    /**
     * Returns a string representing the {@code TeamTypeAssetsAvailability} with
     * the specified id.
     * 
     * @param id
     *            id of the queried {@code TeamTypeAssetsAvailability}
     * @return string representing the queried
     *         {@code TeamTypeAssetsAvailability}
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getTeamTypeAssetsAvailabilityText(
            @ValidId @PathParam("id") final String id) {
        final TeamTypeAssetsAvailability ava;

        ava = getTeamTypeAssetsAvailabilityService()
                .getTeamTypeAssetsAvailabilityById(Integer.parseInt(id));

        // TODO: Extend to show all data
        return String.format("%s", ava.getTeamType().getName());
    }

    /**
     * Returns the service being used by the resource.
     * 
     * @return the service being used by the resource
     */
    private final TeamTypeAssetsAvailabilityDataService
            getTeamTypeAssetsAvailabilityService() {
        return avaService;
    }

}
