package com.bancoexterior.tesoreria.ve.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Embeddable
@Data
public class LimitesGeneralesPk implements Serializable{
	 
	@NotEmpty(message = "no puede ser vacio")
	@Column(name= "cod_moneda",nullable = false)
	@Size(min = 3, max = 3)
	private String codMoneda;

	@NotEmpty(message = "no puede ser vacio")
	@Column(name= "tipo_transaccion", nullable = false)
	@Size(min = 1, max = 1)
	private String tipoTransaccion;
	 
	@NotEmpty(message = "no puede ser vacio")
	@Column(name= "naturaleza", nullable = false)
	@Size(min = 1, max = 1)
	private String naturaleza; 
		 
	
	
	private static final long serialVersionUID = 1L;

}
