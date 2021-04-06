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
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDto;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaRequest;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaRequestConsulta;
import com.bancoexterior.tesoreria.ve.entities.Tasa;
import com.bancoexterior.tesoreria.ve.service.ITasaService;
import com.bancoexterior.tesoreria.ve.util.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("/api/des/v1/parametros/tasas")
@RequestMapping("${microservicio.path.pre}" + "${microservicio.ambiente}")
public class TasaController {

	@Autowired
	ITasaService tasaService;
	
	@GetMapping("/AllTasasPrueba")
	public List<Tasa> findAll(){
		return tasaService.findAll();
	}
	
	
	@GetMapping("/AllTasasPrueba1")
	public List<TasaDto> findAllTasas(){
		return tasaService.findAllDto();
	}
	
	
	@GetMapping(path =Servicios.TASASURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listAllTasas(@RequestBody TasaRequestConsulta tasaRequestConsulta, 
			HttpServletRequest requestHTTP){
		
		log.info("[==== INICIO Convenio n° 1 Tasa - Controller ====]");
		log.info("tasasRequest: " + tasaRequestConsulta);
		TasaDtoResponse response;
		HttpStatus estatusCM;
		
		response = tasaService.consultaTasas(tasaRequestConsulta);
		estatusCM = Utils.getHttpStatus(response.getResultado().getCodigo().trim());
		//TasaDtoResponse response = tasaService.findAllDtoResponse();
		//log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 Monedas - Controller ====]");
		log.info("estatusCM: "+estatusCM);
		log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 Monedas - Controller ====]");
		if(response.getResultado().getCodigo().trim().substring(0, 1).equalsIgnoreCase(Constantes.SUBSTRING_COD_OK)) {
			return new ResponseEntity<>(response,estatusCM);
		}else {
		
			return new ResponseEntity<>(response.getResultado(),estatusCM);
		}
	}
	
	//@GetMapping("/prueba3")
	@GetMapping(path =Servicios.TASASURLV1+"/todas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllTasasResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, 
			HttpServletRequest requestHTTP){
		
		log.info("[==== INICIO Convenio n° 1 Tasa - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		
		TasaDtoResponse response = tasaService.findAllDtoResponse();
		//log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 Monedas - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	//@GetMapping("/codMonedaOrigen/{codMonedaOrigen}/codMonedaDestino/{codMonedaDestino}")
	@GetMapping(path =Servicios.TASASPARAMETERURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getTasasResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, @PathVariable String codMonedaOrigen, @PathVariable String codMonedaDestino,
			HttpServletRequest requestHTTP){
		
		log.info("[==== INICIO Convenio n° 1 Tasa - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMonedaOrigen: " + codMonedaOrigen);
		log.info("codMonedaDestino: " + codMonedaDestino);
		
		TasaDtoResponse response = tasaService.getTasaByParameter(codMonedaOrigen, codMonedaDestino);
		log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 Monedas - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(path =Servicios.TASASPARAMETERCODMONEDAORIGENURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getTasasByCodMonedaOrigenResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, @PathVariable String codMonedaOrigen, 
			HttpServletRequest requestHTTP){
		
		log.info("[==== INICIO Convenio n° 1 Tasa - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMonedaOrigen: " + codMonedaOrigen);
		
		
		TasaDtoResponse response = tasaService.getTasaByParameter(codMonedaOrigen);
		log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 Monedas - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(path =Servicios.TASASPARAMETERCODMONEDADESTINOURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getTasasByCodMonedaDestinoResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, @PathVariable String codMonedaDestino, 
			HttpServletRequest requestHTTP){
		
		log.info("[==== INICIO Convenio n° 1 Tasa - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMonedaOrigen: " + codMonedaDestino);
		
		
		TasaDtoResponse response = tasaService.getTasaByParameterCodMonedaDestino(codMonedaDestino);
		log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 Monedas - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/prueba/codMonedaOrigen/{codMonedaOrigen}/codMonedaDestino/{codMonedaDestino}")
	public List<TasaDto> getTasas(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, @PathVariable String codMonedaOrigen, @PathVariable String codMonedaDestino,
			HttpServletRequest requestHTTP){
		
		log.info("[==== INICIO Convenio n° 1 Monedas - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMonedaOrigen: " + codMonedaOrigen);
		log.info("codMonedaDestino: " + codMonedaDestino);
		
		return tasaService.getTasaByCodMonedaOrigenAndCodMonedaDestino(codMonedaOrigen, codMonedaDestino);
	}
}
