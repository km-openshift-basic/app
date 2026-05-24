package com.example;

import java.util.Map;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @ConfigProperty(name = "app.greeting", defaultValue = "Hello from OpenShift!")
    String greeting;

    @ConfigProperty(name = "app.environment", defaultValue = "unknown")
    String environment;

    @GET
    @Path("/hello")
    public Map<String, String> hello() {
        return Map.of("message", greeting);
    }

    @GET
    @Path("/info")
    public Map<String, Object> info() {
        return Map.of(
                "application", "openshift-workshop-app",
                "version", "1.0.0",
                "environment", environment,
                "javaVersion", System.getProperty("java.version"),
                "hostname", hostname());
    }

    private String hostname() {
        try {
            return java.net.InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return System.getenv().getOrDefault("HOSTNAME", "unknown");
        }
    }
}
