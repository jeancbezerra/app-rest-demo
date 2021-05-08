package br.com.yaman.rest.demo.resources;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.yaman.rest.demo.dao.UserDAO;
import br.com.yaman.rest.demo.entity.UserEntity;

@Path(value = "/user")
public class UserResource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(UserResource.class);
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newUser(UserEntity userEntity) {
		try {			
			
			if(userEntity != null) {
				
				UserDAO userDAO = new UserDAO();
				userDAO.persistNewUser(userEntity);			
				
				return Response.status(Status.OK).entity(userEntity).type(MediaType.APPLICATION_JSON).build();
			}else {
				return Response.status(Status.BAD_REQUEST).entity("{\"message\":\"BAD_REQUEST\"}").type(MediaType.APPLICATION_JSON).build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"INTERNAL_SERVER_ERROR\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}

	
	@GET
	@Path("/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByUUID(@PathParam(value = "uuid") String uuid) {
		try {

			UserDAO userDAO = new UserDAO();
			UserEntity userEntity = userDAO.getUserDetails(uuid);
			
			if(userEntity != null) {
				return Response.status(Status.OK).entity(userEntity).type(MediaType.APPLICATION_JSON).build();	
			}else{
				return Response.status(Status.NO_CONTENT).entity("{\"message\":\"NO_CONTENT\"}").type(MediaType.APPLICATION_JSON).build();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"INTERNAL_SERVER_ERROR\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@DELETE
	@Path("/delete/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserByUUID(@PathParam(value = "uuid") String uuid) {
		try {

			UserDAO userDAO = new UserDAO();
			userDAO.removeUser(uuid);
			
			if(uuid != null) {
				return Response.status(Status.OK).entity("{\"removed\":\""+ uuid +"\"}").type(MediaType.APPLICATION_JSON).build();	
			}else{
				return Response.status(Status.NO_CONTENT).entity("{\"message\":\"NO_CONTENT\"}").type(MediaType.APPLICATION_JSON).build();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"INTERNAL_SERVER_ERROR\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllUsers() {
		try {

			UserDAO userDAO = new UserDAO();
			List<UserEntity> userList = userDAO.listAllUsers();
			
			if(userList != null) {
				return Response.status(Status.OK).entity(userList).type(MediaType.APPLICATION_JSON).build();	
			}else{
				return Response.status(Status.NO_CONTENT).entity("{\"message\":\"NO_CONTENT\"}").type(MediaType.APPLICATION_JSON).build();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"INTERNAL_SERVER_ERROR\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(UserEntity userEntity) {
		try {
		
			if(userEntity != null) {
				UserDAO userDAO = new UserDAO();
				userDAO.mergeUser(userEntity);				
				
				return Response.status(Status.OK).entity(userEntity).type(MediaType.APPLICATION_JSON).build();
			}else{
				return Response.status(Status.BAD_REQUEST).entity("{\"message\":\"BAD_REQUEST\"}").type(MediaType.APPLICATION_JSON).build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"INTERNAL_SERVER_ERROR\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}
}
