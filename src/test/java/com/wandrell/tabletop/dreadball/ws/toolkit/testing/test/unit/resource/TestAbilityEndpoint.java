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

package com.wandrell.tabletop.dreadball.ws.toolkit.testing.test.unit.resource;

import java.util.Collection;
import java.util.LinkedList;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.ws.toolkit.business.service.unit.AbilityDataService;
import com.wandrell.tabletop.dreadball.ws.toolkit.endpoint.unit.AbilityEndpoint;

public final class TestAbilityEndpoint {

    private AbilityEndpoint resource;

    public static String diff(String str1, String str2) {
        int index = str1.lastIndexOf(str2);
        if (index > -1) {
            return str1.substring(str2.length());
        }
        return str1;
    }

    public TestAbilityEndpoint() {
        super();
    }

    @BeforeClass
    public final void initialize() {
        final AbilityDataService service;
        final Collection<Ability> abilities;
        Ability ability;

        service = Mockito.mock(AbilityDataService.class);

        abilities = new LinkedList<Ability>();

        ability = Mockito.mock(Ability.class);
        Mockito.when(ability.getName()).thenReturn("ability1");
        abilities.add(ability);

        ability = Mockito.mock(Ability.class);
        Mockito.when(ability.getName()).thenReturn("ability2");
        abilities.add(ability);

        Mockito.when(service.getAllAbilities()).thenReturn(abilities);

        resource = new AbilityEndpoint(service);
    }

    @Test
    public final void testAbilitiesHTML() {
        Assert.assertEquals(2, resource.getAbilitiesHTML().size());
    }

    @Test
    public final void testAbilitiesJSON() {
        Assert.assertEquals(2, resource.getAbilitiesHTML().size());
    }

    @Test
    public final void testAbilitiesText() {
        final String response;

        response = resource.getAbilitiesText();

        Assert.assertEquals("ability1\nability2\n", response);
    }

}