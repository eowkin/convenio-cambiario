package com.bancoexterior.tesoreria.ve.exception.prueba;

public class FieldAlreadyExistException extends BadRequestException{
	private static final String DESCRIPCION = "Ya existe, esta en uso";

	public FieldAlreadyExistException(String detail) {
		super(DESCRIPCION+". "+detail);
		// TODO Auto-generated constructor stub
	}
	
	
}
