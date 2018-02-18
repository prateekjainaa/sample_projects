package com.pjain.service.health;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class MemoryHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long memUsed = memoryBean.getHeapMemoryUsage().getUsed();
        long memMax = memoryBean.getHeapMemoryUsage().getMax();
        double memLeft = ((memMax - memUsed)/memMax)*100;
        if (memLeft >= 10d) {
            return HealthCheckResponse.named("heap-memory").withData("used", memUsed)
                    .withData("max", memMax).up().build();
        }
        return HealthCheckResponse.named("heap-memory").withData("used", memUsed)
                .withData("max", memMax).down().build();
        
    }

}
