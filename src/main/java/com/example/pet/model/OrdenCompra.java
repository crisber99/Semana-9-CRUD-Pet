package com.example.pet.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "pet")
public class OrdenCompra extends RepresentationModel<OrdenCompra> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOC")
    private long idOC;
    @NotBlank(message = "No se puede ingresar una empresa vacía")
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "direccion")
    private String direccion;

    public long getIdOC(){
        return idOC;
    }

    public String getEmpresa(){
        return empresa;
    }

    public String getProveedor(){
        return proveedor;
    }

    public String getDireccion(){
        return direccion;
    }
    //SET

    public void setIdOc(long idOC){
        this.idOC = idOC;
    }

    public void setEmpresa(String empresa){
        this.empresa = empresa;
    }

    public void setProveedor(String proveedor){
        this.proveedor = proveedor;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
}
