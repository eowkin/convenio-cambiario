package com.bancoexterior.tesoreria.ve.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.bancoexterior.tesoreria.ve.config.Codigos.CodRespuesta;
import com.bancoexterior.tesoreria.ve.config.Codigos.Constantes;
import com.bancoexterior.tesoreria.ve.config.Codigos.Servicios;
import com.bancoexterior.tesoreria.ve.dto.DatosRequestConsulta;
import com.bancoexterior.tesoreria.ve.dto.MonedaDto;
import com.bancoexterior.tesoreria.ve.dto.MonedaDtoResponse;
import com.bancoexterior.tesoreria.ve.dto.MonedasDtoRequest;
import com.bancoexterior.tesoreria.ve.dto.MonedasRequest;
import com.bancoexterior.tesoreria.ve.dto.Resultado;
import com.bancoexterior.tesoreria.ve.entities.Moneda;
import com.bancoexterior.tesoreria.ve.exception.prueba.FieldAlreadyExistException;
import com.bancoexterior.tesoreria.ve.service.IMonedaService;
import com.bancoexterior.tesoreria.ve.util.Mapper;
import com.bancoexterior.tesoreria.ve.util.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("/api/monedas")
@RequestMapping("${microservicio.path.pre}" + "${microservicio.ambiente}")
public class MonedaController {

	@Autowired
	IMonedaService monedaService;

	@Autowired
	private Environment env;

	@Autowired
	private Mapper mapper;

	
	
