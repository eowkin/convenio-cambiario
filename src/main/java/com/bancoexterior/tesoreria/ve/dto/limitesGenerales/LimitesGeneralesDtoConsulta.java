package com.bancoexterior.tesoreria.ve.dto.limitesGenerales;

import java.io.Serializable;

import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDtoConsulta;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LimitesGeneralesDtoConsulta implements Serializable{

	@JsonProperty("codMoneda")
	private String codMoneda;
	
	@JsonProperty("tipoTransaccion")
	private String tipoTransaccion;
	
	@JsonProperty("naturaleza")
	private String naturaleza;
	
	@JsonProperty("flagActivo")
	private Boolean flagActivo;
	
	
	public LimitesGeneralesDtoConsulta(LimitesGeneralesRequestConsulta limitesGenerealesRequestConsulta) {
		this.codMoneda = limitesGenerealesRequestConsulta.getLimitesGeneralesDtoRequestConsulta().getCodMoneda();
		this.tipoTransaccion = limitesGenerealesRequestConsulta.getLimitesGeneralesDtoRequestConsulta().getTipoTransaccion();
		this.naturaleza = limitesGenerealesRequestConsulta.getLimitesGeneralesDtoRequestConsulta().getNaturaleza();
		this.flagActivo = limitesGenerealesRequestConsulta.getLimitesGeneralesDtoRequestConsulta().getFlagActivo();
		
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
