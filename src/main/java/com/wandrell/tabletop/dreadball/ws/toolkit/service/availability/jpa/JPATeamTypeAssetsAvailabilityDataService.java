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
package com.wandrell.tabletop.dreadball.ws.toolkit.service.availability.jpa;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.tabletop.dreadball.model.availability.team.TeamTypeAssetsAvailability;
import com.wandrell.tabletop.dreadball.ws.toolkit.repository.availability.TeamTypeAssetsAvailabilityRepository;
import com.wandrell.tabletop.dreadball.ws.toolkit.service.availability.TeamTypeAssetsAvailabilityDataService;

/**
 * Implementation of {@link TeamTypeAssetsAvailabilityDataService} working
 * behind the scenes with JPA.
 * <p>
 * This is prepared to be used with Spring, as part of the dependency injection
 * process.
 * 
 * @author Bernardo Martínez Garrido
 */
@Singleton
@Service("teamTypeAssetsAvailabilityDataService")
public final class JPATeamTypeAssetsAvailabilityDataService
        implements TeamTypeAssetsAvailabilityDataService {

    /**
     * Repository for the {@code TeamTypeAssetsAvailability} instances.
     */
    private final TeamTypeAssetsAvailabilityRepository unitRepository;

    /**
     * Constructs a {@code JPATeamTypeAssetsAvailabilityService} with the
     * specified repository.
     * <p>
     * Said repository is meant to be injected through Spring.
     * 
     * @param repository
     *            the repository to be used by the service
     */
    @Autowired
    public JPATeamTypeAssetsAvailabilityDataService(
            final TeamTypeAssetsAvailabilityRepository repository) {
        super();

        unitRepository = checkNotNull(repository,
                "Received a null pointer as abilities repository");
    }

    @Override
    public final Collection<TeamTypeAssetsAvailability>
            getAllTeamTypeAssetsAvailabilities() {
        return getRepository().getAll();
    }

    @Override
    public final TeamTypeAssetsAvailability
            getTeamTypeAssetsAvailabilityById(final Integer id) {
        final QueryData filter;

        filter = new DefaultQueryData(
                "SELECT ava FROM TeamTypeAssetsAvailability ava WHERE id = :id");
        filter.addParameter("id", id);

        return getRepository().getEntity(filter);
    }

    /**
     * Returns the repository being used by the service.
     * 
     * @return the repository being used by the service
     */
    private final TeamTypeAssetsAvailabilityRepository getRepository() {
        return unitRepository;
    }

}
