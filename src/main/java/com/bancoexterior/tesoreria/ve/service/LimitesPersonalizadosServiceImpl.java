package com.bancoexterior.tesoreria.ve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizados;
import com.bancoexterior.tesoreria.ve.repository.ILimitesPersonalizadosRepository;



@Service
public class LimitesPersonalizadosServiceImpl implements ILimitesPersonalizadosService{

	@Autowired
	private ILimitesPersonalizadosRepository repo;
	
	@Override
	public List<LimitesPersonalizados> findAll() {
		return repo.findAll();
	}

}
