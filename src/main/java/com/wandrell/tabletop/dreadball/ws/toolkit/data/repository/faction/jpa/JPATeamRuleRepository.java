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
package com.wandrell.tabletop.dreadball.ws.toolkit.data.repository.faction.jpa;

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
import com.wandrell.tabletop.dreadball.model.faction.TeamRule;
import com.wandrell.tabletop.dreadball.model.persistence.faction.JPATeamRule;
import com.wandrell.tabletop.dreadball.ws.toolkit.data.repository.faction.TeamRuleRepository;

/**
 * JPA-based implementation of {@link TeamRuleRepository}.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Repository("teamRuleRepository")
public final class JPATeamRuleRepository
        implements TeamRuleRepository, FilteredRepository<TeamRule, QueryData> {

    /**
     * Base repository for applying inheritance through composition.
     */
    private FilteredRepository<JPATeamRule, QueryData> repository;

    /**
     * Constructs a {@code JPATeamRuleRepository}.
     */
    public JPATeamRuleRepository() {
        super();
    }

    @Override
    public final void add(final TeamRule entity) {
        checkArgument(entity instanceof JPATeamRule,
                "The entity should be an instance of JPATeamRule");

        getBaseRepository().add((JPATeamRule) entity);
    }

    @Override
    public final Collection<TeamRule> getAll() {
        return new LinkedList<TeamRule>(getBaseRepository().getAll());
    }

    @Override
    public final Collection<TeamRule> getCollection(final QueryData filter) {
        return new LinkedList<TeamRule>(
                getBaseRepository().getCollection(filter));
    }

    @Override
    public final TeamRule getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final TeamRule entity) {
        checkArgument(entity instanceof JPATeamRule,
                "The entity should be an instance of JPATeamRule");

        getBaseRepository().remove((JPATeamRule) entity);
    }

    /**
     * Setter for injecting the {@code EntityManager}.
     * 
     * @param entityManager
     *            {@code EntityManager} for the repository
     */
    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPATeamRule>(entityManager,
                new DefaultQueryData("SELECT rule FROM TeamRule rule"));
    }

    @Override
    public final void update(final TeamRule entity) {
        checkArgument(entity instanceof JPATeamRule,
                "The entity should be an instance of JPATeamRule");

        getBaseRepository().update((JPATeamRule) entity);
    }

    /**
     * Returns the base repository, being used for applying inheritance through
     * composition.
     * 
     * @return the base repository
     */
    private final FilteredRepository<JPATeamRule, QueryData>
            getBaseRepository() {
        return repository;
    }

}
