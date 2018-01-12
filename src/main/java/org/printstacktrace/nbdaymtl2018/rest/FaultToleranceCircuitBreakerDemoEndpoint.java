package org.printstacktrace.nbdaymtl2018.rest;

import java.net.ConnectException;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;

@ApplicationScoped
@Path("/ftcbdemo")
public class FaultToleranceCircuitBreakerDemoEndpoint {
    
    private int count = 0;
    
    @GET
    @Produces("text/plain")
    @Fallback(fallbackMethod="fallbackService")
    @CircuitBreaker(requestVolumeThreshold=2, failureRatio=0.50, delay=5000, successThreshold=2)
    public Response doGet() throws ConnectException {
            return Response.ok(myService()).build();
    }
    
    
    private String myService() throws ConnectException {
        // Simulating 2 failures
        if (count++ < 2) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
            }
            throw new ConnectException("The system is down. Try again later.");
        }
        // Rest simulation
        if(count >10) 
            count = 0;
        
        return "Hello Montreal (" + count + ")";
    }
    
    public Response fallbackService() throws ConnectException {
        return Response.ok("Hello Montreal (with fallback) (" + count + ")").build();
    }
}
