package org.printstacktrace.nbdaymtl2018.rest;

import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;

@ApplicationScoped
@Path("/metricdemo")
public class MetricDemoEndpoint {

    private final Random random = new Random();
   
    @Inject
    @Metric(name = "calls")
    private Counter calls;

    @GET
    @Produces("text/plain")
    public Response doGet() {
        calls.inc();
        return Response.ok("Hello Montreal!").build();
    }

    @GET
    @Path("timed")
    @Produces("text/plain")
    @Timed(name = "helloTimed", description = "Timing of the Hello call", absolute = true)
    public Response doHelloTimed() {
        int sleep = random.nextInt(1000);
        try {
            Thread.sleep(sleep);
	} catch (InterruptedException e) {
	}
        return Response.ok("Hello Timed! (" + sleep + "ms)").build();
    }

    @GET
    @Path("counted")
    @Produces("text/plain")
    @Counted(name = "helloCounted", description = "Counting of the Hello call", monotonic = true)
    public Response doHelloCounted() {
        return Response.ok("Hello Counted!").build();
    }

}
