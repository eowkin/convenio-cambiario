package com.bancoexterior.tesoreria.ve.dto.limitesGenerales;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.bancoexterior.tesoreria.ve.annotation.AFechaValidate;
import com.bancoexterior.tesoreria.ve.annotation.ANotEmptyValidate;
import com.bancoexterior.tesoreria.ve.config.Codigos.CodRespuesta;
import com.bancoexterior.tesoreria.ve.config.Codigos.ParamConfig;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LimitesGeneralesRequestConsulta implements Serializable{
	
	@JsonProperty("idSesion")
	@NotEmpty(message=CodRespuesta.CDE1000)
	@AFechaValidate(message=CodRespuesta.CDE1000, formato = ParamConfig.IDSESIONVALID)
	private String idSesionMR;
	
	@JsonProperty("idUsuario")
	@NotEmpty(message=CodRespuesta.CDE1001)
	@Pattern(regexp=ParamConfig.IDUSUARIO, message=CodRespuesta.CDE1001)
	private String idUsuarioMR;
	
	@JsonProperty("codUsuario")
	@NotEmpty(message=CodRespuesta.CDE1002)
	@Pattern(regexp=ParamConfig.CODUSUARIO, message=CodRespuesta.CDE1002)
	private String codUsuarioMR;
	
	@JsonProperty("canal")
	@NotEmpty(message=CodRespuesta.CDE1008)
	@Pattern(regexp=ParamConfig.CANAL, message=CodRespuesta.CDE1008)
	private String canalCM;
	
	
	@JsonProperty("limitesGenerales")
	@ANotEmptyValidate(message=CodRespuesta.CDE1003)
	@Valid
	private LimitesGeneralesDtoRequestConsulta limitesGeneralesDtoRequestConsulta;
	
	
	public LimitesGeneralesRequestConsulta() {
		super();
		limitesGeneralesDtoRequestConsulta = new LimitesGeneralesDtoRequestConsulta();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
