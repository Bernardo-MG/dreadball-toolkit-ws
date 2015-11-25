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
package com.wandrell.tabletop.dreadball.ws.toolkit.endpoint.unit;

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

import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.ws.toolkit.business.service.unit.AbilityDataService;
import com.wandrell.tabletop.dreadball.ws.toolkit.business.validation.ValidId;

/**
 * Web service resource for querying Dreadball abilities.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Path("/units/abilities")
@Service
public final class AbilityEndpoint {

    /**
     * Service being used by the resource to handle the {@code Ability}
     * instances.
     */
    private final AbilityDataService abilityService;

    /**
     * Constructs a {@code AbilityResource} with the specified service.
     * 
     * @param service
     *            the service to be used by the resource.
     */
    @Autowired
    public AbilityEndpoint(final AbilityDataService service) {
        super();

        abilityService = checkNotNull(service,
                "Received a null pointer as abilities service");
    }

    /**
     * Returns the abilities to be transformed into an HTML response.
     * <p>
     * These will be transformed into HTML by using the Freemarker template in
     * the path '/unit/ability/list-html'.
     * 
     * @return the abilities to be transformed into an HTML response through a
     *         Freemarker template
     */
    @GET
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/unit/ability/list-html")
    public final Collection<Ability> getAbilitiesHTML() {
        return getAbilityService().getAllAbilities();
    }

    /**
     * Returns the abilities to be transformed into a JSON response.
     * <p>
     * These will be transformed into JSON through the use of a JSON provider.
     * 
     * @return the abilities to be transformed into a JSON response through a
     *         JSON provider
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public final Collection<Ability> getAbilitiesJSON() {
        return getAbilityService().getAllAbilities();
    }

    /**
     * Returns a string with all the abilities.
     * 
     * @return a string with all the abilities
     */
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getAbilitiesText() {
        final Collection<Ability> abilities;
        final StringBuilder result;

        abilities = getAbilityService().getAllAbilities();

        result = new StringBuilder();
        for (final Ability ability : abilities) {
            result.append(ability.getName()).append('\n');
        }

        return result.toString();
    }

    /**
     * Returns the {@code Ability} with the specified id, to be transformed into
     * an HTML response.
     * <p>
     * This will be transformed into HTML by using the Freemarker template in
     * the path '/unit/ability/detail-html'.
     * 
     * @param id
     *            id of the queried {@code Ability}
     * @return {@code Ability} to transform into an HTML response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/unit/ability/detail-html")
    @ErrorTemplate(name = "/errors/404")
    public final Ability
            getAbilityHtml(@ValidId @PathParam("id") final String id) {
        return getAbilityService().getAbilityById(Integer.parseInt(id));
    }

    /**
     * Returns the {@code Ability} with the specified id, to be transformed into
     * a JSON response.
     * <p>
     * This will be transformed into JSON by using a JSON provider.
     * 
     * @param id
     *            id of the queried {@code Ability}
     * @return {@code Ability} to transform into a JSON response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public final Ability
            getAbilityJSON(@ValidId @PathParam("id") final String id) {
        return getAbilityService().getAbilityById(Integer.parseInt(id));
    }

    /**
     * Returns a string representing the {@code Ability} with the specified id.
     * 
     * @param id
     *            id of the queried {@code Ability}
     * @return string representing the queried {@code Ability}
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_PLAIN })
    public final String
            getAbilityText(@ValidId @PathParam("id") final String id) {
        final Ability ability;

        ability = getAbilityService().getAbilityById(Integer.parseInt(id));

        return ability.getName();
    }

    /**
     * Returns the service being used by the resource.
     * 
     * @return the service being used by the resource
     */
    private final AbilityDataService getAbilityService() {
        return abilityService;
    }

}
