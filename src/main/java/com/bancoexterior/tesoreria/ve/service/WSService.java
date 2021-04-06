package com.bancoexterior.tesoreria.ve.service;

import javax.net.ssl.HostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;



import com.bancoexterior.tesoreria.ve.config.Codigos.Constantes;
import com.bancoexterior.tesoreria.ve.config.Codigos.ExceptionMessages;
import com.bancoexterior.tesoreria.ve.interfase.IWSService;
import com.bancoexterior.tesoreria.ve.model.WSRequest;
import com.bancoexterior.tesoreria.ve.model.WSResponse;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class WSService implements IWSService{
	
	 
	 private static final boolean VERIFYSSL = false;
	 private static final HostnameVerifier VERIFIER = NoopHostnameVerifier.INSTANCE;
	 
	 @Override
	 public WSResponse post(WSRequest request) {
		
		 HttpResponse<String> retorno = null;
		 WSResponse response;
		 
		 try {
			 initUniRest (request.getSocketTimeout(),request.getConnectTimeout() );
			 retorno = Unirest.post(request.getUrl()) 
			  .header(Constantes.CONTENT_TYPE, request.getContenType())
			  .header(Constantes.ACCEPT_CHARSET, Constantes.UTF8)
			  .body(request.getBody()).asString();
			 response = new WSResponse(retorno);
			 
	
		} catch (HttpStatusCodeException e) {
			log.error(String.format(ExceptionMessages.UNIRESTHTTPE ,e));
			log.error(String.format(ExceptionMessages.UNIRESTBODYE ,e.getResponseBodyAsString()));
			response = new WSResponse(e);
		}catch (Exception e) {
			log.error(String.format(ExceptionMessages.UNIRESTE ,e));
			response = new WSResponse(e);
		}	

		 return response;
	 }
	 
	 
	 /**
		 * Nombre:                  initUniRest
		 * Descripcion:             Inicializar Objeto Unirest
		 *
		 * @version 1.0
		 * @author Wilmer Vieira
		 * @since 13/10/20
		 */
     
		private void initUniRest (int socketTimeout, int connectTimeout ) {
			Unirest.config().reset();
			Unirest.config()
		    .socketTimeout(socketTimeout)
		    .connectTimeout(connectTimeout)
			.verifySsl(VERIFYSSL)
			.hostnameVerifier(VERIFIER);
			
		}

}
