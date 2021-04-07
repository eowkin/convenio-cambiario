package com.bancoexterior.tesoreria.ve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bancoexterior.tesoreria.ve.config.Codigos.CodRespuesta;
import com.bancoexterior.tesoreria.ve.config.Codigos.Constantes;
import com.bancoexterior.tesoreria.ve.dto.Resultado;
import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDto;
import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizados;
import com.bancoexterior.tesoreria.ve.repository.ILimitesPersonalizadosRepository;



@Service
public class LimitesPersonalizadosServiceImpl implements ILimitesPersonalizadosService{

	@Autowired
	private ILimitesPersonalizadosRepository repo;
	
	@Autowired
	private Environment env;
	
	@Override
	public List<LimitesPersonalizados> findAll() {
		return repo.findAll();
	}

	@Override
	public List<LimitesPersonalizadosDto> findAllDto() {
		// TODO Auto-generated method stub
		return repo.getAll();
	}

	@Override
	public LimitesPersonalizadosDtoResponse findAllDtoResponse() {
		LimitesPersonalizadosDtoResponse response = new LimitesPersonalizadosDtoResponse();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		List<LimitesPersonalizadosDto> listLimitesPersonalizadosDto = repo.getAll();
		
		if (listLimitesPersonalizadosDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}else {
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}
		response.setResultado(resultado);
		response.setListLimitesPersonalizadosDto(listLimitesPersonalizadosDto);
		return response;
	}

}
