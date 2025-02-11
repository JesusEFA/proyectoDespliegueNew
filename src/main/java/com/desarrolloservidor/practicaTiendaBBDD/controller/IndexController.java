package com.desarrolloservidor.practicaTiendaBBDD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping(value = "/")
	public String paginaPrincipal() {
        return "index";
    }
	
	@GetMapping(value = "/fabricante")
	public String paginaPrincipalFabricante() {
        return "fabricante/indexFabricante";
    }
	
	@GetMapping(value = "/articulo")
	public String paginaPrincipalArticulo() {
        return "articulo/indexArticulo";
    }

}
