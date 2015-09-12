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
import com.wandrell.tabletop.dreadball.repository.AbilityRepository;

@Component("abilityRepository")
public class WSAbilityRepository
        implements AbilityRepository, FilteredRepository<Ability, QueryData> {

    private FilteredRepository<JPAAbility, QueryData> repository;

    public WSAbilityRepository() {
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
