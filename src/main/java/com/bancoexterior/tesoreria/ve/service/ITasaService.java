package com.bancoexterior.tesoreria.ve.service;

import java.util.List;

import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDto;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDtoConsulta;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaRequest;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaRequestConsulta;
import com.bancoexterior.tesoreria.ve.entities.Tasa;
import com.bancoexterior.tesoreria.ve.entities.TasaPk;

public interface ITasaService {

	public TasaDtoResponse consultaTasas(TasaRequestConsulta tasaRequestConsulta);
	
	public List<Tasa> findAll();
	
	public Tasa findById(TasaPk id);
	
	public List<TasaDto> getTasaByCodMonedaOrigenAndCodMonedaDestino(String codMonedaOrigen, String codMonedaDestino);
	
	public TasaDtoResponse getTasaByParameter(String codMonedaOrigen, String codMonedaDestino);
	
	public TasaDtoResponse getTasaByParameter(String codMonedaOrigen);
	
	public TasaDtoResponse getTasaByParameterCodMonedaDestino(String codMonedaDestino);
	
	public List<TasaDto> findAllDto();
	
	public List<TasaDto> findAllDto(TasaDtoConsulta tasaDtoConsulta);
	
	public TasaDtoResponse findAllDtoResponse();
	
}
