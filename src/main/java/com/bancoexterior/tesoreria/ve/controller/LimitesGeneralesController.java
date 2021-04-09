package com.bancoexterior.tesoreria.ve.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoexterior.tesoreria.ve.config.Codigos.Constantes;
import com.bancoexterior.tesoreria.ve.config.Codigos.Servicios;
import com.bancoexterior.tesoreria.ve.dto.DatosRequestConsulta;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDto;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDtoRequestConsulta;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesRequestConsulta;
import com.bancoexterior.tesoreria.ve.entities.LimitesGenerales;
import com.bancoexterior.tesoreria.ve.service.ILimitesGeneralesService;
import com.bancoexterior.tesoreria.ve.util.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("/api/des/v1/parametros/limites")
@RequestMapping("${microservicio.path.pre}" + "${microservicio.ambiente}")
public class LimitesGeneralesController {

	@Autowired
	private ILimitesGeneralesService limitesService;
	
	@GetMapping(path =Servicios.LIMITESGENERALESURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listLimitesGeneralesResponse(@RequestBody LimitesGeneralesRequestConsulta limitesGeneralesRequestConsulta, 
			HttpServletRequest requestHTTP){
		log.info("[==== INICIO Convenio n° 1 LimitesGenerales - Controller ====]");
		log.info("datosRequestConsulta: " + limitesGeneralesRequestConsulta);
		LimitesGeneralesDtoResponse response;
		HttpStatus estatusCM;
		response = limitesService.consultaLimitesGenerales(limitesGeneralesRequestConsulta);
		estatusCM = Utils.getHttpStatus(response.getResultado().getCodigo().trim());
		
		log.info("estatusCM: "+estatusCM);
		log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 LimitesGenerales - Controller ====]");
		if(response.getResultado().getCodigo().trim().substring(0, 1).equalsIgnoreCase(Constantes.SUBSTRING_COD_OK)) {
			return new ResponseEntity<>(response,estatusCM);
		}else {
		
			return new ResponseEntity<>(response.getResultado(),estatusCM);
		}
	}
	
	
	@GetMapping(path =Servicios.LIMITESGENERALESURLV1+"/todas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllLimitesGeneralesResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, 
			HttpServletRequest requestHTTP){
		log.info("[==== INICIO Convenio n° 1 LimitesGenerales - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		LimitesGeneralesDtoResponse response  = limitesService.findAllDtoResponse();
		log.info("[==== FIN Convenio n° 1 LimitesGenerales - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	//@GetMapping("/codMoneda/{codMoneda}/tipoTransaccion/{tipoTransaccion}/naturaleza/{naturaleza}")
	@GetMapping(path =Servicios.LIMITESGENERALESPARAMETERIDURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getIdLimitesGeneralesResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, @PathVariable String codMoneda,
			@PathVariable String tipoTransaccion,@PathVariable String naturaleza, 
			HttpServletRequest requestHTTP){
		log.info("[==== INICIO Convenio n° 1 LimitesGenerales - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMoneda: "+codMoneda);
		log.info("tipoTransaccion: "+tipoTransaccion);
		log.info("naturaleza: "+naturaleza);
		LimitesGeneralesDtoResponse response  = limitesService.getLimitesGeneralesByParameter(codMoneda, tipoTransaccion, naturaleza);
		log.info("[==== FIN Convenio n° 1 LimitesGenerales - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(path =Servicios.LIMITESGENERALESALLPARAMETERURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllParameterLimitesGeneralesResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, @PathVariable String codMoneda,
			@PathVariable String tipoTransaccion,@PathVariable String naturaleza, @PathVariable boolean flagActivo, 
			HttpServletRequest requestHTTP){
		log.info("[==== INICIO Convenio n° 1 LimitesGenerales - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMoneda: "+codMoneda);
		log.info("tipoTransaccion: "+tipoTransaccion);
		log.info("naturaleza: "+naturaleza);
		log.info("flagActivo: "+flagActivo);
		LimitesGeneralesDtoResponse response  = limitesService.getLimitesGeneralesByAllParameter(codMoneda, tipoTransaccion, naturaleza, flagActivo);
		log.info("[==== FIN Convenio n° 1 LimitesGenerales - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	
	
	@GetMapping("/prueba")
	public List<LimitesGenerales> findAll(){
		return limitesService.findAll();
	}
	
	@GetMapping("/dto")
	public List<LimitesGeneralesDto> findAllDto(){
		return limitesService.findAllDto();
	}
	
	
}
