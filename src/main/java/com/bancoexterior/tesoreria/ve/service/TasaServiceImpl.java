package com.bancoexterior.tesoreria.ve.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import com.bancoexterior.tesoreria.ve.config.Codigos.CodRespuesta;
import com.bancoexterior.tesoreria.ve.config.Codigos.Constantes;
import com.bancoexterior.tesoreria.ve.dto.Resultado;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDto;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDtoConsulta;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDtoRequest;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDtoRequestConsulta;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaRequest;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaRequestConsulta;
import com.bancoexterior.tesoreria.ve.entities.Tasa;
import com.bancoexterior.tesoreria.ve.entities.TasaPk;
import com.bancoexterior.tesoreria.ve.repository.ITasaRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class TasaServiceImpl implements ITasaService{

	@Autowired
	private ITasaRepository repo;
	
	@Autowired
	private Environment env;
	
	@Override
	public List<Tasa> findAll() {
		return repo.findAll();
	}

	@Override
	public Tasa findById(TasaPk id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<TasaDto> getTasaByCodMonedaOrigenAndCodMonedaDestino(String codMonedaOrigen, String codMonedaDestino) {
		return repo.getTasaByCodMonedaOrigenAndCodMonedaDestino(codMonedaOrigen, codMonedaDestino);
	}

	@Override
	public List<TasaDto> findAllDto() {
		return repo.getAll();
	}

	@Override
	public TasaDtoResponse getTasaByParameter(String codMonedaOrigen, String codMonedaDestino) {
		TasaDtoResponse response = new TasaDtoResponse();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		List<TasaDto> listTasasDto = repo.getTasaByCodMonedaOrigenAndCodMonedaDestino(codMonedaOrigen, codMonedaDestino);
		if (listTasasDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}else {
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}
		
		response.setResultado(resultado);
		response.setListTasasDto(listTasasDto);
		return response;
	}

	@Override
	public TasaDtoResponse findAllDtoResponse() {
		TasaDtoResponse response = new TasaDtoResponse();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		List<TasaDto> listTasasDto = repo.getAll();
		
		if (listTasasDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}else {
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}
		
		response.setResultado(resultado);
		response.setListTasasDto(listTasasDto);
		return response;
	}

	@Override
	public TasaDtoResponse consultaTasas(TasaRequestConsulta request) {
		log.info("\"==== INICIO Convenio 1 - Tasas Consultas ====\"");
		TasaDtoResponse tasaDtoResponse = new TasaDtoResponse();
		Resultado resultado = new Resultado();
		String codigo = CodRespuesta.C0000;
		String errorCM = Constantes.BLANK;
		List<TasaDto> listTasaDto;
		TasaDtoConsulta tasaDtoConsulta = new TasaDtoConsulta(request);
		TasaDtoRequestConsulta tasaDtoRequestConsulta = request.getTasaDtoRequestConsulta();
		log.info("codMonedaOrigen: "+tasaDtoRequestConsulta.getCodMonedaOrigen());
		log.info("codMonedaDestino: "+tasaDtoRequestConsulta.getCodMonedaDestino());
		try {
			log.info("antes de llamara validarDatosConsulta");
			codigo = validaDatosConsulta(request);
			log.info("codigo: "+codigo);
			if(codigo.equalsIgnoreCase(CodRespuesta.C0000)) {
				log.info("monedaDto: "+tasaDtoConsulta);	
				log.info("codMonedaOrigen: "+tasaDtoConsulta.getCodMonedaOrigen());
				log.info("codMonedaDestino: "+tasaDtoConsulta.getCodMonedaDestino());
				//consulta BD
				//listMonedasDto = this.findAllMonedasDto(monedaDto);
				//monedaDtoResponse.setListMonedasDto(listMonedasDto);
				
				//Validar Respuesta
				//resultado = validaConsulta(listMonedasDto);
				codigo = resultado.getCodigo();
				errorCM = resultado.getDescripcion();
			}
		} catch (Exception e) {
			log.error(""+e);
			codigo = CodRespuesta.CME6000;
			errorCM = Constantes.EXC+e;
		}
		
		
		return null;
	}
	
	private String validaDatosConsulta(TasaRequestConsulta request) {
		log.info("dentro de validarDatosConsulta");
		log.info(""+request);
		String codigo = CodRespuesta.C0000;
		String codMonedaOrigen;
		String codMonedaDestino;
		
		codMonedaOrigen = request.getTasaDtoRequestConsulta().getCodMonedaOrigen() == null ? "000":request.getTasaDtoRequestConsulta().getCodMonedaOrigen();
		codMonedaDestino = request.getTasaDtoRequestConsulta().getCodMonedaDestino() == null ? "000":request.getTasaDtoRequestConsulta().getCodMonedaDestino();
		
		request.getTasaDtoRequestConsulta().setCodMonedaOrigen(codMonedaOrigen);
		request.getTasaDtoRequestConsulta().setCodMonedaDestino(codMonedaDestino);
		
		
		log.info("antes de llamar factory");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<TasaRequestConsulta>> errores = validator.validate(request);
		
	
			for (ConstraintViolation<TasaRequestConsulta> cv : errores) {
				
				if ( !cv.getMessage().equalsIgnoreCase(Constantes.BLANK)) {
					codigo = cv.getMessage();
					 break;
				}

			}

		
		return codigo;
	}

}
