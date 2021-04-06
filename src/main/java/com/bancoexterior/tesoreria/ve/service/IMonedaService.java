package com.bancoexterior.tesoreria.ve.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.bancoexterior.tesoreria.ve.dto.MonedaDto;
import com.bancoexterior.tesoreria.ve.dto.MonedaDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.MonedasRequest;
import com.bancoexterior.tesoreria.ve.dto.Resultado;
import com.bancoexterior.tesoreria.ve.entities.Moneda;

public interface IMonedaService {

	public MonedaDtoResponse consultaMonedas(MonedasRequest request);
	//public Resultado gestionMoneda(MonedasRequest request,HttpServletRequest requestHTTP);
	
	public boolean existsById(String codMoneda); 
	public MonedaDto findById(String codMoneda);
	public MonedaDtoResponse save(MonedasRequest monedasRequest);
	public List<Moneda> findAll();
	public List<MonedaDto> findAllMonedasDto(MonedaDto monedaDto);
	public List<MonedaDto> findAllGlobalMapper();
	public MonedaDtoResponse getAll();
	public MonedaDtoResponse get(String codMoneda);
}
