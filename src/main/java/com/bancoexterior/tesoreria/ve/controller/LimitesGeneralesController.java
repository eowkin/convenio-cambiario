package com.bancoexterior.tesoreria.ve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoexterior.tesoreria.ve.entities.LimitesGenerales;
import com.bancoexterior.tesoreria.ve.service.ILimitesGeneralesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/des/v1/parametros/limites")
public class LimitesGeneralesController {

	@Autowired
	private ILimitesGeneralesService limitesService;
	
	@GetMapping
	public List<LimitesGenerales> findAll(){
		return limitesService.findAll();
	}
}
