package com.bancoexterior.tesoreria.ve.service;

import java.util.List;

import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDto;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDtoConsulta;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDtoRequestConsulta;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesRequestConsulta;
import com.bancoexterior.tesoreria.ve.entities.LimitesGenerales;

public interface ILimitesGeneralesService {

	public LimitesGeneralesDtoResponse consultaLimitesGenerales(LimitesGeneralesRequestConsulta request);
	
	public List<LimitesGenerales> findAll();
	
	public List<LimitesGeneralesDto> findAllDto();
	
	public List<LimitesGeneralesDto> findAllDto(LimitesGeneralesDtoConsulta limitesGeneralesDtoConsulta);
	
	public LimitesGeneralesDtoResponse findAllDtoResponse();
	
	public LimitesGeneralesDtoResponse getLimitesGeneralesByParameter(String codMoneda, String tipoTransaccion, String naturaleza);
	
	public LimitesGeneralesDtoResponse getLimitesGeneralesByAllParameter(String codMoneda, String tipoTransaccion, String naturaleza, boolean flagActivo);
}
