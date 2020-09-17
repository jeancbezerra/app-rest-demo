package br.com.yaman.rest.demo.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@ApplicationPath("/api/v1/*")
public class GlobalResourceConfig extends ResourceConfig{	
	    
	    public GlobalResourceConfig() {
	    	
	    	Info infoConfiguration = new Info();
	    	infoConfiguration.title("App Rest Demo");
	    	infoConfiguration.description("Descricao do App Rest Demo");
	    	infoConfiguration.termsOfService("https://acme.ind");
	    	infoConfiguration.contact(new Contact().email("jcbm.contato@outlook.com").name("Jean Carlos Bezerra Macena da Silv "));
	    	infoConfiguration.license(new License().name("All Rights").url("http://jcbms.com.br/license"));
	    	
	    	OpenAPI oas = new OpenAPI();	    	
	    	oas.info(infoConfiguration);
	    	
	    	
	    	SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
	    	swaggerConfiguration.openAPI(oas);
	    	swaggerConfiguration.prettyPrint(true);	    	
	    	

	    	
	    	packages("br.com.yaman.rest.demo.resources","io.swagger.jaxrs.listing");	    	
	    	register(MultiPartFeature.class); //Multipart Provider
	    	register(GsonMessageBodyHandler.class); //Gson Provider
	    	register(CORSFilter.class);
	    	register(swaggerConfiguration);
	    	
	    	
	    	/*
	    	BeanConfig beanConfig = new BeanConfig();
	        beanConfig.setVersion("1.0.2");
	        beanConfig.setSchemes(new String[]{"http"});
	        beanConfig.setHost("localhost:8002");
	        beanConfig.setBasePath("/api");
	        beanConfig.setResourcePackage("io.swagger.resources");
	        beanConfig.setScan(true);
	        */
	    }
	    
	    
	    
}