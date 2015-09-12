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
import com.wandrell.tabletop.dreadball.repository.AbilityRepository;

@Component
@Path("/ability")
public class AbilityResource {

    private final AbilityRepository repository;

    @Autowired
    public AbilityResource(final AbilityRepository repository) {
        super();

        checkNotNull(repository,
                "Received a null pointer as abilities repository");

        this.repository = repository;
    }

    @GET
    @Path("/json")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAbilitiesJSON() throws JsonProcessingException {
        final ObjectMapper mapper;
        final Collection<Ability> abilities;

        mapper = new ObjectMapper();
        mapper.addMixIn(Ability.class, AbilityMixIn.class);

        abilities = getAbilityRepository().getAll();

        return Response.status(201)
                .entity(mapper.writer().writeValueAsString(abilities)).build();
    }

    @GET
    @Path("/text")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response getAbilitiesText() {
        final Collection<Ability> abilities;
        String result;

        abilities = getAbilityRepository().getAll();

        result = "";
        for (final Ability ability : abilities) {
            result += " " + ability.getAbilityName();
        }

        return Response.status(200).entity(result).build();
    }

    private final AbilityRepository getAbilityRepository() {
        return repository;
    }

}
