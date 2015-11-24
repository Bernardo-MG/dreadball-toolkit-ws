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
package com.wandrell.tabletop.dreadball.ws.toolkit.business.service.availability;

import java.util.Collection;

import com.wandrell.tabletop.dreadball.model.availability.asset.TeamTypeAssetsAvailability;

/**
 * Interface for a service which handles {@link TeamTypeAssetsAvailability}
 * instances.
 * 
 * @author Bernardo Martínez Garrido
 */
public interface TeamTypeAssetsAvailabilityDataService {

    /**
     * Returns all {@code TeamTypeAssetsAvailability} instances.
     * 
     * @return all {@code TeamTypeAssetsAvailability} instances
     */
    public Collection<TeamTypeAssetsAvailability>
            getAllTeamTypeAssetsAvailabilities();

    /**
     * Acquires an {@code TeamTypeAssetsAvailability} based on its id.
     * 
     * @param id
     *            the {@code TeamTypeAssetsAvailability} id
     * @return the {@code TeamTypeAssetsAvailability} with that id
     */
    public TeamTypeAssetsAvailability
            getTeamTypeAssetsAvailabilityById(final Integer id);

}
