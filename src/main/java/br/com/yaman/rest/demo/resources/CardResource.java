package br.com.yaman.rest.demo.resources;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.yaman.rest.demo.dao.CardDAO;
import br.com.yaman.rest.demo.entity.CardEntity;

@Path(value = "/card")
public class CardResource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(CardResource.class);
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newCard(CardEntity cardEntity) {
		try {			
			
			if(cardEntity != null) {
				
				CardDAO cardDAO = new CardDAO();
				cardDAO.persistNewCard(cardEntity);			
				
				return Response.status(Status.OK).entity(cardEntity).type(MediaType.APPLICATION_JSON).build();
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
	public Response getCardById(@PathParam(value = "uuid") String uuid) {
		try {

			CardEntity cardEntity = new CardEntity();
			CardDAO cardDAO = new CardDAO();
			cardEntity = cardDAO.getCardEntity(uuid);
			
			if(cardEntity==null) {				
				return Response.status(Status.NO_CONTENT).entity("{\"message\":\"" + Status.NO_CONTENT + "\"}").type(MediaType.APPLICATION_JSON).build();
			}else {
				return Response.status(Status.OK).entity(cardEntity).type(MediaType.APPLICATION_JSON).build();	
			}
			
						
		} catch (Exception e) {			
			LOGGER.error(e.getMessage());
			return Response.status(Status.NO_CONTENT).entity(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@GET
	@Path("/nf/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCardByIdNotFound(@PathParam(value = "uuid") String uuid) {
		try {

			CardEntity cardEntity = new CardEntity();
			CardDAO cardDAO = new CardDAO();
			cardEntity = cardDAO.getCardEntity(uuid);
			
			if(cardEntity==null) {
				return Response.status(Status.NOT_FOUND).entity("{\"message\":\"" + Status.NOT_FOUND + "\"}").type(MediaType.APPLICATION_JSON).build();		
			}else {
				return Response.status(Status.OK).entity(cardEntity).type(MediaType.APPLICATION_JSON).build();	
			}		
						
		} catch (Exception e) {			
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"" + Status.INTERNAL_SERVER_ERROR + "\"}").type(MediaType.APPLICATION_JSON).build();	
		}
	}
	
	@GET
	@Path("/user/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCardListByUserId(@PathParam(value = "uuid") String uuid) {
		try {

			CardDAO cardDAO = new CardDAO();
			List<CardEntity> myCards = cardDAO.listMyCards(uuid);
			
			if(myCards != null) {
				return Response.status(Status.OK).entity(myCards).type(MediaType.APPLICATION_JSON).build();	
			}else{
				return Response.status(Status.NO_CONTENT).entity("{\"message\":\"NO_CONTENT\"}").type(MediaType.APPLICATION_JSON).build();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"INTERNAL_SERVER_ERROR\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}

}