package br.com.yaman.rest.demo.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.yaman.rest.demo.entity.SystemPropertiesEntity;

@Path("/instance")
public class InstanceResource {

	private static final Logger LOGGER = LogManager.getLogger(InstanceResource.class);

	@GET
	@Path("/jvm/kv")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInstanceJavaVirtualMachineDetails() {
		try {

			List<SystemPropertiesEntity> list = new ArrayList<>(250);

			for (Entry<Object, Object> systemProperties : System.getProperties().entrySet()) {
				SystemPropertiesEntity systemPropertiesEntity = new SystemPropertiesEntity();
				systemPropertiesEntity.setKey(systemProperties.getKey().toString());
				systemPropertiesEntity.setValue(systemProperties.getValue().toString());

				list.add(systemPropertiesEntity);
			}

			return Response.status(Status.OK).entity(list).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"INTERNAL_ERROR\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("/jvm/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInstanceJavaVirtualMachineDetails2() {
		try {

			return Response.status(Status.OK).entity(System.getProperties()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"INTERNAL_ERROR\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

}
