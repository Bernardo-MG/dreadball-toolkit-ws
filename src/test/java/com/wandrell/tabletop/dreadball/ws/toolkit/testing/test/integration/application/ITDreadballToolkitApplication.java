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
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.CharStreams;

public final class ITDreadballToolkitApplication {

    public ITDreadballToolkitApplication() {
        super();
        // https://github.com/Hylke1982/jersey2-spring-test-example
        // https://vikashazrati.wordpress.com/2011/03/07/testing-rest-with-grizzly/
        // http://www.hascode.com/2011/09/rest-assured-vs-jersey-test-framework-testing-your-restful-web-services/
        // http://www.hascode.com/2011/10/testing-restful-web-services-made-easy-using-the-rest-assured-framework/
        // http://ameethpaatil.blogspot.com.es/2011/09/restful-webservice-jersey-spring-30x.html
        // https://github.com/amacoder/demo-restWS-spring-jersey-tomcat-mybatis
    }

    @Test
    public final void testGetAbilities_String()
            throws ClientProtocolException, IOException {
        final HttpUriRequest request;
        final HttpResponse httpResponse;
        final String result;
        final String expected;

        request = new HttpGet("http://localhost:11080/abilities");
        request.addHeader("Accept", "text/plain");

        httpResponse = HttpClientBuilder.create().build().execute(request);

        result = CharStreams.toString(
                new InputStreamReader(httpResponse.getEntity().getContent()));

        expected = "360_vision\n" + "adaptable\n" + "a_safe_pair_of_hands\n"
                + "alert\n" + "backflip\n" + "backstab\n" + "blood_money\n"
                + "cant_feel_a_thing\n" + "charge\n" + "comin_through\n"
                + "crowd_puller\n" + "dirty_tricks\n" + "does_this_hurt\n"
                + "driller\n" + "crowd_puller\n" + "duck_and_weave\n"
                + "even_the_odds\n" + "explosive_collar\n" + "fan_favourite\n"
                + "fragile\n" + "gotcha\n" + "grizzled\n" + "harmonics\n"
                + "heal\n" + "illegal\n" + "illegal_modifications\n"
                + "it_wasnt_me\n" + "jump\n" + "keeper\n" + "klutz\n"
                + "long_arms\n" + "lucky\n" + "mighty\n" + "mind_control\n"
                + "mind_like_water\n" + "misdirect\n" + "mutant_native\n"
                + "nemesis\n" + "one_mind\n" + "pacifist\n" + "phaser\n"
                + "pile_driver\n" + "poison_blade\n" + "portal\n"
                + "portal_master\n" + "prima_donna\n" + "pummel\n" + "push\n"
                + "quick_change_artist\n" + "quick_recovery\n" + "ram\n"
                + "ray_gun\n" + "really_lucky\n" + "resilient\n" + "resources\n"
                + "roll\n" + "rolling\n" + "rule_of_law\n" + "runaround\n"
                + "running_interference\n" + "shock_collar\n" + "shove\n"
                + "show_off\n" + "slide\n" + "slippery_customer\n" + "snack\n"
                + "spinner\n" + "steady\n" + "stench\n" + "stretch\n"
                + "stubborn\n" + "tail\n" + "taking_a_dive\n" + "teleport\n"
                + "threatening\n" + "tongue\n" + "toxic_immunity\n"
                + "trail_blazer\n" + "toxic\n" + "uncontrolled\n" + "vigour\n";

        Assert.assertEquals(expected, result);
    }

}
