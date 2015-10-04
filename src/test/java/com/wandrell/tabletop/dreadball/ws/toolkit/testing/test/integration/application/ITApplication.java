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

import javax.ws.rs.core.Application;

import org.codehaus.jettison.json.JSONArray;
import org.glassfish.jersey.test.JerseyTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.dreadball.ws.toolkit.DreadballToolkitApplication;

public final class ITApplication extends JerseyTest {

    @Test
    public void testGetAbilities() {
        final JSONArray jsons;

        jsons = target("ability").request("application/json")
                .get(JSONArray.class);

        Assert.assertTrue(jsons != null);
    }

    @Override
    protected Application configure() {
        return new DreadballToolkitApplication();
    }

}
