package com.wandrell.tabletop.dreadball.ws.toolkit.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.ws.toolkit.repository.AbilityRepository;

@Component("abilityService")
public final class JPAAbilityService implements AbilityService {

    private final AbilityRepository repository;

    @Autowired
    public JPAAbilityService(final AbilityRepository repository) {
        super();

        checkNotNull(repository,
                "Received a null pointer as abilities repository");

        this.repository = repository;
    }

    @Override
    public final Collection<Ability> getAllAbilities() {
        return getRepository().getAll();
    }

    private final AbilityRepository getRepository() {
        return repository;
    }

}
