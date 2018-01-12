
package org.printstacktrace.nbdaymtl2018;

import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class HealthCheckRandomDemo implements HealthCheck {
    
   private final Random random = new Random();
   
    @Override
    public HealthCheckResponse call() {
        int value = random.nextInt(10);
        return HealthCheckResponse.builder().name("demo-random")
              .withData("conference", "NetBeansDay Mongtreal")
              .withData("value", value)
              .state(value < 8)
              .build();
    }
    
}
