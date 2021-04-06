package com.bancoexterior.tesoreria.ve.service;

import java.util.List;

import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDto;
import com.bancoexterior.tesoreria.ve.entities.LimitesGenerales;

public interface ILimitesGeneralesService {

	public List<LimitesGenerales> findAll();
	
	public List<LimitesGeneralesDto> findAllDto();
}
