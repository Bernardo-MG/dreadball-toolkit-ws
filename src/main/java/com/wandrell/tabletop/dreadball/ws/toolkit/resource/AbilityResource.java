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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wandrell.tabletop.dreadball.model.json.unit.stats.AbilityMixIn;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.ws.toolkit.service.AbilityService;

@Component
@Path("/ability")
public class AbilityResource {

    private final AbilityService service;

    @Autowired
    public AbilityResource(final AbilityService service) {
        super();

        checkNotNull(service, "Received a null pointer as abilities service");

        this.service = service;
    }

    @GET
    @Produces({ MediaType.TEXT_HTML })
    public final Response getAbilitiesHTML() {
        final Collection<Ability> abilities;
        final String title;
        final StringBuilder table;
        final StringBuilder html;

        title = "Abilities list";

        html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("\n");
        html.append(
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\">");
        html.append("\n");
        html.append("<head>");
        html.append("\n");
        html.append(
                "<meta http-equiv=\"Content-Type\" content=\"text/html); charset=utf-8\"/>");
        html.append("\n");
        html.append("<meta charset='utf-8'>");
        html.append("\n");
        html.append("<title>" + title + "</title>");
        html.append("\n");
        html.append("</head>");

        abilities = getAbilityService().getAllAbilities();

        table = new StringBuilder();
        table.append("<table border=\"1\">");
        table.append("\n");
        table.append("<thead>");
        table.append("\n");
        table.append("<tr>");
        table.append("\n");
        table.append("<th>Name</th>");
        table.append("\n");
        table.append("</tr>");
        table.append("\n");
        table.append("</thead>");
        for (final Ability ability : abilities) {
            table.append("\n");
            table.append("<tr>");
            table.append("\n");
            table.append("<td>" + ability.getAbilityName() + "</td>");
            table.append("\n");
            table.append("</tr>");
        }
        table.append("</table>");

        html.append("\n");
        html.append("<body>");
        html.append("\n");
        html.append("<h1>" + title + "</h1>");
        html.append("\n");
        html.append(table);
        html.append("\n");
        html.append("</body>");

        return Response.ok().entity(html.toString()).build();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public final Response getAbilitiesJSON() throws JsonProcessingException {
        final ObjectMapper mapper;
        final Collection<Ability> abilities;

        mapper = new ObjectMapper();
        mapper.addMixIn(Ability.class, AbilityMixIn.class);

        abilities = getAbilityService().getAllAbilities();

        return Response.ok().entity(mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(abilities)).build();
    }

    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public final Response getAbilitiesText() {
        final Collection<Ability> abilities;
        final StringBuilder result;

        abilities = getAbilityService().getAllAbilities();

        result = new StringBuilder();
        for (final Ability ability : abilities) {
            if (result.length() > 0) {
                result.append("\n");
            }
            result.append(ability.getAbilityName());
        }

        return Response.ok().entity(result.toString()).build();
    }

    private final AbilityService getAbilityService() {
        return service;
    }

}
