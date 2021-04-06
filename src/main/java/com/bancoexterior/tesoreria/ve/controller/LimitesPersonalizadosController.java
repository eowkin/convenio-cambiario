package com.bancoexterior.tesoreria.ve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizados;
import com.bancoexterior.tesoreria.ve.service.ILimitesPersonalizadosService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/des/v1/parametros/limites-personalizados")
public class LimitesPersonalizadosController {
	
	@Autowired
	private ILimitesPersonalizadosService limitesPersonalizadosService;
	
	@GetMapping
	public List<LimitesPersonalizados> findAll(){
		return limitesPersonalizadosService.findAll();
	}
	
	
	

}
