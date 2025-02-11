package com.desarrolloservidor.practicaTiendaBBDD.model;

public class FabricanteDTO{
	
	private Long idFabricante;
    private String nombre;

    // Getters y Setters
    public Long getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Long idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
}
