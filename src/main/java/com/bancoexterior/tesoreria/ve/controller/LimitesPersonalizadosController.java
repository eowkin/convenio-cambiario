package com.bancoexterior.tesoreria.ve.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoexterior.tesoreria.ve.config.Codigos.Servicios;
import com.bancoexterior.tesoreria.ve.dto.DatosRequestConsulta;
import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDto;
import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDtoResponse;
import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizados;
import com.bancoexterior.tesoreria.ve.service.ILimitesPersonalizadosService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("/api/des/v1/parametros/limites-personalizados")
@RequestMapping("${microservicio.path.pre}" + "${microservicio.ambiente}")
public class LimitesPersonalizadosController {
	
	@Autowired
	private ILimitesPersonalizadosService limitesPersonalizadosService;
	
	
	@GetMapping(path =Servicios.LIMITESUSUARIOSURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllLimitesPersonalizadosResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta, 
			HttpServletRequest requestHTTP){
		log.info("[==== INICIO Convenio n° 1 LimitesPersonalizados - Controller ====]");
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		LimitesPersonalizadosDtoResponse response = limitesPersonalizadosService.findAllDtoResponse();
		log.info("[==== FIN Convenio n° 1 LimitesPersonalizados - Controller ====]");
		return ResponseEntity.ok(response);
	}

	
	
	@GetMapping("/pruebaLimites")
	public List<LimitesPersonalizados> findAll(){
		return limitesPersonalizadosService.findAll();
	}
	
	@GetMapping("/dtoLimites")
	public List<LimitesPersonalizadosDto> findAllDto(){
		return limitesPersonalizadosService.findAllDto();
	}
	

}
