package com.desarrolloservidor.practicaTiendaBBDD.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desarrolloservidor.practicaTiendaBBDD.entity.ArticuloEntity;

@Repository
public interface ArticuloRepository extends JpaRepository<ArticuloEntity, Long> {
	
	// Consulta JPQL
    @Query("SELECT e FROM ArticuloEntity e WHERE e.nombre = :nombre")
    List<ArticuloEntity> buscarPorNombre(@Param("nombre") String nombre);
    
	// Consulta JPQL
    @Query("SELECT e FROM ArticuloEntity e WHERE e.precio between :precioMenor and :precioMayor")
    List<ArticuloEntity> buscarPorPrecio(@Param("precioMenor") int precioMenor, @Param("precioMayor") int precioMayor);

}
