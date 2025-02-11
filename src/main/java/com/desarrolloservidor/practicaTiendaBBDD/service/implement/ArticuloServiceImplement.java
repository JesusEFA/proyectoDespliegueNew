package com.desarrolloservidor.practicaTiendaBBDD.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.desarrolloservidor.practicaTiendaBBDD.repo.ArticuloRepository;
import com.desarrolloservidor.practicaTiendaBBDD.service.ArticuloService;
import com.desarrolloservidor.practicaTiendaBBDD.entity.ArticuloEntity;

@Service
public class ArticuloServiceImplement implements ArticuloService {
	
	@Autowired
	private ArticuloRepository articuloRepository;

    @Override
    public List<ArticuloEntity> obtenerTodosArticulos() {
        return articuloRepository.findAll();
    }
    
    @Override
    public ArticuloEntity obtenerArticuloPorId(Long id) {
        Optional<ArticuloEntity> articulo = articuloRepository.findById(id);
        return articulo.orElse(null);
    }
    
    @Override
    public List<ArticuloEntity> buscarArticulosPorNombre(String nombre) {
        return articuloRepository.buscarPorNombre(nombre);
    }
    
    @Override
    public ArticuloEntity guardarArticulo(ArticuloEntity articulo) {
        return articuloRepository.save(articulo);
    }
    
    @Override
    public void eliminarArticulo(Long id) {
        articuloRepository.deleteById(id);
    }
    
    @Override
    public List<ArticuloEntity> buscarArticulosPorPrecio(int precioMenor, int precioMayor){
    	return articuloRepository.buscarPorPrecio(precioMenor, precioMayor);
    }
    
}
