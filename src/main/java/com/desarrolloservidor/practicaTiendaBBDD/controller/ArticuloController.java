package com.desarrolloservidor.practicaTiendaBBDD.controller;

import com.desarrolloservidor.practicaTiendaBBDD.entity.ArticuloEntity;
import com.desarrolloservidor.practicaTiendaBBDD.service.FabricanteService;
import com.desarrolloservidor.practicaTiendaBBDD.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {

	@Autowired
    private ArticuloService articuloService;
	
	@Autowired
    private FabricanteService fabricanteService;

    @GetMapping("/mostrarArticulos")
    public String mostrarTodosArticulos(Model model) {
        List<ArticuloEntity> articulos = articuloService.obtenerTodosArticulos();
        model.addAttribute("articulos", articulos);
        return "articulo/mostrarArticulos";
    }
    
    // Introducir nombre
    @GetMapping("/buscarPorNombre")
    public String introducirNombre() {
        return "articulo/introducirNombre";
    }
    
    @GetMapping("/mostrarPorNombre")
    public String mostrarPorNombre(@RequestParam("nombre") String nombre, Model model) {
        List<ArticuloEntity> articulos = articuloService.buscarArticulosPorNombre(nombre);
        model.addAttribute("articulos", articulos);
        return "articulo/mostrarArticulos";
    }

    @GetMapping("/nuevoArticulo")
    public String formularioNuevoArticulo(Model model) {
        model.addAttribute("articulo", new ArticuloEntity());
        model.addAttribute("fabricantes", fabricanteService.obtenerTodosFabricantes());
        return "articulo/crearArticulo";
    }

    @PostMapping("/guardarArticulo")
    public String guardarArticulo(@ModelAttribute("articulo") ArticuloEntity articulo) {
    	System.out.println("id viejo " + articulo.getIdArticulo());
    	System.out.println("id nombre " + articulo.getNombre());
    	articuloService.guardarArticulo(articulo);
        System.out.println("noombre " + articulo.getIdArticulo());
        return "redirect:/articulos/mostrarArticulos";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarArticulo(@PathVariable Long id, Model model) {
        ArticuloEntity articulo = articuloService.obtenerArticuloPorId(id);
        if (articulo == null) {
            return "redirect:/articulos/mostrarArticulos";
        }
        model.addAttribute("articulo", articulo);
        System.out.println("id: " + articulo.getIdArticulo());
        model.addAttribute("fabricantes", fabricanteService.obtenerTodosFabricantes());
        return "articulo/crearArticulo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarArticulo(@PathVariable Long id) {
        articuloService.eliminarArticulo(id);
        return "redirect:/articulos/mostrarArticulos";
    }
    
    // Introducir precio menor y mayor
    @GetMapping("/buscarPorPrecio")
    public String introducirPrecios() {
        return "articulo/introducirPrecios";
    }
    
    @GetMapping("/mostrarPorPrecio")
    public String mostrarPorNombre(@RequestParam("precioMenor") int precioMenor, @RequestParam("precioMayor") int precioMayor, Model model) {
    	List<ArticuloEntity> articulos = articuloService.buscarArticulosPorPrecio(precioMenor, precioMayor);
        model.addAttribute("articulos", articulos);
        return "articulo/mostrarArticulos";
    }
}

