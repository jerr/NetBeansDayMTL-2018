package org.printstacktrace.nbdaymtl2018;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class HealthCheckDemo implements HealthCheck {
    
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("demo").up().build();
    }

}