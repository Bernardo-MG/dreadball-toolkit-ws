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
import com.wandrell.tabletop.dreadball.model.persistence.unit.JPAUnitTemplate;
import com.wandrell.tabletop.dreadball.model.unit.UnitTemplate;
import com.wandrell.tabletop.dreadball.ws.toolkit.data.repository.unit.UnitTemplateRepository;

/**
 * JPA-based implementation of {@link UnitTemplateRepository}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Repository("unitRepository")
public final class JPAUnitTemplateRepository implements UnitTemplateRepository,
        FilteredRepository<UnitTemplate, QueryData> {

    /**
     * Base repository for applying inheritance through composition.
     */
    private FilteredRepository<JPAUnitTemplate, QueryData> repository;

    /**
     * Constructs a {@code JPAUnitRepository}.
     */
    public JPAUnitTemplateRepository() {
        super();
    }

    @Override
    public final void add(final UnitTemplate entity) {
        checkArgument(entity instanceof JPAUnitTemplate,
                "The entity should be an instance of JPAUnit");

        getBaseRepository().add((JPAUnitTemplate) entity);
    }

    @Override
    public final Collection<UnitTemplate> getAll() {
        return new LinkedList<UnitTemplate>(getBaseRepository().getAll());
    }

    @Override
    public final Collection<UnitTemplate>
            getCollection(final QueryData filter) {
        return new LinkedList<UnitTemplate>(
                getBaseRepository().getCollection(filter));
    }

    @Override
    public final UnitTemplate getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final UnitTemplate entity) {
        checkArgument(entity instanceof JPAUnitTemplate,
                "The entity should be an instance of JPAUnit");

        getBaseRepository().remove((JPAUnitTemplate) entity);
    }

    /**
     * Setter for injecting the {@code EntityManager}.
     * 
     * @param entityManager
     *            {@code EntityManager} for the repository
     */
    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAUnitTemplate>(entityManager,
                new DefaultQueryData("SELECT unit FROM UnitTemplate unit"));
    }

    @Override
    public final void update(final UnitTemplate entity) {
        checkArgument(entity instanceof JPAUnitTemplate,
                "The entity should be an instance of JPAUnit");

        getBaseRepository().update((JPAUnitTemplate) entity);
    }

    /**
     * Returns the base repository, being used for applying inheritance through
     * composition.
     * 
     * @return the base repository
     */
    private final FilteredRepository<JPAUnitTemplate, QueryData>
            getBaseRepository() {
        return repository;
    }

}
