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

package com.wandrell.tabletop.dreadball.ws.toolkit.business.service.unit.jpa;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.ws.toolkit.business.service.unit.AbilityDataService;
import com.wandrell.tabletop.dreadball.ws.toolkit.data.repository.unit.AbilityRepository;

/**
 * Implementation of {@link AbilityDataService} working behind the scenes with
 * JPA.
 * <p>
 * This is prepared to be used with Spring, as part of the dependency injection
 * process.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Service("abilityDataService")
public final class JPAAbilityDataService implements AbilityDataService {

    /**
     * Repository for the {@code Ability} instances.
     */
    private final AbilityRepository abilityRepository;

    /**
     * Constructs a {@code JPAAbilityService} with the specified repository.
     * <p>
     * Said repository is meant to be injected through Spring.
     * 
     * @param repository
     *            the repository to be used by the service
     */
    @Autowired
    public JPAAbilityDataService(final AbilityRepository repository) {
        super();

        abilityRepository = checkNotNull(repository,
                "Received a null pointer as abilities repository");
    }

    @Override
    public final Ability getAbilityById(final Integer id) {
        final QueryData filter;

        filter = new DefaultQueryData(
                "SELECT ability FROM Ability ability WHERE id = :id");
        filter.addParameter("id", id);

        return getRepository().getEntity(filter);
    }

    @Override
    public final Collection<Ability> getAllAbilities() {
        return getRepository().getAll();
    }

    /**
     * Returns the repository being used by the service.
     * 
     * @return the repository being used by the service
     */
    private final AbilityRepository getRepository() {
        return abilityRepository;
    }

}
