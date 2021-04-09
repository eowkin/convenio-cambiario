package com.bancoexterior.tesoreria.ve.exception.prueba;

public class FieldInvalidException extends BadRequestException{
	private static final String DESCRIPCION = "Campo invalido";
	
	public FieldInvalidException(String detail) {
		super(DESCRIPCION+". "+detail);
		// TODO Auto-generated constructor stub
	}

	
	

}
