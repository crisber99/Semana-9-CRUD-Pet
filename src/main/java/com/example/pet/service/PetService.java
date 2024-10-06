package com.example.pet.service;
import com.example.pet.model.OrdenCompra;
import java.util.List;
import java.util.Optional;

public interface PetService {
    List<OrdenCompra> getAllOrdenCompra();
    Optional<OrdenCompra> getOrdenCompraById(Long id);
    OrdenCompra createOrdenCompra(OrdenCompra ordenCompra);
    OrdenCompra updateOrdenCompra(Long id, OrdenCompra ordenCompra);
    void deleteOrdenCompra(Long id);
}
