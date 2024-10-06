package com.example.pet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pet.model.OrdenCompra;

public interface PetRepository extends JpaRepository<OrdenCompra, Long>{
    
}
