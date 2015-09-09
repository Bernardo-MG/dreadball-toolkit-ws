package com.wandrell.tabletop.dreadball.ws.toolkit;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.wandrell.tabletop.dreadball.ws.toolkit.resource.AbilityResource;
import com.wandrell.tabletop.dreadball.ws.toolkit.resource.VersionInfoResource;

@ApplicationPath("/")
public final class ToolkitApplication extends ResourceConfig {

    public ToolkitApplication() {
        super();

        register(VersionInfoResource.class);

        register(AbilityResource.class);
    }

}
