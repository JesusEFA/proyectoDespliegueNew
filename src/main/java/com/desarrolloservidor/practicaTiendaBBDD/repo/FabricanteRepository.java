package com.desarrolloservidor.practicaTiendaBBDD.repo;

import com.desarrolloservidor.practicaTiendaBBDD.entity.Fabricante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
    // JpaRepository ya incluye métodos para CRUD:
    // save() - Crear/Actualizar
    // findById() - Leer por ID
    // findAll() - Leer todos
    // deleteById() - Eliminar por ID
	
	// Consulta JPQL
    @Query("SELECT f FROM Fabricante f WHERE f.nombre = :nombre")
    Fabricante buscarPorNombre(@Param("nombre") String nombre);

}
