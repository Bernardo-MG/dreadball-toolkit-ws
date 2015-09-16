package com.wandrell.tabletop.dreadball.ws.toolkit.testing.test.unit.resource;

import java.util.Collection;
import java.util.LinkedList;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;
import com.wandrell.tabletop.dreadball.ws.toolkit.resource.AbilityResource;
import com.wandrell.tabletop.dreadball.ws.toolkit.service.AbilityService;

import junit.framework.Assert;

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
        final Response response;
        final String html;

        html = String.format(
                "<!DOCTYPE html>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\">\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n<meta charset='utf-8'>\n<title>Abilities list</title>\n</head>\n<body>\n<h1>Abilities list</h1>\n<table border=\"1\">\n<thead>\n<tr>\n<th>Name</th>\n</tr>\n</thead>\n<tr>\n<td>ability1</td>\n</tr>\n<tr>\n<td>ability2</td>\n</tr></table>\n</body>");

        response = resource.getAbilitiesHTML();
        Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(html, response.getEntity());
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
        final Response response;

        response = resource.getAbilitiesText();
        Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals("ability1\nability2", response.getEntity());
    }

}