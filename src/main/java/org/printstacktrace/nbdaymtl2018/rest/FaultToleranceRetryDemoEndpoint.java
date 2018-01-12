package org.printstacktrace.nbdaymtl2018.rest;

import java.net.ConnectException;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;

@ApplicationScoped
@Path("/ftretrydemo")
public class FaultToleranceRetryDemoEndpoint {
    
    private int count = 0;
    
    @GET
    @Produces("text/plain")
    @Retry(abortOn = ConnectException.class, maxRetries = 3, delay = 100 )
    public Response doGet() throws ConnectException {
            return Response.ok(myService()).build();
    }
    
    private String myService() throws ConnectException {
        // Failures simulation
        if (++count > 2 && count <= 4 ) {
            throw new ConnectException("The system is down. Try again later.");
        }
        
        // Rest simulation
        count = count%10;        
        return "Hello Montreal (" + count + ")";
    }
    
}
