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
package com.wandrell.tabletop.dreadball.ws.toolkit.resource.faction;

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

import com.wandrell.tabletop.dreadball.model.faction.TeamRule;
import com.wandrell.tabletop.dreadball.ws.toolkit.service.faction.TeamRuleAccessService;
import com.wandrell.tabletop.dreadball.ws.toolkit.validation.ValidId;

/**
 * Web service resource for querying Dreadball team rules.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Path("/teams/rules")
@Service
public final class TeamRuleResource {

    /**
     * Service being used by the resource to handle the {@code TeamRule}
     * instances.
     */
    private final TeamRuleAccessService ruleService;

    /**
     * Constructs a {@code TeamRuleResource} with the specified service.
     * 
     * @param service
     *            the service to be used by the resource.
     */
    @Autowired
    public TeamRuleResource(final TeamRuleAccessService service) {
        super();

        ruleService = checkNotNull(service,
                "Received a null pointer as team rules service");
    }

    /**
     * Returns the {@code TeamRule} with the specified id, to be transformed
     * into an HTML response.
     * <p>
     * This will be transformed into HTML by using the Freemarker template in
     * the path '/team/rule/detail-html'.
     * 
     * @param id
     *            id of the queried {@code TeamRule}
     * @return {@code TeamRule} to transform into an HTML response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/team/rule/detail-html")
    @ErrorTemplate(name = "/errors/404")
    public final TeamRule
            getTeamRuleHtml(@ValidId @PathParam("id") final String id) {
        return getTeamRuleService().getTeamRuleById(Integer.parseInt(id));
    }

    /**
     * Returns the {@code TeamRule} with the specified id, to be transformed
     * into a JSON response.
     * <p>
     * This will be transformed into JSON by using a JSON provider.
     * 
     * @param id
     *            id of the queried {@code TeamRule}
     * @return {@code TeamRule} to transform into a JSON response
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public final TeamRule
            getTeamRuleJSON(@ValidId @PathParam("id") final String id) {
        return getTeamRuleService().getTeamRuleById(Integer.parseInt(id));
    }

    /**
     * Returns the team rules to be transformed into an HTML response.
     * <p>
     * These will be transformed into HTML by using the Freemarker template in
     * the path '/team/rule/list-html'.
     * 
     * @return the team rules to be transformed into an HTML response through a
     *         Freemarker template
     */
    @GET
    @Produces({ MediaType.TEXT_HTML })
    @Template(name = "/team/rule/list-html")
    public final Collection<TeamRule> getTeamRulesHTML() {
        return getTeamRuleService().getAllTeamRules();
    }

    /**
     * Returns the team rules to be transformed into a JSON response.
     * <p>
     * These will be transformed into JSON through the use of a JSON provider.
     * 
     * @return the team rules to be transformed into a JSON response through a
     *         JSON provider
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public final Collection<TeamRule> getTeamRulesJSON() {
        return getTeamRuleService().getAllTeamRules();
    }

    /**
     * Returns a string with all the team rules.
     * 
     * @return a string with all the team rules
     */
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public final String getTeamRulesText() {
        final Collection<TeamRule> rules;
        final StringBuilder result;

        rules = getTeamRuleService().getAllTeamRules();

        result = new StringBuilder();
        for (final TeamRule rule : rules) {
            result.append(rule.getTeamRuleName()).append('\n');
        }

        return result.toString();
    }

    /**
     * Returns a string representing the {@code TeamRule} with the specified id.
     * 
     * @param id
     *            id of the queried {@code TeamRule}
     * @return string representing the queried {@code TeamRule}
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.TEXT_PLAIN })
    public final String
            getTeamRuleText(@ValidId @PathParam("id") final String id) {
        final TeamRule teamRule;

        teamRule = getTeamRuleService().getTeamRuleById(Integer.parseInt(id));

        return teamRule.getTeamRuleName();
    }

    /**
     * Returns the service being used by the resource.
     * 
     * @return the service being used by the resource
     */
    private final TeamRuleAccessService getTeamRuleService() {
        return ruleService;
    }

}
