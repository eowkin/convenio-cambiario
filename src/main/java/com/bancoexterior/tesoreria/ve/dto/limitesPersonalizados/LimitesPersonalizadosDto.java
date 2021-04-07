package com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor @NoArgsConstructor
public class LimitesPersonalizadosDto implements Serializable{

	private String codIbs;
	
	private String codMoneda;
	
	private String tipoTransaccion;
	
	private Double montoMin;
	
	private Double montoMax;
	
	private Double montoTope;
	
	private Double montoMensual;
	
	private Double montoDiario;
	
	private Boolean flagActivo;
	
	private String codUsuario;
	
	private Date fechaModificacion;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
