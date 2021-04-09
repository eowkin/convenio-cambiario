package com.bancoexterior.tesoreria.ve.service;

import java.util.List;

import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDto;
import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDtoConsulta;
import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosRequestConsulta;
import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizados;



public interface ILimitesPersonalizadosService {

	public LimitesPersonalizadosDtoResponse consultaLimitesPersonalizados(LimitesPersonalizadosRequestConsulta limitesPersonalizadosRequestConsulta);
	
	public LimitesPersonalizadosDtoResponse findAllDtoResponse(); 
	
	public List<LimitesPersonalizados> findAll();
	
	public List<LimitesPersonalizadosDto> findAllDto();
	
	public List<LimitesPersonalizadosDto> findAllDto(LimitesPersonalizadosDtoConsulta limitesPersonalizadosDtoConsulta);
	
	public LimitesPersonalizadosDtoResponse findByIdDtoResponse(String codMoneda, String tipoTransaccion, String codIbs);
}
