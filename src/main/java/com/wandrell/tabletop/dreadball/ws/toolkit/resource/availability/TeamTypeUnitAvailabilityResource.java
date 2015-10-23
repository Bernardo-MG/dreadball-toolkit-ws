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
package com.wandrell.tabletop.dreadball.ws.toolkit.resource.availability;

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

import com.wandrell.tabletop.dreadball.model.availability.unit.TeamTypeUnitAvailability;
import com.wandrell.tabletop.dreadball.ws.toolkit.service.availability.TeamTypeUnitAvailabilityDataService;
import com.wandrell.tabletop.dreadball.ws.toolkit.validation.ValidId;

/**
 * Web service resource for querying Dreadball unit availabilities.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Path("/teams/units")
@Service
public final class TeamTypeUnitAvailabilityResource {

    /**
     * Service being used by the resource to handle the
     * {@code TeamTypeUnitAvailability} instances.
     */
    private final TeamTypeUnitAvailabilityDataService avaService;

    /**
     * Constructs a {@code TeamTypeUnitAvailabilityResource} with the specified
     * service.
     * 
     * @param service
     *            the service to be used by the resource.
     */
    @Autowired
    public TeamTypeUnitAvailabilityResource(
            final TeamTypeUnitAvailabilityDataService service) {
        super();

        avaService = checkNotNull(service,
                "Received a null pointer as units service");
    }

    /**
     * Returns the {@code TeamTypeUnitAvailability} with the specified id, to be
     * transformed into an HTML response.
     * <p>
     * This will be transformed into HTML by using the Freemarker template in
     * the path '/team/unit/detail-html'.
     * 
     * @param id
     *            id of the queried {@code TeamTypeUnitAvailability}
     * @return {@code TeamTypeUnitAvailability} to transform into an HTML
     *         response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/team/unit/detail-html")
    @ErrorTemplate(name = "/errors/404")
    public final TeamTypeUnitAvailability getTeamTypeUnitAvailabilityHtml(
            @ValidId @PathParam("id") final String id) {
        return getTeamTypeUnitAvailabilityService()
                .getTeamTypeUnitAvailabilityById(Integer.parseInt(id));
    }

    /**
     * Returns the {@code TeamTypeUnitAvailability} with the specified id, to be
     * transformed into a JSON response.
     * <p>
     * This will be transformed into JSON by using a JSON provider.
     * 
     * @param id
     *            id of the queried {@code TeamTypeUnitAvailability}
     * @return {@code TeamTypeUnitAvailability} to transform into a JSON
     *         response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public final TeamTypeUnitAvailability getTeamTypeUnitAvailabilityJSON(
            @ValidId @PathParam("id") final String id) {
        return getTeamTypeUnitAvailabilityService()
                .getTeamTypeUnitAvailabilityById(Integer.parseInt(id));
    }

    /**
     * Returns the unit availabilities to be transformed into an HTML response.
     * <p>
     * These will be transformed into HTML by using the Freemarker template in
     * the path '/team/unit/list-html'.
     * 
     * @return the unit availabilities to be transformed into an HTML response
     *         through a Freemarker template
     */
    @GET
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/team/unit/list-html")
    public final Collection<TeamTypeUnitAvailability>
            getTeamTypeUnitAvailabilitysHTML() {
        return getTeamTypeUnitAvailabilityService()
                .getAllTeamTypeUnitAvailabilities();
    }

    /**
     * Returns the unit availabilities to be transformed into a JSON response.
     * <p>
     * These will be transformed into JSON through the use of a JSON provider.
     * 
     * @return the unit availabilities to be transformed into a JSON response
     *         through a JSON provider
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public final Collection<TeamTypeUnitAvailability>
            getTeamTypeUnitAvailabilitysJSON() {
        return getTeamTypeUnitAvailabilityService()
                .getAllTeamTypeUnitAvailabilities();
    }

    /**
     * Returns a string with all the unit availabilities.
     * 
     * @return a string with all the unit availabilities
     */
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getTeamTypeUnitAvailabilitysText() {
        final Collection<TeamTypeUnitAvailability> avas;
        final StringBuilder result;

        avas = getTeamTypeUnitAvailabilityService()
                .getAllTeamTypeUnitAvailabilities();

        result = new StringBuilder();
        for (final TeamTypeUnitAvailability ava : avas) {
            result.append(ava.getTeamType().getName()).append(" - ")
                    .append(ava.getUnit().getTemplateName()).append('\n');
        }

        return result.toString();
    }

    /**
     * Returns a string representing the {@code TeamTypeUnitAvailability} with
     * the specified id.
     * 
     * @param id
     *            id of the queried {@code TeamTypeUnitAvailability}
     * @return string representing the queried {@code TeamTypeUnitAvailability}
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getTeamTypeUnitAvailabilityText(
            @ValidId @PathParam("id") final String id) {
        final TeamTypeUnitAvailability ava;

        ava = getTeamTypeUnitAvailabilityService()
                .getTeamTypeUnitAvailabilityById(Integer.parseInt(id));

        return String.format("%s - %s", ava.getTeamType().getName(),
                ava.getUnit().getTemplateName());
    }

    /**
     * Returns the service being used by the resource.
     * 
     * @return the service being used by the resource
     */
    private final TeamTypeUnitAvailabilityDataService
            getTeamTypeUnitAvailabilityService() {
        return avaService;
    }

}
