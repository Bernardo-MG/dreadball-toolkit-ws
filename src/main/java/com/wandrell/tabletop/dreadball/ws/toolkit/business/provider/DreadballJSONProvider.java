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
package com.wandrell.tabletop.dreadball.ws.toolkit.business.provider;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wandrell.tabletop.dreadball.model.faction.TeamRule;
import com.wandrell.tabletop.dreadball.model.json.faction.TeamRuleMixIn;
import com.wandrell.tabletop.dreadball.model.json.unit.AffinityGroupMixIn;
import com.wandrell.tabletop.dreadball.model.json.unit.UnitTemplateMixIn;
import com.wandrell.tabletop.dreadball.model.json.unit.stats.AbilityMixIn;
import com.wandrell.tabletop.dreadball.model.json.unit.stats.AttributesHolderMixIn;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.UnitTemplate;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.model.unit.stats.AttributesHolder;

/**
 * Provider for creating JSON responses from the Dreadball model classes.
 * 
 * @author Bernardo Martínez Garrido
 */
@Provider
public final class DreadballJSONProvider
        implements ContextResolver<ObjectMapper> {

    /**
     * Mapper for creating the JSON messages.
     */
    private final ObjectMapper mapper;

    {
        mapper = new ObjectMapper();

        mapper.addMixIn(Ability.class, AbilityMixIn.class);
        mapper.addMixIn(AffinityGroup.class, AffinityGroupMixIn.class);

        mapper.addMixIn(UnitTemplate.class, UnitTemplateMixIn.class);
        mapper.addMixIn(AttributesHolder.class, AttributesHolderMixIn.class);

        mapper.addMixIn(TeamRule.class, TeamRuleMixIn.class);
    }

    /**
     * Constructs a {@code DreadballJSONProvider}.
     */
    public DreadballJSONProvider() {
        super();
    }

    @Override
    public final ObjectMapper getContext(final Class<?> type) {
        return mapper;
    }

}
