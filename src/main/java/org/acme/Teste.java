package org.acme;

import java.util.List;

import org.acme.dto.Transaction;
import org.acme.dto.User;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class Teste {

	@GET()
	@Produces(MediaType.APPLICATION_JSON)
	public Response teste() {
		User user = new User();
		user.setUser("teste");
		return Response.status(200).entity(user).build();
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lucros")
    public Response getLucros() {
        Client client = ClientBuilder.newClient();
        Response response = client.target("https://123.herokuapp.com")
                .path("/transaction/market/lucros")
                .request(MediaType.APPLICATION_JSON)
                .header("api-key", "asdasda")
                .get();

        if (response.getStatus() == 200) {
           Transaction lucros = response.readEntity(new GenericType<Transaction>() {});
            return Response.ok(lucros).build();
        } else {
            return Response.status(response.getStatus()).entity("Erro ao obter os lucros.").build();
        }
    }
}
