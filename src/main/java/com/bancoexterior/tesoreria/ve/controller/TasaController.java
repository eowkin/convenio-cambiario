package com.bancoexterior.tesoreria.ve.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoexterior.tesoreria.ve.dto.DatosRequestConsulta;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDto;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaRequest;
import com.bancoexterior.tesoreria.ve.dto.tasa.TasaRequestConsulta;
import com.bancoexterior.tesoreria.ve.entities.Tasa;
import com.bancoexterior.tesoreria.ve.service.ITasaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/des/v1/parametros/tasas")
public class TasaController {

	@Autowired
	ITasaService tasaService;
	
	@GetMapping("/prueba")
	public List<Tasa> findAll(){
		return tasaService.findAll();
	}
	
	
	@GetMapping("/prueba1")
	public List<TasaDto> findAllTasas(){
		return tasaService.findAllDto();
	}
	
	
	@GetMapping("/chachacha")
	public ResponseEntity<Object> listAllTasas(@RequestBody TasaRequestConsulta tasaRequestConsulta, 
			HttpServletRequest requestHTTP){
		
		log.info("[==== INICIO Convenio n° 1 Tasa - Controller ====]");
		log.info("tasasRequest: " + tasaRequestConsulta);
		
		tasaService.consultaTasas(tasaRequestConsulta);
		//TasaDtoResponse response = tasaService.findAllDtoResponse();
		//log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 Monedas - Controller ====]");
		//return ResponseEntity.ok(response);
		return null;
	}
	
	@GetMapping()
	public ResponseEntity<Object> getAllTasasResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, 
			HttpServletRequest requestHTTP){
		
		log.info("[==== INICIO Convenio n° 1 Tasa - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		
		TasaDtoResponse response = tasaService.findAllDtoResponse();
		//log.info("response: "+response);
		log.info("[==== FIN Convenio n° 1 Monedas - Controller ====]");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/codMonedaOrigen/{codMonedaOrigen}/codMonedaDestino/{codMonedaDestino}")
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
