package edu.uniandes.service.ws;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uniandes.annotations.Feature;
import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

@Path("/facebook")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@LocalBean
@Feature(name="Facebook")
public class FacebookResource {

	public Response compartirFacebook(String mensaje, String urlImagen) {
		Facebook facebook = new FacebookFactory().getInstance();
    	facebook.setOAuthAppId("***********", "**************************");
    	facebook.setOAuthAccessToken(new AccessToken("************************************"));
    	ResponseList<Account> accounts;
		try {
			accounts = facebook.getAccounts();
			Account yourPageAccount = accounts.get(0); 
        	String pageAccessToken = yourPageAccount.getAccessToken();
        	facebook.setOAuthAccessToken(new AccessToken(pageAccessToken));
		} catch (FacebookException e1) {
			e1.printStackTrace();
		}
    	
    	try {
			facebook.postStatusMessage(mensaje);
		} catch (FacebookException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(mensaje).build();
	}
}
