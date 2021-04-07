package com.bancoexterior.tesoreria.ve.dto.limitesGenerales;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.bancoexterior.tesoreria.ve.config.Codigos.CodRespuesta;
import com.bancoexterior.tesoreria.ve.config.Codigos.ParamConfig;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LimitesGeneralesDtoRequestConsulta implements Serializable{
	
	@JsonProperty("codMoneda")
	@NotEmpty(message=CodRespuesta.CDE1004)
	@Pattern(regexp=ParamConfig.CODMONEDA, message=CodRespuesta.CDE1004)
	private String codMoneda;
	
	@JsonProperty("tipoTransaccion")
	private String tipoTransaccion;
	
	@JsonProperty("naturaleza")
	private String naturaleza;
	
	@JsonProperty("flagActivo")
	private Boolean flagActivo;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
