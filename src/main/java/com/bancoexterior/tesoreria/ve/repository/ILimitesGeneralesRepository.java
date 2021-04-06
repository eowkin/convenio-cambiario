package com.bancoexterior.tesoreria.ve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDto;
import com.bancoexterior.tesoreria.ve.entities.LimitesGenerales;
import com.bancoexterior.tesoreria.ve.entities.LimitesGeneralesPk;

@Repository
public interface ILimitesGeneralesRepository extends JpaRepository<LimitesGenerales, LimitesGeneralesPk>{
	String queryAll = "select new com.bancoexterior.tesoreria.ve.dto.limitesGenerales.LimitesGeneralesDto"
			+ "(t.id.codMoneda, t.id.tipoTransaccion, t.id.naturaleza, t.montoMin, t.montoMax, t.montoTope, "
			+ "t.montoMensual, t.montoDiario, t.montoBanco, t.codUsuario, t.flagActivo, t.fechaModificacion) "
			+ " from LimitesGenerales t"
			+ " where 1=1";
	
	@Query(value = queryAll)
	public List<LimitesGeneralesDto> getAll();
}
