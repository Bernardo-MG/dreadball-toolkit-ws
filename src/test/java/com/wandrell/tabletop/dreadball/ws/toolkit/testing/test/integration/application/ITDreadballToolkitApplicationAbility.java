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
import com.google.gson.Gson;
import com.wandrell.tabletop.dreadball.model.persistence.unit.stats.JPAAbility;
import com.wandrell.tabletop.dreadball.model.unit.stats.Ability;

public final class ITDreadballToolkitApplicationAbility {

    private static final String PATH = "http://localhost:8080/abilities/1";

    public ITDreadballToolkitApplicationAbility() {
        super();
    }

    @Test
    public final void testGetAbility_HTML()
            throws ClientProtocolException, IOException {
        final HttpUriRequest request;
        final HttpResponse httpResponse;
        final String result;
        final Element html;
        final Element header;
        final Element name;

        request = new HttpGet(PATH);
        request.addHeader("Accept", "text/html");

        httpResponse = HttpClientBuilder.create().build().execute(request);

        result = CharStreams.toString(
                new InputStreamReader(httpResponse.getEntity().getContent()));

        html = Jsoup.parse(result).body();

        header = html.select("h3").first();

        name = html.select("dd").get(1);

        Assert.assertEquals(header.text(), "Ability 1");
        Assert.assertEquals(name.text(), "360_vision");
    }

    @Test
    public final void testGetAbility_JSON()
            throws ClientProtocolException, IOException {
        final HttpUriRequest request;
        final HttpResponse httpResponse;
        final Ability ability;

        request = new HttpGet(PATH);
        request.addHeader("Accept", "application/json");

        httpResponse = HttpClientBuilder.create().build().execute(request);

        ability = new Gson().fromJson(
                new InputStreamReader(httpResponse.getEntity().getContent()),
                JPAAbility.class);

        Assert.assertEquals("360_vision", ability.getAbilityName());
    }

    @Test
    public final void testGetAbility_String()
            throws ClientProtocolException, IOException {
        final HttpUriRequest request;
        final HttpResponse httpResponse;
        final String result;

        request = new HttpGet(PATH);
        request.addHeader("Accept", "text/plain");

        httpResponse = HttpClientBuilder.create().build().execute(request);

        result = CharStreams.toString(
                new InputStreamReader(httpResponse.getEntity().getContent()));

        Assert.assertEquals("360_vision", result);
    }

}
