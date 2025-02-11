package com.desarrolloservidor.practicaTiendaBBDD.controller;

import com.desarrolloservidor.practicaTiendaBBDD.entity.ArticuloEntity;
import com.desarrolloservidor.practicaTiendaBBDD.entity.Fabricante;
import com.desarrolloservidor.practicaTiendaBBDD.service.ArticuloService;
import com.desarrolloservidor.practicaTiendaBBDD.service.FabricanteService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fabricante")
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;
    
    @Autowired
    private ArticuloService articuloService;

    // Mostrar todos los fabricantes
    @GetMapping("/mostrarTodos")
    public String obtenerFabricantes(Model model) {
    	model.addAttribute("fabricantes", fabricanteService.obtenerTodosFabricantes());
        return "fabricante/mostrarFabricantes";
    }
    
    // Formulario para crear un nuevo fabricante
    @GetMapping("/nuevoFabricante")
    public String formularioNuevoFabricante(Model model) {
        model.addAttribute("fabricante", new Fabricante());
        return "fabricante/crearFabricante";
    }

    // Guardar un nuevo fabricante
    @PostMapping("/guardarFabricante")
    public String guardarFabricante(@ModelAttribute Fabricante fabricante) {
    	Fabricante fabricanteExistente = null;
    	fabricanteExistente = fabricanteService.buscarPorNombre(fabricante.getNombre());
        
    	if(fabricanteExistente == null) {
    		fabricanteService.guardarFabricante(fabricante);
    	}
    	
        return "redirect:/fabricante/mostrarTodos";
    }
    
    // Mostrar formulario para editar fabricante
    @GetMapping("/editarFabricante/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {         	
    	model.addAttribute("fabricante", fabricanteService.obtenerFabricanteId(id));
    	return "fabricante/editarFabricante";

    }
    
    // Actualizar y guardar el fabricante
    @PostMapping("/guardarEdicion")
    public String actualizar(@ModelAttribute Fabricante fabricante, @RequestParam("idFabricante") Long idFabricante, @RequestParam("nuevoNombre") String nuevoNombre) {
        fabricante.setNombre(nuevoNombre);
    	fabricanteService.actualizar(fabricante);
    	return "redirect:/fabricante/mostrarTodos";
    }
    
    // Eliminar fabricante
    @GetMapping("/eliminarFabricante/{id}")
    public String eliminarFabricante(@PathVariable("id") Long id, Model model) {         	
    	Fabricante fabricante = fabricanteService.obtenerFabricanteId(id);
    	Set<ArticuloEntity> articulos = fabricante.getArticulos();
    	
    	model.addAttribute("fabricante", fabricante);
        model.addAttribute("articulos", articulos);
        model.addAttribute("fabricantes", fabricanteService.obtenerTodosFabricantes());
        return "fabricante/eliminarFabricante";

    }
    
    @PostMapping("/eliminarFabricante")
    public String eliminarFabricante(@RequestParam("fabricanteId") Long fabricanteId, @RequestParam(value = "opcion", required = false) String opcion, @RequestParam(value = "nuevoFabricanteId", required = false) Long nuevoFabricanteId) {
        Fabricante fabricante = fabricanteService.obtenerFabricanteId(fabricanteId);

        if (opcion.equals("cambiar") && nuevoFabricanteId != null) {
            Fabricante nuevoFabricante = fabricanteService.obtenerFabricanteId(nuevoFabricanteId);
            for (ArticuloEntity articulo : fabricante.getArticulos()) {
                articulo.setFabricante(nuevoFabricante);
                articuloService.guardarArticulo(articulo);
            }
        } else if (opcion.equals("borrar")) {
            for (ArticuloEntity articulo : fabricante.getArticulos()) {
                articuloService.eliminarArticulo(articulo.getIdArticulo());
            }
        }
        fabricanteService.eliminar(fabricanteId);

        return "redirect:/fabricante/mostrarTodos";
    }
    
    // Introducir nombre
    @GetMapping("/buscarFabricantePorNombre")
    public String introducirNombre() {
        return "fabricante/introducirNombre";
    }
    
    // Buscar fabricante por nombre
    @GetMapping("/buscarPorNombre")
    public String buscarPorNombre(@RequestParam("nombre") String nombre, Model model) {         	
    	model.addAttribute("fabricantes", fabricanteService.buscarPorNombre(nombre));
        return "fabricante/mostrarFabricantes";

    }
    
    @GetMapping("articulos/{id}")
    public String verArticulosPorFabricante(@PathVariable Long id, Model model) {
        Fabricante fabricante = fabricanteService.obtenerFabricanteId(id);
        model.addAttribute("fabricante", fabricante);
        model.addAttribute("articulos", fabricante.getArticulos());
        return "fabricante/articulosFabricante";
    }
    
}
