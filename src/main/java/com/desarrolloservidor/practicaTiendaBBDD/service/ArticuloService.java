package com.desarrolloservidor.practicaTiendaBBDD.service;

import java.util.List;

import com.desarrolloservidor.practicaTiendaBBDD.entity.ArticuloEntity;

public interface ArticuloService {

	List<ArticuloEntity> obtenerTodosArticulos();

	ArticuloEntity obtenerArticuloPorId(Long id);

    List<ArticuloEntity> buscarArticulosPorPrecio(int precioMenor, int precioMayor);

    List<ArticuloEntity> buscarArticulosPorNombre(String nombre);

    ArticuloEntity guardarArticulo(ArticuloEntity articulo);

    void eliminarArticulo(Long id);
    
}
