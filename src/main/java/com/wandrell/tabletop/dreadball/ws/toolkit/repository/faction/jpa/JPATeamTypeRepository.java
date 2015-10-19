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
package com.wandrell.tabletop.dreadball.ws.toolkit.repository.faction.jpa;

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
import com.wandrell.tabletop.dreadball.model.faction.TeamType;
import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamType;
import com.wandrell.tabletop.dreadball.ws.toolkit.repository.faction.TeamTypeRepository;

/**
 * JPA-based implementation of {@link TeamTypeRepository}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Repository("teamTypeRepository")
public final class JPATeamTypeRepository
        implements TeamTypeRepository, FilteredRepository<TeamType, QueryData> {

    /**
     * Base repository for applying inheritance through composition.
     */
    private FilteredRepository<JPATeamType, QueryData> repository;

    /**
     * Constructs a {@code JPATeamTypeRepository}.
     */
    public JPATeamTypeRepository() {
        super();
    }

    @Override
    public final void add(final TeamType entity) {
        checkArgument(entity instanceof JPATeamType,
                "The entity should be an instance of JPATeamType");

        getBaseRepository().add((JPATeamType) entity);
    }

    @Override
    public final Collection<TeamType> getAll() {
        return new LinkedList<TeamType>(getBaseRepository().getAll());
    }

    @Override
    public final Collection<TeamType> getCollection(final QueryData filter) {
        return new LinkedList<TeamType>(
                getBaseRepository().getCollection(filter));
    }

    @Override
    public final TeamType getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final TeamType entity) {
        checkArgument(entity instanceof JPATeamType,
                "The entity should be an instance of JPATeamType");

        getBaseRepository().remove((JPATeamType) entity);
    }

    /**
     * Setter for injecting the {@code EntityManager}.
     * 
     * @param entityManager
     *            {@code EntityManager} for the repository
     */
    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPATeamType>(entityManager,
                new DefaultQueryData("SELECT team FROM TeamType team"));
    }

    @Override
    public final void update(final TeamType entity) {
        checkArgument(entity instanceof JPATeamType,
                "The entity should be an instance of JPATeamType");

        getBaseRepository().update((JPATeamType) entity);
    }

    /**
     * Returns the base repository, being used for applying inheritance through
     * composition.
     * 
     * @return the base repository
     */
    private final FilteredRepository<JPATeamType, QueryData>
            getBaseRepository() {
        return repository;
    }

}
