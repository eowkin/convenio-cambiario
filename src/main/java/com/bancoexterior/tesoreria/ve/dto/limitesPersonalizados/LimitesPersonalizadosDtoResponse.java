package com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bancoexterior.tesoreria.ve.dto.Resultado;

import lombok.Data;

@Data
public class LimitesPersonalizadosDtoResponse implements Serializable{
	
	private Resultado resultado;
	
	private List<LimitesPersonalizadosDto> listLimitesPersonalizadosDto;
	
	public LimitesPersonalizadosDtoResponse() {
		super();
		this.resultado = new Resultado();
		listLimitesPersonalizadosDto = new ArrayList<LimitesPersonalizadosDto>();
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
