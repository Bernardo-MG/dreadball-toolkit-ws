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
package com.wandrell.tabletop.dreadball.ws.toolkit.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.ws.toolkit.repository.AbilityRepository;

@Component("abilityService")
public final class JPAAbilityService implements AbilityService {

    private final AbilityRepository repository;

    @Autowired
    public JPAAbilityService(final AbilityRepository repository) {
        super();

        checkNotNull(repository,
                "Received a null pointer as abilities repository");

        this.repository = repository;
    }

    @Override
    public final Ability getAbilityById(final Integer id) {
        final QueryData filter;
        
        filter = new DefaultQueryData("SELECT ability FROM Ability ability WHERE id = :id");
        filter.addParameter("id", id);        
        
        return getRepository().getEntity(filter);
    }

    @Override
    public final Collection<Ability> getAllAbilities() {
        return getRepository().getAll();
    }

    private final AbilityRepository getRepository() {
        return repository;
    }

}
