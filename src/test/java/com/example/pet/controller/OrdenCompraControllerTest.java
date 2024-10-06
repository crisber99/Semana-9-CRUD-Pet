package com.example.pet.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.pet.model.OrdenCompra;
import com.example.pet.service.PetServiveImpl;

@WebMvcTest(OrdenCompraController.class)
public class OrdenCompraControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetServiveImpl OcServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception{
        OrdenCompra Oc1 = new OrdenCompra();
        Oc1.setDireccion("Direccion 1");
        Oc1.setEmpresa("Empresa 1");
        Oc1.setProveedor("Proveedor 1");
        Oc1.setIdOc(1);

        OrdenCompra Oc2 = new OrdenCompra();
        Oc2.setDireccion("Direccion 2");
        Oc2.setEmpresa("Empresa 2");
        Oc2.setProveedor("Proveedor 2");
        Oc2.setIdOc(2);

        OrdenCompra Oc3 = new OrdenCompra();
        Oc3.setDireccion("Direccion 1");
        Oc3.setEmpresa("Empresa 1");
        Oc3.setProveedor("Proveedor 1");
        Oc3.setIdOc(3);

        OrdenCompra Oc4 = new OrdenCompra();
        Oc4.setDireccion("Direccion 2");
        Oc4.setEmpresa("Empresa 2");
        Oc4.setProveedor("Proveedor 2");
        Oc4.setIdOc(4);

        List<OrdenCompra> lOc = Arrays.asList(Oc1, Oc2, Oc3, Oc4);
        when(OcServiceMock.getAllOrdenCompra()).thenReturn(lOc);

        mockMvc.perform(MockMvcRequestBuilders.get("/pet"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(4)))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.OcList[0].name", Matchers.is("Direccion 1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.OcList[1].name", Matchers.is("Direccion 2")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.OcList[2].name", Matchers.is("Direccion 3")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.OcList[3].name", Matchers.is("Direccion 4")));
    }

}
