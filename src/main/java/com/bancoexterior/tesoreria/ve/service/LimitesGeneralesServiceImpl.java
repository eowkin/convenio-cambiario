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
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDto;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDtoConsulta;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDtoRequestConsulta;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesRequestConsulta;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDto;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaRequestConsulta;
import com.bancoexterior.tesoreria.ve.entities.LimitesGenerales;
import com.bancoexterior.tesoreria.ve.repository.ILimitesGeneralesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LimitesGeneralesServiceImpl implements ILimitesGeneralesService{

	@Autowired
	private ILimitesGeneralesRepository repo;
	
	@Autowired
	private Environment env;
	
	@Override
	public List<LimitesGenerales> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<LimitesGeneralesDto> findAllDto() {
		// TODO Auto-generated method stub
		return repo.getAll();
	}

	@Override
	public LimitesGeneralesDtoResponse findAllDtoResponse() {
		LimitesGeneralesDtoResponse response = new LimitesGeneralesDtoResponse();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		List<LimitesGeneralesDto> listlimitesGeneralesDto = repo.getAll();
		
		if(listlimitesGeneralesDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}else {
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}
		response.setResultado(resultado);
		response.setListLimitesGeneralesDto(listlimitesGeneralesDto);
		return response;
	}

	@Override
	public LimitesGeneralesDtoResponse getLimitesGeneralesByParameter(String codMoneda, String tipoTransaccion,
			String naturaleza) {
		LimitesGeneralesDtoResponse response = new LimitesGeneralesDtoResponse();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		List<LimitesGeneralesDto> listlimitesGeneralesDto = repo.getById(codMoneda, tipoTransaccion, naturaleza);
		
		if(listlimitesGeneralesDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}else {
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}
		response.setResultado(resultado);
		response.setListLimitesGeneralesDto(listlimitesGeneralesDto);
		return response;
	}

	@Override
	public LimitesGeneralesDtoResponse getLimitesGeneralesByAllParameter(String codMoneda, String tipoTransaccion,
			String naturaleza, boolean flagActivo) {
		LimitesGeneralesDtoResponse response = new LimitesGeneralesDtoResponse();
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		List<LimitesGeneralesDto> listlimitesGeneralesDto = repo.getByCodMonedaAndTipoTransaccionAndNaturalezaAndFlagActivo(codMoneda, tipoTransaccion, naturaleza, flagActivo);
		
		if(listlimitesGeneralesDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}else {
			resultado.setDescripcion(env.getProperty(Constantes.RES + resultado.getCodigo(), resultado.getCodigo()));
		}
		response.setResultado(resultado);
		response.setListLimitesGeneralesDto(listlimitesGeneralesDto);
		return response;
	}

	@Override
	public List<LimitesGeneralesDto> findAllDto(LimitesGeneralesDtoConsulta limitesGeneralesDtoConsulta) {
		List<LimitesGeneralesDto> limitesGeneralesDto = null;
		log.info("findAllDto");
		log.info("limitesGeneralesDtoConsulta: "+limitesGeneralesDtoConsulta);
		log.info("limitesGeneralesDtoConsulta.getCodMoneda(): "+limitesGeneralesDtoConsulta.getCodMoneda());
		log.info("limitesGeneralesDtoConsulta.getTipoTransaccion(): "+limitesGeneralesDtoConsulta.getTipoTransaccion());
		log.info("limitesGeneralesDtoConsulta.getNaturaleza(): "+limitesGeneralesDtoConsulta.getNaturaleza());
		log.info("limitesGeneralesDtoConsulta.getFlagActivo(): "+limitesGeneralesDtoConsulta.getFlagActivo());
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() != null && limitesGeneralesDtoConsulta.getTipoTransaccion() != null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() != null && limitesGeneralesDtoConsulta.getFlagActivo() != null) {
			limitesGeneralesDto = repo.getAll();
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() != null && limitesGeneralesDtoConsulta.getTipoTransaccion() != null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() != null && limitesGeneralesDtoConsulta.getFlagActivo() == null) {
			limitesGeneralesDto = repo.getById(limitesGeneralesDtoConsulta.getCodMoneda(), limitesGeneralesDtoConsulta.getTipoTransaccion(), limitesGeneralesDtoConsulta.getNaturaleza());
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() != null && limitesGeneralesDtoConsulta.getTipoTransaccion() != null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() == null && limitesGeneralesDtoConsulta.getFlagActivo() == null) {
			limitesGeneralesDto = repo.getByCodMonedaAndTipoTransaccion(limitesGeneralesDtoConsulta.getCodMoneda(), limitesGeneralesDtoConsulta.getTipoTransaccion());
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() != null && limitesGeneralesDtoConsulta.getTipoTransaccion() == null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() == null && limitesGeneralesDtoConsulta.getFlagActivo() == null) {
			limitesGeneralesDto = repo.getByCodMoneda(limitesGeneralesDtoConsulta.getCodMoneda());
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() == null && limitesGeneralesDtoConsulta.getTipoTransaccion() != null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() != null && limitesGeneralesDtoConsulta.getFlagActivo() == null) {
			limitesGeneralesDto = repo.getByTipoTrasaccionAndNaturaleza(limitesGeneralesDtoConsulta.getTipoTransaccion(), limitesGeneralesDtoConsulta.getNaturaleza());
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() == null && limitesGeneralesDtoConsulta.getTipoTransaccion() != null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() != null && limitesGeneralesDtoConsulta.getFlagActivo() != null) {
			limitesGeneralesDto = repo.getByTipoTrasaccionAndNaturalezaAndFlagActivo(limitesGeneralesDtoConsulta.getTipoTransaccion(), limitesGeneralesDtoConsulta.getNaturaleza(), limitesGeneralesDtoConsulta.getFlagActivo());
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() == null && limitesGeneralesDtoConsulta.getTipoTransaccion() == null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() != null && limitesGeneralesDtoConsulta.getFlagActivo() == null) {
			limitesGeneralesDto = repo.getByNaturaleza(limitesGeneralesDtoConsulta.getNaturaleza());
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() != null && limitesGeneralesDtoConsulta.getTipoTransaccion() == null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() != null && limitesGeneralesDtoConsulta.getFlagActivo() == null) {
			limitesGeneralesDto = repo.getByCodMonedaAndNaturaleza(limitesGeneralesDtoConsulta.getCodMoneda(), limitesGeneralesDtoConsulta.getNaturaleza());
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() == null && limitesGeneralesDtoConsulta.getTipoTransaccion() == null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() != null && limitesGeneralesDtoConsulta.getFlagActivo() != null) {
			limitesGeneralesDto = repo.getByNaturalezaAndFlagActivo(limitesGeneralesDtoConsulta.getNaturaleza(), limitesGeneralesDtoConsulta.getFlagActivo());
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() == null && limitesGeneralesDtoConsulta.getTipoTransaccion() == null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() == null && limitesGeneralesDtoConsulta.getFlagActivo() != null) {
			limitesGeneralesDto = repo.getByFlagActivo(limitesGeneralesDtoConsulta.getFlagActivo());
			log.info(""+limitesGeneralesDto.size());
		}
		
		if (limitesGeneralesDtoConsulta.getCodMoneda() != null && limitesGeneralesDtoConsulta.getTipoTransaccion() == null 
				&& limitesGeneralesDtoConsulta.getNaturaleza() == null && limitesGeneralesDtoConsulta.getFlagActivo() != null) {
			limitesGeneralesDto = repo.getByCodMonedaAndFlagActivo(limitesGeneralesDtoConsulta.getCodMoneda(), limitesGeneralesDtoConsulta.getFlagActivo());
			log.info(""+limitesGeneralesDto.size());
		}
		return limitesGeneralesDto;
	}
	
	
	@Override
	public LimitesGeneralesDtoResponse consultaLimitesGenerales(
			LimitesGeneralesRequestConsulta request) {
		log.info("\"==== INICIO Convenio 1 - LimitesGenerales Consultas ====\"");
		LimitesGeneralesDtoResponse response = new LimitesGeneralesDtoResponse();
		Resultado resultado = new Resultado();
		String codigo = CodRespuesta.C0000;
		String errorCM = Constantes.BLANK;
		List<LimitesGeneralesDto> listLimitesGeneralesDto;
		LimitesGeneralesDtoConsulta limitesGeneralesDtoConsulta = new LimitesGeneralesDtoConsulta(request);
		LimitesGeneralesDtoRequestConsulta limitesGeneralesDtoRequestConsulta = request.getLimitesGeneralesDtoRequestConsulta();
		log.info("codMoneda: "+limitesGeneralesDtoRequestConsulta.getCodMoneda());
		log.info("tipoTransaccion: "+limitesGeneralesDtoRequestConsulta.getTipoTransaccion());
		log.info("naturaleza: "+limitesGeneralesDtoRequestConsulta.getNaturaleza());
		log.info("flagActivo: "+limitesGeneralesDtoRequestConsulta.getFlagActivo());
		
		try {
			log.info("antes de llamara validarDatosConsulta");
			codigo = validaDatosConsulta(request);
			log.info("codigo: "+codigo);
			if(codigo.equalsIgnoreCase(CodRespuesta.C0000)) {
				log.info("codMonedaDto: "+limitesGeneralesDtoConsulta.getCodMoneda());
				log.info("tipoTransaccionDto: "+limitesGeneralesDtoConsulta.getTipoTransaccion());
				log.info("naturalezaDto: "+limitesGeneralesDtoConsulta.getNaturaleza());
				log.info("flagActivoDto: "+limitesGeneralesDtoConsulta.getFlagActivo());
				
				//consulta BD
				listLimitesGeneralesDto = this.findAllDto(limitesGeneralesDtoConsulta);
				response.setListLimitesGeneralesDto(listLimitesGeneralesDto);
				log.info("antes de llamara validaConsulta");
				//Validar Respuesta
				resultado = validaConsulta(listLimitesGeneralesDto);
				codigo = resultado.getCodigo();
				errorCM = resultado.getDescripcion();
			}
			
			
		} catch (Exception e) {
			log.error(""+e);
			codigo = CodRespuesta.CME6000;
			errorCM = Constantes.EXC+e;
		}
		response.getResultado().setCodigo(codigo);
		response.getResultado().setDescripcion(env.getProperty(Constantes.RES+codigo,codigo).replace(Constantes.ERROR, errorCM));
		
		log.info("tasaDtoResponse: "+response);
		log.info("==== FIN Convenio 1 - Tasa Consultas ====");
		return response;
	}
	
	private String validaDatosConsulta(LimitesGeneralesRequestConsulta request) {
		log.info("dentro de validarDatosConsulta");
		log.info(""+request);
		String codigo = CodRespuesta.C0000;
		String codMoneda;
		String tipoTransaccion;
		String naturaleza;
		boolean flagActivo;
		
		
		codMoneda = request.getLimitesGeneralesDtoRequestConsulta().getCodMoneda() == null ? "000":request.getLimitesGeneralesDtoRequestConsulta().getCodMoneda();
		tipoTransaccion = request.getLimitesGeneralesDtoRequestConsulta().getTipoTransaccion() == null ? "000":request.getLimitesGeneralesDtoRequestConsulta().getTipoTransaccion();
		naturaleza = request.getLimitesGeneralesDtoRequestConsulta().getNaturaleza() == null ? "000":request.getLimitesGeneralesDtoRequestConsulta().getNaturaleza();
		flagActivo = request.getLimitesGeneralesDtoRequestConsulta().getFlagActivo() == null ? Boolean.parseBoolean(Constantes.TRUE) : request.getLimitesGeneralesDtoRequestConsulta().getFlagActivo();
		
		
		request.getLimitesGeneralesDtoRequestConsulta().setCodMoneda(codMoneda);
		request.getLimitesGeneralesDtoRequestConsulta().setTipoTransaccion(tipoTransaccion);
		request.getLimitesGeneralesDtoRequestConsulta().setNaturaleza(naturaleza);
		request.getLimitesGeneralesDtoRequestConsulta().setFlagActivo(flagActivo);
		
		log.info("antes de llamar factory");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<LimitesGeneralesRequestConsulta>> errores = validator.validate(request);
		
	
			for (ConstraintViolation<LimitesGeneralesRequestConsulta> cv : errores) {
				
				if ( !cv.getMessage().equalsIgnoreCase(Constantes.BLANK)) {
					codigo = cv.getMessage();
					 break;
				}

			}

		
		return codigo;
	}

	
	
	/**
     * Nombre:                  validaConsulta
     * Descripcion:             Metodo para evaluar el resultado de la consulta de las monedas
     *
     * @param  Objeto List<MonedasBD>
     * @return Resultado  Objeto con la información de la evaluacion.
     * @version 1.0
     * @author Wilmer Vieira
	 * @since 16/03/21
    */ 
	
	private Resultado validaConsulta(List<LimitesGeneralesDto> listLimitesGeneralesDto) {
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		
		if(listLimitesGeneralesDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			return resultado;
		}

		/*
	    if(monedasBD.get(0).getCodMonedaBD().equalsIgnoreCase(Constantes.SERROR)) {
	    	resultado.setCodigo(CodRespuesta.CME6002);
	    	resultado.setDescripcion(monedasBD.get(0).getDescripcionBD());
	    	 LOGGER.error(resultado);
	    	return resultado;
	    }*/

	    
	    log.info(""+resultado);
		return resultado;
		
	}

	

}
