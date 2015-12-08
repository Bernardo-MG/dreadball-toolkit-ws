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

package com.wandrell.tabletop.dreadball.ws.toolkit.data.repository.unit.jpa;

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
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAAffinityGroup;
import com.wandrell.tabletop.dreadball.model.unit.AffinityGroup;
import com.wandrell.tabletop.dreadball.ws.toolkit.data.repository.unit.AbilityRepository;
import com.wandrell.tabletop.dreadball.ws.toolkit.data.repository.unit.AffinityGroupRepository;

/**
 * JPA-based implementation of {@link AbilityRepository}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Repository("affinityGroupRepository")
public final class JPAAffinityGroupRepository implements
        AffinityGroupRepository, FilteredRepository<AffinityGroup, QueryData> {

    /**
     * Base repository for applying inheritance through composition.
     */
    private FilteredRepository<JPAAffinityGroup, QueryData> repository;

    /**
     * Constructs a {@code JPAAffinityGroupRepository}.
     */
    public JPAAffinityGroupRepository() {
        super();
    }

    @Override
    public final void add(final AffinityGroup entity) {
        checkArgument(entity instanceof JPAAffinityGroup,
                "The entity should be an instance of JPAAffinityGroup");

        getBaseRepository().add((JPAAffinityGroup) entity);
    }

    @Override
    public final Collection<AffinityGroup> getAll() {
        return new LinkedList<AffinityGroup>(getBaseRepository().getAll());
    }

    @Override
    public final Collection<AffinityGroup>
            getCollection(final QueryData filter) {
        return new LinkedList<AffinityGroup>(
                getBaseRepository().getCollection(filter));
    }

    @Override
    public final AffinityGroup getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final AffinityGroup entity) {
        checkArgument(entity instanceof JPAAffinityGroup,
                "The entity should be an instance of JPAAffinityGroup");

        getBaseRepository().remove((JPAAffinityGroup) entity);
    }

    /**
     * Setter for injecting the {@code EntityManager}.
     * 
     * @param entityManager
     *            {@code EntityManager} for the repository
     */
    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAAffinityGroup>(entityManager,
                new DefaultQueryData(
                        "SELECT affinity FROM AffinityGroup affinity"));
    }

    @Override
    public final void update(final AffinityGroup entity) {
        checkArgument(entity instanceof JPAAffinityGroup,
                "The entity should be an instance of JPAAffinityGroup");

        getBaseRepository().update((JPAAffinityGroup) entity);
    }

    /**
     * Returns the base repository, being used for applying inheritance through
     * composition.
     * 
     * @return the base repository
     */
    private final FilteredRepository<JPAAffinityGroup, QueryData>
            getBaseRepository() {
        return repository;
    }

}
