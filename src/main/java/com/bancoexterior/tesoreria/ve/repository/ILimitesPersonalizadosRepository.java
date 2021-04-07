package com.bancoexterior.tesoreria.ve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDto;
import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizados;
import com.bancoexterior.tesoreria.ve.entities.LimitesPersonalizadosPk;



@Repository
public interface ILimitesPersonalizadosRepository extends JpaRepository<LimitesPersonalizados, LimitesPersonalizadosPk>{
	
	String queryAll = "select new com.bancoexterior.tesoreria.ve.dto.limitesPersonalizados.LimitesPersonalizadosDto "
			+ "(t.id.codMoneda, t.id.tipoTransaccion, t.id.codIbs, t.montoMin, t.montoMax, t.montoTope, "
			+ "t.montoMensual, t.montoDiario, t.flagActivo, t.codUsuario, t.fechaModificacion) "
			+ " from LimitesPersonalizados t"
			+ " where 1=1";
	
	@Query(value = queryAll)
	public List<LimitesPersonalizadosDto> getAll();
	
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.id.tipoTransaccion = ?2 and t.id.codIbs = ?3")
	public List<LimitesPersonalizadosDto> getById(String codMoneda, String tipoTransaccion, String codIbs);
}
