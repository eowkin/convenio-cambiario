package com.bancoexterior.tesoreria.ve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bancoexterior.tesoreria.ve.dto.tasa.TasaDto;
import com.bancoexterior.tesoreria.ve.entities.Tasa;
import com.bancoexterior.tesoreria.ve.entities.TasaPk;

@Repository
public interface ITasaRepository extends JpaRepository<Tasa, TasaPk>{

	String queryAll = "select new com.bancoexterior.tesoreria.ve.dto.tasa.TasaDto(t.id.codMonedaOrigen, t.id.codMonedaDestino, t.montoTasa, t.codUsuario, t.fechaModificacion) "
			+ " from Tasa t"
			+ " where 1=1";

		@Query(value = queryAll)
		public List<TasaDto> getAll();
		
		
		@Query(value = queryAll + " and t.id.codMonedaOrigen = ?1")
		public List<TasaDto> getTasaByCodMonedaOrigen(String codMonedaOrigen);
		
		@Query(value = queryAll + " and t.id.codMonedaDestino = ?1")
		public List<TasaDto> getTasaByCodMonedaDestino(String codMonedaDestino);
	
		@Query(value = queryAll + " and t.id.codMonedaOrigen = ?1 and t.id.codMonedaDestino = ?2")
		public List<TasaDto> getTasaByCodMonedaOrigenAndCodMonedaDestino(String codMonedaOrigen, String codMonedaDestino);
	
}
