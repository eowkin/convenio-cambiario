package com.bancoexterior.tesoreria.ve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDto;
import com.bancoexterior.tesoreria.ve.entities.LimitesGenerales;
import com.bancoexterior.tesoreria.ve.repository.ILimitesGeneralesRepository;

@Service
public class LimitesGeneralesServiceImpl implements ILimitesGeneralesService{

	@Autowired
	private ILimitesGeneralesRepository repo;
	
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

}
