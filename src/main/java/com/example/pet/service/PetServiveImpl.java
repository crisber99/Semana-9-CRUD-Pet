package com.example.pet.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet.model.OrdenCompra;
import com.example.pet.repository.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiveImpl implements PetService{
    @Autowired
    private PetRepository petRepository;

    @Override
    public List<OrdenCompra> getAllOrdenCompra() {
        return petRepository.findAll();
    }

    @Override
    public Optional<OrdenCompra> getOrdenCompraById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public OrdenCompra createOrdenCompra(OrdenCompra ordenCompra) {
        return petRepository.save(ordenCompra);
    }

    @Override
    public OrdenCompra updateOrdenCompra(Long id, OrdenCompra ordenCompra) {
        if(petRepository.existsById(id))
        {
            ordenCompra.setIdOc(id);
            return petRepository.save(ordenCompra);
        }
        else
        return null;
    }

    @Override
    public void deleteOrdenCompra(Long id) {
        petRepository.deleteById(id);
    }
}