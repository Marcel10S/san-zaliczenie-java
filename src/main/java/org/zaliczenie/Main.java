package org.zaliczenie;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Main {

    public static void main(String[] args) {
        // Adres URI serwera (http://localhost:8080/api)
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).path("api").build();

        // Konfiguracja zasobów
        ResourceConfig config = new ResourceConfig()
                .packages("org.zaliczenie"); // Adjust with your package containing resources

        // Uruchomienie serwera Grizzly
        var server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        // Komunikat potwierdzający uruchomienie serwera
        System.out.println("Jersey app started with WADL available at " + baseUri + "application.wadl");
        System.out.println("Press CTRL^C to exit..");

        // Czekaj na zatrzymanie serwera
        try {
            server.start();
            Thread.currentThread().join();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            server.shutdownNow();
        }
    }
}