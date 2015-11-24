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
package com.wandrell.tabletop.dreadball.ws.toolkit.data.repository.availability.jpa;

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
import com.wandrell.tabletop.dreadball.model.availability.asset.TeamTypeAssetsAvailability;
import com.wandrell.tabletop.dreadball.model.persistence.availability.asset.JPATeamTypeAssetsAvailability;
import com.wandrell.tabletop.dreadball.ws.toolkit.data.repository.availability.TeamTypeAssetsAvailabilityRepository;

/**
 * JPA-based implementation of {@link TeamTypeAssetsAvailabilityRepository}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Repository("teamTypeAssetsAvailabilityRepository")
public final class JPATeamTypeAssetsAvailabilityRepository
        implements TeamTypeAssetsAvailabilityRepository,
        FilteredRepository<TeamTypeAssetsAvailability, QueryData> {

    /**
     * Base repository for applying inheritance through composition.
     */
    private FilteredRepository<JPATeamTypeAssetsAvailability, QueryData> repository;

    /**
     * Constructs a {@code JPATeamTypeAssetsAvailabilityRepository}.
     */
    public JPATeamTypeAssetsAvailabilityRepository() {
        super();
    }

    @Override
    public final void add(final TeamTypeAssetsAvailability entity) {
        checkArgument(entity instanceof JPATeamTypeAssetsAvailability,
                "The entity should be an instance of JPATeamTypeAssetsAvailability");

        getBaseRepository().add((JPATeamTypeAssetsAvailability) entity);
    }

    @Override
    public final Collection<TeamTypeAssetsAvailability> getAll() {
        return new LinkedList<TeamTypeAssetsAvailability>(
                getBaseRepository().getAll());
    }

    @Override
    public final Collection<TeamTypeAssetsAvailability>
            getCollection(final QueryData filter) {
        return new LinkedList<TeamTypeAssetsAvailability>(
                getBaseRepository().getCollection(filter));
    }

    @Override
    public final TeamTypeAssetsAvailability getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final TeamTypeAssetsAvailability entity) {
        checkArgument(entity instanceof JPATeamTypeAssetsAvailability,
                "The entity should be an instance of JPATeamTypeAssetsAvailability");

        getBaseRepository().remove((JPATeamTypeAssetsAvailability) entity);
    }

    /**
     * Setter for injecting the {@code EntityManager}.
     * 
     * @param entityManager
     *            {@code EntityManager} for the repository
     */
    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPATeamTypeAssetsAvailability>(
                entityManager, new DefaultQueryData(
                        "SELECT ava FROM TeamTypeAssetsAvailability ava"));
    }

    @Override
    public final void update(final TeamTypeAssetsAvailability entity) {
        checkArgument(entity instanceof JPATeamTypeAssetsAvailability,
                "The entity should be an instance of JPATeamTypeAssetsAvailability");

        getBaseRepository().update((JPATeamTypeAssetsAvailability) entity);
    }

    /**
     * Returns the base repository, being used for applying inheritance through
     * composition.
     * 
     * @return the base repository
     */
    private final FilteredRepository<JPATeamTypeAssetsAvailability, QueryData>
            getBaseRepository() {
        return repository;
    }

}
