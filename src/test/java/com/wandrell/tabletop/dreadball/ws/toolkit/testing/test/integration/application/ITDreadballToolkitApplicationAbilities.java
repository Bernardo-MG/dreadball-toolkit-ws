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

public final class ITDreadballToolkitApplicationAbilities {

    public ITDreadballToolkitApplicationAbilities() {
        super();
        // https://github.com/Hylke1982/jersey2-spring-test-example
        // https://vikashazrati.wordpress.com/2011/03/07/testing-rest-with-grizzly/
        // http://www.hascode.com/2011/09/rest-assured-vs-jersey-test-framework-testing-your-restful-web-services/
        // http://www.hascode.com/2011/10/testing-restful-web-services-made-easy-using-the-rest-assured-framework/
        // http://ameethpaatil.blogspot.com.es/2011/09/restful-webservice-jersey-spring-30x.html
        // https://github.com/amacoder/demo-restWS-spring-jersey-tomcat-mybatis
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

        request = new HttpGet("http://localhost:11080/abilities");
        request.addHeader("Accept", "text/html");

        httpResponse = HttpClientBuilder.create().build().execute(request);

        result = CharStreams.toString(
                new InputStreamReader(httpResponse.getEntity().getContent()));

        html = Jsoup.parse(result).body();

        header = html.select("h1").first();

        cell = html.select("td").first();

        Assert.assertEquals(header.text(), "Abilities list");
        Assert.assertEquals(cell.text(), "360_vision");
    }

    @Test
    public final void testGetAbilities_String()
            throws ClientProtocolException, IOException {
        final HttpUriRequest request;
        final HttpResponse httpResponse;
        final String result;

        request = new HttpGet("http://localhost:11080/abilities");
        request.addHeader("Accept", "text/plain");

        httpResponse = HttpClientBuilder.create().build().execute(request);

        result = CharStreams.toString(
                new InputStreamReader(httpResponse.getEntity().getContent()));

        Assert.assertTrue(result.startsWith("360_vision\n"));
    }

}
