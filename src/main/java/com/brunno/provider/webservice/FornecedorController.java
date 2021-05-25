package com.brunno.provider.webservice;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fornecedorDAO.FornecedorDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("FornecedorController")
public class FornecedorController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Fornecedores")
	public List<Fornecedor> Fornecedores() throws ClassNotFoundException, SQLException {
		
		try {
			FornecedorDAO providerDAO = new FornecedorDAO();
			return providerDAO.list();
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarFornecedor/{ID}")
	public Fornecedor buscarFornecedor(@PathParam("ID") int ID) throws SQLException, ClassNotFoundException {
		
		try {
			FornecedorDAO providerDAO = new FornecedorDAO();
			return providerDAO.select(ID);
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/adicFornecedor")
	public Response adicFornecedor(Fornecedor provider) throws SQLException, ClassNotFoundException {
		
		try {
			FornecedorDAO providerDAO = new FornecedorDAO();
			providerDAO.add(provider);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/alterFornecedor")
	public Response alterFornecedor(Fornecedor provider) throws SQLException, ClassNotFoundException {
		
		try {
			FornecedorDAO providerDAO = new FornecedorDAO();
			providerDAO.alter(provider);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DELETE
	@Path("/deletFornecedor")
	public Response deletFornecedor(@PathParam("ID") int ID) throws SQLException, ClassNotFoundException {
		
		try {
			FornecedorDAO providerDAO = new FornecedorDAO();
			providerDAO.delete(ID);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
