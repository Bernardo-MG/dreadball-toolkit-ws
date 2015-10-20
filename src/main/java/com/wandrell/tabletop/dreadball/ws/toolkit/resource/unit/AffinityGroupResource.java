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
package com.wandrell.tabletop.dreadball.ws.toolkit.resource.unit;

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

import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;
import com.wandrell.tabletop.dreadball.ws.toolkit.service.unit.AffinityGroupDataService;
import com.wandrell.tabletop.dreadball.ws.toolkit.validation.ValidId;

/**
 * Web service resource for querying Dreadball affinity groups.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Path("/affinities")
@Service
public final class AffinityGroupResource {

    /**
     * Service being used by the resource to handle the {@code AffinityGroup}
     * instances.
     */
    private final AffinityGroupDataService affinitiesService;

    /**
     * Constructs a {@code AffinityGroupResource} with the specified service.
     * 
     * @param service
     *            the service to be used by the resource.
     */
    @Autowired
    public AffinityGroupResource(final AffinityGroupDataService service) {
        super();

        affinitiesService = checkNotNull(service,
                "Received a null pointer as affinity groups service");
    }

    /**
     * Returns the {@code AffinityGroup} with the specified id, to be
     * transformed into an HTML response.
     * <p>
     * This will be transformed into HTML by using the Freemarker template in
     * the path '/affinity/detail-html'.
     * 
     * @param id
     *            id of the queried {@code Ability}
     * @return {@code Ability} to transform into an HTML response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/affinity/detail-html")
    @ErrorTemplate(name = "/errors/404")
    public final AffinityGroup
            getAffinityGroupHtml(@ValidId @PathParam("id") final String id) {
        return getAffinityGroupService()
                .getAffinityGroupById(Integer.parseInt(id));
    }

    /**
     * Returns the {@code AffinityGroup} with the specified id, to be
     * transformed into a JSON response.
     * <p>
     * This will be transformed into JSON by using a JSON provider.
     * 
     * @param id
     *            id of the queried {@code AffinityGroup}
     * @return {@code AffinityGroup} to transform into a JSON response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public final AffinityGroup
            getAffinityGroupJSON(@ValidId @PathParam("id") final String id) {
        return getAffinityGroupService()
                .getAffinityGroupById(Integer.parseInt(id));
    }

    /**
     * Returns the affinity groups to be transformed into an HTML response.
     * <p>
     * These will be transformed into HTML by using the Freemarker template in
     * the path '/affinity/list-html'.
     * 
     * @return the affinity groups to be transformed into an HTML response
     *         through a Freemarker template
     */
    @GET
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/affinity/list-html")
    public final Collection<AffinityGroup> getAffinityGroupsHTML() {
        return getAffinityGroupService().getAllAffinityGroups();
    }

    /**
     * Returns the affinity groups to be transformed into a JSON response.
     * <p>
     * These will be transformed into JSON through the use of a JSON provider.
     * 
     * @return the affinity groups to be transformed into a JSON response
     *         through a JSON provider
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public final Collection<AffinityGroup> getAffinityGroupsJSON() {
        return getAffinityGroupService().getAllAffinityGroups();
    }

    /**
     * Returns a string with all the affinity groups.
     * 
     * @return a string with all the affinity groups
     */
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getAffinityGroupsText() {
        final Collection<AffinityGroup> affinities;
        final StringBuilder result;

        affinities = getAffinityGroupService().getAllAffinityGroups();

        result = new StringBuilder();
        for (final AffinityGroup affinity : affinities) {
            result.append(affinity.getAffinityGroupName()).append('\n');
        }

        return result.toString();
    }

    /**
     * Returns a string representing the {@code AffinityGroup} with the
     * specified id.
     * 
     * @param id
     *            id of the queried {@code AffinityGroup}
     * @return string representing the queried {@code AffinityGroup}
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_PLAIN })
    public final String
            getAffinityGroupText(@ValidId @PathParam("id") final String id) {
        final AffinityGroup affinity;

        affinity = getAffinityGroupService()
                .getAffinityGroupById(Integer.parseInt(id));

        return affinity.getAffinityGroupName();
    }

    /**
     * Returns the service being used by the resource.
     * 
     * @return the service being used by the resource
     */
    private final AffinityGroupDataService getAffinityGroupService() {
        return affinitiesService;
    }

}
