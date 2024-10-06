package com.example.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pet.model.OrdenCompra;
import com.example.pet.service.PetService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/pet")
public class OrdenCompraController {

    private static final Logger log = LoggerFactory.getLogger(OrdenCompraController.class);

    @Autowired
    private PetService petService;

    @GetMapping
    public CollectionModel<EntityModel<OrdenCompra>> getAllOrdenCompra() {
        log.info("getAllOrdenCompra");
        List<OrdenCompra> OCs = petService.getAllOrdenCompra();
        List<EntityModel<OrdenCompra>> OcResources = OCs.stream()
            .map(oc -> EntityModel.of(oc, 
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getOrdenCompraById(oc.getIdOC())).withSelfRel()
                ))
            .collect(Collectors.toList());
            
            WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllOrdenCompra());
            CollectionModel<EntityModel<OrdenCompra>> resources = CollectionModel.of(OcResources, linkTo.withRel("pet"));

        return resources;
    }
        
    @GetMapping("/{id}")
    public EntityModel<OrdenCompra> getOrdenCompraById(@PathVariable Long id) {
        log.info("getOrdenCompraById");
        Optional<OrdenCompra> OC = petService.getOrdenCompraById(id);

        if(OC.isPresent()){
            return EntityModel.of(OC.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getOrdenCompraById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllOrdenCompra()).withRel("all-OC"));
        }
        else{
            throw new OrdenCompraNotFoundException("Orden de Compra no funciona con el id: " + id); 
        }
    }

    @PostMapping
    public EntityModel<OrdenCompra> createOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        log.info("createOrdenCompra");
        OrdenCompra OC = petService.createOrdenCompra(ordenCompra);
        return EntityModel.of(OC,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getOrdenCompraById(OC.getIdOC())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllOrdenCompra()).withRel("all-OC"));
    }

    @PutMapping("/{id}")
    public EntityModel<OrdenCompra> updateOrdenCompra(@PathVariable Long id, @RequestBody OrdenCompra ordenCompra) {
        log.info("updateOrdenCompra");
        OrdenCompra OC = petService.updateOrdenCompra(id, ordenCompra);
        return EntityModel.of(OC,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getOrdenCompraById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllOrdenCompra()).withRel("all-OC"));
    }

    @DeleteMapping("/{id}")
    public void deleteOrdenCompra(@PathVariable Long id)
    {
        log.info("deleteOrdenCompra");
        petService.deleteOrdenCompra(id);
    }

    static class ErrorResponse{
        private final String msg;

        public ErrorResponse(String msg){
            this.msg = msg;
        }

        public String getMsg(){
            return msg;
        }
    }
}