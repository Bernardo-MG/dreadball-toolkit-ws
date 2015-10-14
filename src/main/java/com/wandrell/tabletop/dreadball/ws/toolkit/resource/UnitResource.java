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
package com.wandrell.tabletop.dreadball.ws.toolkit.resource;

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

import com.wandrell.tabletop.dreadball.model.unit.Unit;
import com.wandrell.tabletop.dreadball.ws.toolkit.service.UnitAccessService;
import com.wandrell.tabletop.dreadball.ws.toolkit.validation.ValidId;

/**
 * Web service resource for querying Dreadball units.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Path("/units/dbo")
@Service
public final class UnitResource {

    /**
     * Service being used by the resource to handle the {@code Unit} instances.
     */
    private final UnitAccessService unitService;

    /**
     * Constructs a {@code UnitResource} with the specified service.
     * 
     * @param service
     *            the service to be used by the resource.
     */
    @Autowired
    public UnitResource(final UnitAccessService service) {
        super();

        unitService = checkNotNull(service,
                "Received a null pointer as units service");
    }

    /**
     * Returns the {@code Unit} with the specified id, to be transformed into an
     * HTML response.
     * <p>
     * This will be transformed into HTML by using the Freemarker template in
     * the path '/unit/dbo/detail-html'.
     * 
     * @param id
     *            id of the queried {@code Unit}
     * @return {@code Unit} to transform into an HTML response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/unit/dbo/detail-html")
    @ErrorTemplate(name = "/errors/404")
    public final Unit getUnitHtml(@ValidId @PathParam("id") final String id) {
        return getUnitService().getUnitById(Integer.parseInt(id));
    }

    /**
     * Returns the {@code Unit} with the specified id, to be transformed into a
     * JSON response.
     * <p>
     * This will be transformed into JSON by using a JSON provider.
     * 
     * @param id
     *            id of the queried {@code Unit}
     * @return {@code Unit} to transform into a JSON response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public final Unit getUnitJSON(@ValidId @PathParam("id") final String id) {
        return getUnitService().getUnitById(Integer.parseInt(id));
    }

    /**
     * Returns the units to be transformed into an HTML response.
     * <p>
     * These will be transformed into HTML by using the Freemarker template in
     * the path '/unit/dbo/list-html'.
     * 
     * @return the units to be transformed into an HTML response through a
     *         Freemarker template
     */
    @GET
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/unit/dbo/list-html")
    public final Collection<Unit> getUnitsHTML() {
        return getUnitService().getAllUnits();
    }

    /**
     * Returns the units to be transformed into a JSON response.
     * <p>
     * These will be transformed into JSON through the use of a JSON provider.
     * 
     * @return the units to be transformed into a JSON response through a JSON
     *         provider
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public final Collection<Unit> getUnitsJSON() {
        return getUnitService().getAllUnits();
    }

    /**
     * Returns a string with all the units.
     * 
     * @return a string with all the units
     */
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getUnitsText() {
        final Collection<Unit> units;
        final StringBuilder result;

        units = getUnitService().getAllUnits();

        result = new StringBuilder();
        for (final Unit unit : units) {
            result.append(unit.getTemplateName()).append('\n');
        }

        return result.toString();
    }

    /**
     * Returns a string representing the {@code Unit} with the specified id.
     * 
     * @param id
     *            id of the queried {@code Unit}
     * @return string representing the queried {@code Unit}
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getUnitText(@ValidId @PathParam("id") final String id) {
        final Unit unit;

        unit = getUnitService().getUnitById(Integer.parseInt(id));

        return unit.getTemplateName();
    }

    /**
     * Returns the service being used by the resource.
     * 
     * @return the service being used by the resource
     */
    private final UnitAccessService getUnitService() {
        return unitService;
    }

}
