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
package com.wandrell.tabletop.dreadball.ws.toolkit.testing.test.integration.application;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.CharStreams;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;

public final class ITDreadballToolkitApplicationAbilities {

    private static final String PATH = "http://localhost:8080/abilities";

    public ITDreadballToolkitApplicationAbilities() {
        super();
    }

    @Test
    public final void testGetAbilities_HTML()
            throws ClientProtocolException, IOException {
        final HttpUriRequest request;
        final HttpResponse httpResponse;
        final String result;
        final Element html;
        final Element header;
        final Element cell;

        request = new HttpGet(PATH);
        request.addHeader("Accept", "text/html");

        httpResponse = HttpClientBuilder.create().build().execute(request);

        result = CharStreams.toString(
                new InputStreamReader(httpResponse.getEntity().getContent()));

        html = Jsoup.parse(result).body();

        header = html.select("h1").first();

        cell = html.select("td").get(1);

        Assert.assertEquals(header.text(), "Abilities list");
        Assert.assertEquals(cell.text(), "360_vision");
    }

    @SuppressWarnings("serial")
    @Test
    public final void testGetAbilities_JSON()
            throws ClientProtocolException, IOException {
        final HttpUriRequest request;
        final HttpResponse httpResponse;
        final Collection<Ability> abilities;
        final Type token;

        request = new HttpGet(PATH);
        request.addHeader("Accept", "application/json");

        httpResponse = HttpClientBuilder.create().build().execute(request);

        token = new TypeToken<Collection<JPAAbility>>() {}.getType();
        abilities = new Gson().fromJson(
                new InputStreamReader(httpResponse.getEntity().getContent()),
                token);

        Assert.assertEquals("360_vision",
                abilities.iterator().next().getAbilityName());
    }

    @Test
    public final void testGetAbilities_String()
            throws ClientProtocolException, IOException {
        final HttpUriRequest request;
        final HttpResponse httpResponse;
        final String result;

        request = new HttpGet(PATH);
        request.addHeader("Accept", "text/plain");

        httpResponse = HttpClientBuilder.create().build().execute(request);

        result = CharStreams.toString(
                new InputStreamReader(httpResponse.getEntity().getContent()));

        Assert.assertTrue(result.startsWith("360_vision\n"));
    }

}
