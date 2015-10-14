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
package com.wandrell.tabletop.dreadball.ws.toolkit.repository;

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
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAUnit;
import com.wandrell.tabletop.dreadball.model.unit.Unit;

/**
 * JPA-based implementation of {@link AbilityRepository}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Repository("unitRepository")
public final class JPAUnitRepository
        implements UnitRepository, FilteredRepository<Unit, QueryData> {

    /**
     * Base repository for applying inheritance through composition.
     */
    private FilteredRepository<JPAUnit, QueryData> repository;

    /**
     * Constructs a {@code JPAUnitRepository}.
     */
    public JPAUnitRepository() {
        super();
    }

    @Override
    public final void add(final Unit entity) {
        checkArgument(entity instanceof JPAUnit,
                "The entity should be an instance of JPAUnit");

        getBaseRepository().add((JPAUnit) entity);
    }

    @Override
    public final Collection<Unit> getAll() {
        return new LinkedList<Unit>(getBaseRepository().getAll());
    }

    @Override
    public final Collection<Unit> getCollection(final QueryData filter) {
        return new LinkedList<Unit>(getBaseRepository().getCollection(filter));
    }

    @Override
    public final Unit getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final Unit entity) {
        checkArgument(entity instanceof JPAUnit,
                "The entity should be an instance of JPAUnit");

        getBaseRepository().remove((JPAUnit) entity);
    }

    /**
     * Setter for injecting the {@code EntityManager}.
     * 
     * @param entityManager
     *            {@code EntityManager} for the repository
     */
    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAUnit>(entityManager,
                new DefaultQueryData("SELECT unit FROM Unit unit"));
    }

    @Override
    public final void update(final Unit entity) {
        checkArgument(entity instanceof JPAUnit,
                "The entity should be an instance of JPAUnit");

        getBaseRepository().update((JPAUnit) entity);
    }

    /**
     * Returns the base repository, being used for applying inheritance through
     * composition.
     * 
     * @return the base repository
     */
    private final FilteredRepository<JPAUnit, QueryData> getBaseRepository() {
        return repository;
    }

}
