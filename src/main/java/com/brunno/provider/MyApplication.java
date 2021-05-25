package com.brunno.provider;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("FornecedorWebServiceRest")
public class MyApplication extends ResourceConfig{
	
	public MyApplication() {
		packages("com.brunno.provider.webservice");
	}
	
}
