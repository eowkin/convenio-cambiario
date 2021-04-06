package com.bancoexterior.tesoreria.ve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizados;
import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizadosPk;



@Repository
public interface ILimitesPersonalizadosRepository extends JpaRepository<LimitesPersonalizados, LimitesPersonalizadosPk>{

}
