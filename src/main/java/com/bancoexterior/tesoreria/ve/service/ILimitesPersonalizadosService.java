package com.bancoexterior.tesoreria.ve.service;

import java.util.List;

import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDto;
import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizados;



public interface ILimitesPersonalizadosService {

	public LimitesPersonalizadosDtoResponse findAllDtoResponse(); 
	
	public List<LimitesPersonalizados> findAll();
	
	public List<LimitesPersonalizadosDto> findAllDto();
}
