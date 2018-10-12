package com.stratpoint.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.stratpoint.logic.ProfileLogic;

@Path("/strat")
public class MainService {

	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProfileList() {
		
		ProfileLogic pl = new ProfileLogic();
		return pl.getProfileList();


	}
	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProfileList() {
		
		ProfileLogic pl = new ProfileLogic();
		return pl.getProfileList();


	}
	
	
	@GET
	@Path("/profile/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchGivenProfile(@PathParam("name") String name) {
		ProfileLogic pl = new ProfileLogic();
		
		
		return pl.searchProfileName(name);
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHTML() {
		String resource = "<h1>HELLO!!!</H1>";

		return resource;

	}
}
