package com.bancoexterior.tesoreria.ve.dto.tasa;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor
public class TasaDtoConsulta implements Serializable{
	

	@JsonProperty("codMonedaOrigen")
	private String codMonedaOrigen;
	
	@JsonProperty("codMonedaDestino")
	private String codMonedaDestino;
	
	public TasaDtoConsulta(TasaRequestConsulta tasaRequestConsulta) {
		this.codMonedaOrigen =  tasaRequestConsulta.getTasaDtoRequestConsulta().getCodMonedaOrigen();
		this.codMonedaDestino =  tasaRequestConsulta.getTasaDtoRequestConsulta().getCodMonedaDestino();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
