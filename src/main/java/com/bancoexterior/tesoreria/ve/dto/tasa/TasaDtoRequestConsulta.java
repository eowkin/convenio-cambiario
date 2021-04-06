package com.bancoexterior.tesoreria.ve.dto.tasa;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.bancoexterior.tesoreria.ve.config.Codigos.CodRespuesta;
import com.bancoexterior.tesoreria.ve.config.Codigos.ParamConfig;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class TasaDtoRequestConsulta implements Serializable{
	

	@JsonProperty("codMonedaOrigen")
	@NotEmpty(message=CodRespuesta.CDE1009)
	@Pattern(regexp=ParamConfig.CODMONEDA, message=CodRespuesta.CDE1009)
	private String codMonedaOrigen;
	
	@JsonProperty("codMonedaDestino")
	@NotEmpty(message=CodRespuesta.CDE1010)
	@Pattern(regexp=ParamConfig.CODMONEDA, message=CodRespuesta.CDE1010)
	private String codMonedaDestino;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
