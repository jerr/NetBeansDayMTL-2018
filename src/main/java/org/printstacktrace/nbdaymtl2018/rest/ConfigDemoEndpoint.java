package org.printstacktrace.nbdaymtl2018.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.Config;

@ApplicationScoped
@Path("/configdemo")
public class ConfigDemoEndpoint {

	@Inject
	@ConfigProperty(name = "p1", defaultValue = "false")
	private Boolean myproperty;

	@Inject
	private Config config;

	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("p1=" + myproperty).build();
	}
        
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("all")
	public Config all() {
		return config;
	}

}