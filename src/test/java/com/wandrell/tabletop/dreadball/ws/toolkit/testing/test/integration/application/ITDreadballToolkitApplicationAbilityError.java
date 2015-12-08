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

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class ITDreadballToolkitApplicationAbilityError {

    private static final String PATH = "http://localhost:8080/units/abilities/12345";

    public ITDreadballToolkitApplicationAbilityError() {
        super();
    }

    @Test
    public final void testGetAbility_JSON()
            throws ClientProtocolException, IOException {
        final HttpUriRequest request;
        final HttpResponse httpResponse;

        request = new HttpGet(PATH);
        request.addHeader("Accept", "application/json");

        // TODO: Test this with other response types

        httpResponse = HttpClientBuilder.create().build().execute(request);

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 204);
    }

}
