package com.bancoexterior.tesoreria.ve.dto.tasa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bancoexterior.tesoreria.ve.dto.Resultado;

import lombok.Data;


@Data
public class TasaDtoResponse implements Serializable{

	private Resultado resultado;
	
	private List<TasaDto> listTasasDto;
	
	public TasaDtoResponse() {
		super();
		this.resultado = new Resultado();
		this.listTasasDto = new ArrayList<TasaDto>();
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