	@GetMapping(path = Servicios.MONEDASURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	//public ResponseEntity<Object> listAllMonedas(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta,
	public ResponseEntity<Object> listAllMonedas(@RequestBody MonedasRequest monedasRequest,
			HttpServletRequest requestHTTP) {
		
		//int valor = 1/0;
		
		log.info(Servicios.MONEDASCONTROLLERI);
		log.info("MonedasRequest: " + monedasRequest);
		MonedaDtoResponse monedaDtoResponse;
		HttpStatus estatusCM;
		
		monedaDtoResponse = monedaService.consultaMonedas(monedasRequest);
		estatusCM = Utils.getHttpStatus(monedaDtoResponse.getResultado().getCodigo().trim());
		//monedaDtoResponse = monedaService.getAll();
		log.info("estatusCM: "+estatusCM);
		log.info("monedaDtoResponse: "+monedaDtoResponse);
		log.info(Servicios.MONEDASCONTROLLERF);
		if(monedaDtoResponse.getResultado().getCodigo().trim().substring(0, 1).equalsIgnoreCase(Constantes.SUBSTRING_COD_OK)) {
			return new ResponseEntity<>(monedaDtoResponse,estatusCM);
		}else {
		
			return new ResponseEntity<>(monedaDtoResponse.getResultado(),estatusCM);
		}
	}
	
	
	@GetMapping(path = Servicios.MONEDASURLV1+"/todas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getAllMonedaResponse(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta,
			HttpServletRequest requestHTTP) {
		
		log.info(Servicios.MONEDASCONTROLLERI);
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		MonedaDtoResponse monedaDtoResponse;
		monedaDtoResponse = monedaService.findAllDtoResponse();
		
		log.info("monedaDtoResponse: "+monedaDtoResponse);
		log.info(Servicios.MONEDASCONTROLLERF);		return ResponseEntity.ok(monedaDtoResponse);
	}
	
	
	
	@GetMapping(path = Servicios.MONEDASPARAMURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getMonedaByParams(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta,
			@PathVariable("codMoneda") String codMoneda, @PathVariable("flagActivo") boolean flagActivo, HttpServletRequest requestHTTP) {
		
		log.info(Servicios.MONEDASCONTROLLERI);
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMoneda: " + codMoneda);
		log.info("flagAvtivo: " + flagActivo);

		MonedaDtoResponse monedaDtoResponse;
		monedaDtoResponse = monedaService.getMonedasByParameter(codMoneda, flagActivo);
		
		log.info("monedaDtoResponse: "+monedaDtoResponse);
		log.info(Servicios.MONEDASCONTROLLERF);		return ResponseEntity.ok(monedaDtoResponse);
	}
	
	@GetMapping(path = Servicios.MONEDAIDURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getMonedaByCodMoneda(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta,
			@PathVariable("codMoneda") String codMoneda, HttpServletRequest requestHTTP) {
		
		log.info(Servicios.MONEDASCONTROLLERI);
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMoneda: " + codMoneda);

		MonedaDtoResponse monedaDtoResponse = new MonedaDtoResponse();
		monedaDtoResponse = monedaService.get(codMoneda);
		
		log.info("monedaDtoResponse: "+monedaDtoResponse);
		log.info(Servicios.MONEDASCONTROLLERF);		return ResponseEntity.ok(monedaDtoResponse);
	}
	
	@GetMapping(path = Servicios.MONEDASFLAGACTIVOURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getMonedasByFlagActivo(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta,
			@PathVariable("flagActivo") boolean flagActivo, HttpServletRequest requestHTTP) {
		
		log.info(Servicios.MONEDASCONTROLLERI);
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMoneda: " + flagActivo);

		MonedaDtoResponse monedaDtoResponse = new MonedaDtoResponse();
		monedaDtoResponse = monedaService.getMonedasByFlagActivo(flagActivo);
		
		log.info("monedaDtoResponse: "+monedaDtoResponse);
		log.info(Servicios.MONEDASCONTROLLERF);		return ResponseEntity.ok(monedaDtoResponse);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
     * Nombre:                  monedaIngresar
     * Descripcion:             Invocar metodo para ingresar moneda nueva
     *
     * @param  request Objeto tipo MonedasRequest
     * @param  requestHTTP Objeto tipo HttpServletRequest
     * @return ResponseEntity<Object>  
     * @version 1.0
     * @author Wilmer Vieira
	 * @since 16/03/21
     */
	
	@PostMapping(path=Servicios.MONEDASURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> crearMoneda(@Valid @RequestBody MonedasRequest monedasRequest, HttpServletRequest requestHTTP) {
		
		log.info(Servicios.MONEDASSERVICEI);
		log.info("monedasRequest: " + monedasRequest);
		MonedaDtoResponse monedaDtoResponse = new MonedaDtoResponse();
		log.info("monedaService.findById(monedaDtoRequest.getCodMoneda()) : "+monedaService.findById(monedasRequest.getMonedasDtoRequest().getCodMoneda()));
		
		if(monedaService.existsById(monedasRequest.getMonedasDtoRequest().getCodMoneda())) {
			log.info("existe");
			throw new FieldAlreadyExistException("codMoneda: "+monedasRequest.getMonedasDtoRequest().getCodMoneda());
		}else {
			monedaDtoResponse = monedaService.save(monedasRequest);
		}
			
		
		/*
		if(monedaService.findById(monedasRequest.getMonedasDtoRequest().getCodMoneda()) == null) {
			log.info("No existe ese codigo, se puede hacer el insert");
			monedaDtoResponse = monedaService.save(monedasRequest);
			
		}else {
			log.info("Existe en Base de datos ese Codigo");
		}*/
		
		return  ResponseEntity.status( HttpStatus.CREATED).body(monedaDtoResponse);
		
	}
	
	@PostMapping(path=Servicios.PRUEBAMONEDASURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> crearMoneda2(@Valid @RequestBody MonedasRequest monedasRequest, HttpServletRequest requestHTTP) {
		
		log.info(Servicios.MONEDASSERVICEI);
		log.info("monedasRequest: " + monedasRequest);
		MonedaDtoResponse monedaDtoResponse = new MonedaDtoResponse();
		log.info("monedaService.findById(monedaDtoRequest.getCodMoneda()) : "+monedaService.findById(monedasRequest.getMonedasDtoRequest().getCodMoneda()));
		
		
		log.debug("Creando usuario con data {}", monedaDtoResponse);
		
		//usuario = usuarioService.create(usuario);
		
		/*
		if(monedaService.findById(monedasRequest.getMonedasDtoRequest().getCodMoneda()) == null) {
			log.info("No existe ese codigo, se puede hacer el insert");
			monedaDtoResponse = monedaService.save(monedasRequest);
			
		}else {
			log.info("Existe en Base de datos ese Codigo");
		}*/
		
		
		
		
		
		return  ResponseEntity.status( HttpStatus.CREATED).body(monedaDtoResponse);
		
	}
	
	
	
	
	

	@GetMapping(path = Servicios.PRUEBAMONEDAIDURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getMoneda2(@Valid @RequestBody DatosRequestConsulta datosRequestConsulta,
			@PathVariable("codMoneda") String codMoneda, HttpServletRequest requestHTTP) {
		
		log.info(Servicios.MONEDASCONTROLLERI);
		log.info("datosRequestConsulta: " + datosRequestConsulta);
		log.info("codMoneda: " + codMoneda);

		MonedaDtoResponse monedaDtoResponse = new MonedaDtoResponse();
		
		if(monedaService.existsById(codMoneda)) {
			log.info("Que es lo que es");
			//throw new  NoHandlerFoundException()
		}
		
		log.info(Servicios.MONEDASCONTROLLERF);		return ResponseEntity.ok(monedaDtoResponse);
	}

	

	
	
	
	
	
	
	
	@GetMapping()
	public List<Moneda> findAll() {
		return monedaService.findAll();
	}

	@GetMapping("/all")
	public MonedaDtoResponse getAll() {
		return monedaService.getAll();
	}
	
	@GetMapping("/all/mapper")
	public MonedaDtoResponse findAllDto() {

		List<Moneda> listMonedas = monedaService.findAll();

		List<MonedaDto> listMonedasDto = mapper.mapAll(listMonedas, MonedaDto.class);

		for (Moneda moneda : listMonedas) {
			listMonedasDto.add(mapper.map(moneda, MonedaDto.class));
		}
		MonedaDtoResponse monedaDtoResponse = new MonedaDtoResponse();
		monedaDtoResponse.setListMonedasDto(listMonedasDto);

		return monedaDtoResponse;
	}

	@GetMapping("/all/mapperGenerico")
	public MonedaDtoResponse findAllDtoGenerico() {

		List<MonedaDto> listMonedasDto = monedaService.findAllGlobalMapper();
		MonedaDtoResponse monedaDtoResponse = new MonedaDtoResponse();
		monedaDtoResponse.setListMonedasDto(listMonedasDto);

		return monedaDtoResponse;
	}
	
	
}
