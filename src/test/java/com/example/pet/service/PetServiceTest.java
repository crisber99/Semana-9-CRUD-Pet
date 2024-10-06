package com.example.pet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.pet.model.OrdenCompra;
import com.example.pet.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @InjectMocks
    private PetServiveImpl petServ;

    @Mock
    private PetRepository pRepository;

    @Test
    public void guardarOCTest(){
        OrdenCompra oc = new OrdenCompra();
        oc.setProveedor("proveedor 6");
        oc.setDireccion("direccion 6");
        oc.setEmpresa("empresa 6");

        when(pRepository.save(any())).thenReturn(oc);

        OrdenCompra resultado = petServ.createOrdenCompra(oc);
        assertEquals("proveedor 6", resultado.getProveedor());
        assertEquals("direccion 6", resultado.getDireccion());
        assertEquals("empresa 6", resultado.getEmpresa());
        
    }
    
}
