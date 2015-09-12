package com.wandrell.tabletop.dreadball.ws.toolkit;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public final class ToolkitApplication extends ResourceConfig {

    public ToolkitApplication() {
        super();

        packages("com.wandrell.tabletop.dreadball.ws.toolkit.resource");
    }

}
