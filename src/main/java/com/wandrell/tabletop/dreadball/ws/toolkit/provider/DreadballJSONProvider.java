package com.wandrell.tabletop.dreadball.ws.toolkit.provider;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wandrell.tabletop.dreadball.model.json.unit.stats.AbilityMixIn;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;

@Provider
public final class DreadballJSONProvider
        implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    {
        mapper = new ObjectMapper();

        mapper.addMixIn(Ability.class, AbilityMixIn.class);
    }

    public DreadballJSONProvider() {
        super();
    }

    @Override
    public final ObjectMapper getContext(final Class<?> type) {
        return mapper;
    }

}
