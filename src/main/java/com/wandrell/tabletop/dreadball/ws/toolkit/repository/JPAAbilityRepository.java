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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.repository.JPARepository;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;

@Component("abilityRepository")
public class JPAAbilityRepository
        implements AbilityRepository, FilteredRepository<Ability, QueryData> {

    private FilteredRepository<JPAAbility, QueryData> repository;

    public JPAAbilityRepository() {
        super();
    }

    @Override
    public final void add(final Ability entity) {
        checkArgument(entity instanceof JPAAbility);

        getBaseRepository().add((JPAAbility) entity);
    }

    @Override
    public final Collection<Ability> getAll() {
        return new LinkedList<Ability>(getBaseRepository().getAll());
    }

    @Override
    public final Collection<Ability> getCollection(final QueryData filter) {
        return new LinkedList<Ability>(
                getBaseRepository().getCollection(filter));
    }

    @Override
    public final Ability getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final Ability entity) {
        checkArgument(entity instanceof JPAAbility);

        getBaseRepository().remove((JPAAbility) entity);
    }

    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAAbility>(entityManager,
                new DefaultQueryData("SELECT ability FROM Ability ability"));
    }

    @Override
    public final void update(final Ability entity) {
        checkArgument(entity instanceof JPAAbility);

        getBaseRepository().update((JPAAbility) entity);
    }

    private final FilteredRepository<JPAAbility, QueryData>
            getBaseRepository() {
        return repository;
    }

}
