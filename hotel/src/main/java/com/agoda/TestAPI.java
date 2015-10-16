package com.agoda;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class TestAPI {
	public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI());
        System.out.println(target.path("api").path("hotel").request().accept(MediaType.TEXT_PLAIN).get(String.class));
        System.out.println(target.path("api").path("hotel/city/Bangkok").request().accept(MediaType.TEXT_PLAIN).get(String.class));
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/hotel").build();
    }


}
