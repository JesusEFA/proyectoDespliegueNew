package com.desarrolloservidor.practicaTiendaBBDD.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desarrolloservidor.practicaTiendaBBDD.entity.Fabricante;
import com.desarrolloservidor.practicaTiendaBBDD.model.FabricanteDTO;
import com.desarrolloservidor.practicaTiendaBBDD.repo.FabricanteRepository;
import com.desarrolloservidor.practicaTiendaBBDD.service.FabricanteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FabricanteServiceImplement implements FabricanteService {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @Override
    public List<FabricanteDTO> obtenerTodosFabricantes() {
    	List<FabricanteDTO> fabricantesDTO = new ArrayList<FabricanteDTO>();
    	List<Fabricante> fabricantes = fabricanteRepository.findAll();
    	
    	FabricanteDTO fabricanteDTO;    	
    	for(Fabricante fabEntity : fabricantes) {
    		fabricanteDTO = new FabricanteDTO();
    		fabricanteDTO.setIdFabricante(fabEntity.getIdFabricante());
    		fabricanteDTO.setNombre(fabEntity.getNombre());
    		fabricantesDTO.add(fabricanteDTO);
    	}
        return fabricantesDTO;
    }

    @Override
    public Fabricante obtenerFabricanteId(Long id) {
        Optional<Fabricante> fabricante = fabricanteRepository.findById(id);
        return fabricante.orElse(null);
    }

    @Override
    public Fabricante buscarPorNombre(String nombre) {
        return fabricanteRepository.buscarPorNombre(nombre);
    }

    @Override
    public Fabricante guardarFabricante(Fabricante fabricante) {
        return fabricanteRepository.save(fabricante);
    }

    @Override
    public void eliminar(Long id) {
        fabricanteRepository.deleteById(id);
    }

    @Override
    public Fabricante actualizar(Fabricante fabricante) {
        // Comprueba que el fabricante existe antes de actualizar
        if (fabricanteRepository.existsById(fabricante.getIdFabricante())) {
            return fabricanteRepository.save(fabricante);
        }
        return null;
    }
}
