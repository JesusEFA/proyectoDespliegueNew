package com.desarrolloservidor.practicaTiendaBBDD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="FABRICANTE")
public class Fabricante implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "ID_FABRICANTE", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFabricante;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @OneToMany(mappedBy = "fabricante")
    Set<ArticuloEntity> articulos = new HashSet<ArticuloEntity>();
    

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

	public Set<ArticuloEntity> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<ArticuloEntity> articulos) {
		this.articulos = articulos;
	}
    
}

