package com.bancoexterior.tesoreria.ve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancoexterior.tesoreria.ve.entities.LimitesGenerales;
import com.bancoexterior.tesoreria.ve.entities.LimitesGeneralesPk;

@Repository
public interface ILimitesGeneralesRepository extends JpaRepository<LimitesGenerales, LimitesGeneralesPk>{

}
