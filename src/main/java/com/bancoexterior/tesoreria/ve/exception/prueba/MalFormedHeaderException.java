package com.bancoexterior.tesoreria.ve.exception.prueba;

public class MalFormedHeaderException extends BadRequestException{

	private static final String DESCRIPCION = "Cuerpo mal formado";
	
	public MalFormedHeaderException(String detail) {
		super(DESCRIPCION+ ". "+   detail);
		// TODO Auto-generated constructor stub
	}

}
