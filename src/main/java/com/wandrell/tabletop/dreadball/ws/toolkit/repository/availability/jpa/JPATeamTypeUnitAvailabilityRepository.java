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
package com.wandrell.tabletop.dreadball.ws.toolkit.repository.availability.jpa;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.repository.JPARepository;
import com.wandrell.tabletop.dreadball.model.availability.unit.TeamTypeUnitAvailability;
import com.wandrell.tabletop.dreadball.model.persistence.availability.unit.JPATeamTypeUnitAvailability;
import com.wandrell.tabletop.dreadball.ws.toolkit.repository.availability.TeamTypeUnitAvailabilityRepository;

/**
 * JPA-based implementation of {@link TeamTypeUnitAvailabilityRepository}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Repository("teamTypeTeamTypeUnitAvailabilityAvailabilityRepository")
public final class JPATeamTypeUnitAvailabilityRepository
        implements TeamTypeUnitAvailabilityRepository,
        FilteredRepository<TeamTypeUnitAvailability, QueryData> {

    /**
     * Base repository for applying inheritance through composition.
     */
    private FilteredRepository<JPATeamTypeUnitAvailability, QueryData> repository;

    /**
     * Constructs a {@code JPATeamTypeUnitAvailabilityRepository}.
     */
    public JPATeamTypeUnitAvailabilityRepository() {
        super();
    }

    @Override
    public final void add(final TeamTypeUnitAvailability entity) {
        checkArgument(entity instanceof JPATeamTypeUnitAvailability,
                "The entity should be an instance of JPATeamTypeUnitAvailability");

        getBaseRepository().add((JPATeamTypeUnitAvailability) entity);
    }

    @Override
    public final Collection<TeamTypeUnitAvailability> getAll() {
        return new LinkedList<TeamTypeUnitAvailability>(
                getBaseRepository().getAll());
    }

    @Override
    public final Collection<TeamTypeUnitAvailability>
            getCollection(final QueryData filter) {
        return new LinkedList<TeamTypeUnitAvailability>(
                getBaseRepository().getCollection(filter));
    }

    @Override
    public final TeamTypeUnitAvailability getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final TeamTypeUnitAvailability entity) {
        checkArgument(entity instanceof JPATeamTypeUnitAvailability,
                "The entity should be an instance of JPATeamTypeUnitAvailability");

        getBaseRepository().remove((JPATeamTypeUnitAvailability) entity);
    }

    /**
     * Setter for injecting the {@code EntityManager}.
     * 
     * @param entityManager
     *            {@code EntityManager} for the repository
     */
    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPATeamTypeUnitAvailability>(
                entityManager, new DefaultQueryData(
                        "SELECT ava FROM TeamTypeUnitAvailability ava"));
    }

    @Override
    public final void update(final TeamTypeUnitAvailability entity) {
        checkArgument(entity instanceof JPATeamTypeUnitAvailability,
                "The entity should be an instance of JPATeamTypeUnitAvailability");

        getBaseRepository().update((JPATeamTypeUnitAvailability) entity);
    }

    /**
     * Returns the base repository, being used for applying inheritance through
     * composition.
     * 
     * @return the base repository
     */
    private final FilteredRepository<JPATeamTypeUnitAvailability, QueryData>
            getBaseRepository() {
        return repository;
    }

}
