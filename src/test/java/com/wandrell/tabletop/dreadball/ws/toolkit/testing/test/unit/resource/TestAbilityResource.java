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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.ws.toolkit.resource.AbilityResource;
import com.wandrell.tabletop.dreadball.ws.toolkit.service.AbilityService;

public final class TestAbilityResource {

    private AbilityResource resource;

    public static String diff(String str1, String str2) {
        int index = str1.lastIndexOf(str2);
        if (index > -1) {
            return str1.substring(str2.length());
        }
        return str1;
    }

    public TestAbilityResource() {
        super();
    }

    @BeforeClass
    public final void initialize() {
        final AbilityService service;
        final Collection<Ability> abilities;
        Ability ability;

        service = Mockito.mock(AbilityService.class);

        abilities = new LinkedList<Ability>();

        ability = Mockito.mock(Ability.class);
        Mockito.when(ability.getAbilityName()).thenReturn("ability1");
        abilities.add(ability);

        ability = Mockito.mock(Ability.class);
        Mockito.when(ability.getAbilityName()).thenReturn("ability2");
        abilities.add(ability);

        Mockito.when(service.getAllAbilities()).thenReturn(abilities);

        resource = new AbilityResource(service);
    }

    @Test
    public final void testAbilitiesHTML() {
        // TODO: This probably needs an integration test
    }

    @Test
    public final void testAbilitiesJSON() throws JsonProcessingException {
        final Response response;
        final String json;

        json = String.format(
                "[ {%n  \"name\" : \"ability1\"%n}, {%n  \"name\" : \"ability2\"%n} ]");

        response = resource.getAbilitiesJSON();
        Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(json, response.getEntity());
    }

    @Test
    public final void testAbilitiesText() {
        final String response;

        response = resource.getAbilitiesText();

        Assert.assertEquals("ability1\nability2\n", response);
    }

}