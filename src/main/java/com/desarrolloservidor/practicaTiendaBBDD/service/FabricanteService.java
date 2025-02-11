package com.desarrolloservidor.practicaTiendaBBDD.service;

import java.util.List;

import com.desarrolloservidor.practicaTiendaBBDD.entity.Fabricante;
import com.desarrolloservidor.practicaTiendaBBDD.model.FabricanteDTO;

public interface FabricanteService {

    List<FabricanteDTO> obtenerTodosFabricantes();

    Fabricante obtenerFabricanteId(Long id);

    Fabricante buscarPorNombre(String nombre);

    Fabricante guardarFabricante(Fabricante fabricante);

    void eliminar(Long id);

    Fabricante actualizar(Fabricante fabricante);
}

