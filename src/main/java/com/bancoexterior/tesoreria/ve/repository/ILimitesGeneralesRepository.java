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
	
	//Todos
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.id.tipoTransaccion = ?2 and t.id.naturaleza = ?3 and t.flagActivo = ?4")
	public List<LimitesGeneralesDto> getByCodMonedaAndTipoTransaccionAndNaturalezaAndFlagActivo(String codMoneda, String tipoTransaccion, String naturaleza, boolean flagActivo);
	
	//codMoneda
	@Query(value = queryAll + " and t.id.codMoneda = ?1")
	public List<LimitesGeneralesDto> getByCodMoneda(String codMoneda);
	
	//codMonedaAndTipoTransaccion
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.id.tipoTransaccion = ?2")
	public List<LimitesGeneralesDto> getByCodMonedaAndTipoTransaccion(String codMoneda, String tipoTransaccion);
	
	//codMonedaAndNaturaleza
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.id.naturaleza = ?2")
	public List<LimitesGeneralesDto> getByCodMonedaAndNaturaleza(String codMoneda, String naturaleza);
	
	//codMonedaAndFlagActivo
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.flagActivo = ?2")
	public List<LimitesGeneralesDto> getByCodMonedaAndFlagActivo(String codMoneda, boolean flagActivo);
	
	//codMOnedaAndTipoTransaccionAndNaturaleza
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.id.tipoTransaccion = ?2 and t.id.naturaleza = ?3")
	public List<LimitesGeneralesDto> getById(String codMoneda, String tipoTransaccion, String naturaleza);
	
	//codMOnedaAndNaturalezaAndFlagActivo
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.id.naturaleza = ?2 and t.flagActivo = ?3")
	public List<LimitesGeneralesDto> getByCodMonedaAndNaturalezaAndFlagActivo(String codMoneda, String naturaleza, boolean flagActivo);
	
	//codMOnedaAndTipoTransaccionAndFlagActivo
	@Query(value = queryAll + " and t.id.codMoneda = ?1 and t.id.tipoTransaccion = ?2 and t.flagActivo = ?3")
	public List<LimitesGeneralesDto> getByCodMonedaAndTipoTransaccionAndFlagActivo(String codMoneda, String tipoTransaccion, boolean flagActivo);
	
	//tipoTransaccion
	@Query(value = queryAll + " and t.id.tipoTransaccion = ?1")
	public List<LimitesGeneralesDto> getByTipoTrasaccion(String tipoTransaccion);
	
	//tipoTransaccionAndFlagActivo
	@Query(value = queryAll + " and t.id.tipoTransaccion = ?1 and t.id.naturaleza = ?2")
	public List<LimitesGeneralesDto> getByTipoTrasaccionAndNaturaleza(String tipoTransaccion, String naturaleza);
	
	//tipoTransaccionAndFlagActivo
	@Query(value = queryAll + " and t.id.tipoTransaccion = ?1 and t.flagActivo = ?2")
	public List<LimitesGeneralesDto> getByTipoTransaccionAndFlagActivo(String tipoTransaccion, boolean flagActivo);
	
	//tipoTransaccionAndNaturalezAndFlagActivo
	@Query(value = queryAll + " and t.id.tipoTransaccion = ?1 and t.id.naturaleza = ?2 and t.flagActivo = ?3")
	public List<LimitesGeneralesDto> getByTipoTrasaccionAndNaturalezaAndFlagActivo(String tipoTransaccion, String naturaleza, boolean flagActivo);
	
	//naturaleza
	@Query(value = queryAll + " and t.id.naturaleza = ?1")
	public List<LimitesGeneralesDto> getByNaturaleza(String naturaleza);
	
	//naturalezaAndFlagActivo
	@Query(value = queryAll + " and t.id.naturaleza = ?1 and t.flagActivo = ?2")
	public List<LimitesGeneralesDto> getByNaturalezaAndFlagActivo(String naturaleza, boolean flagActivo);
		
	//flagActivo
	@Query(value = queryAll + " and t.flagActivo = ?1")
	public List<LimitesGeneralesDto> getByFlagActivo(boolean flagActivo);
	
	
}
