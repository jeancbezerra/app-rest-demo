package br.com.yaman.rest.demo.resources;

import java.security.SecureRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path(value = "/search")
public class SearchResource {

	private static final Logger LOGGER = LogManager.getLogger(SearchResource.class);
	private SecureRandom rand = new SecureRandom();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSearch() {
		try {

			if (this.rand.nextBoolean()) {
				Thread.sleep(this.rand.nextInt(30000));
				return Response.status(Status.OK).entity("\"searchResult\":\""+this.rand.nextInt(100)+"\"").type(MediaType.APPLICATION_JSON).build();
			} else {
				return Response.status(Status.OK).entity("\"searchResult\":\"not_found\"").type(MediaType.APPLICATION_JSON).build();
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"INTERNAL_ERROR\"}").type(MediaType.APPLICATION_JSON).build();
		}

	}

}
