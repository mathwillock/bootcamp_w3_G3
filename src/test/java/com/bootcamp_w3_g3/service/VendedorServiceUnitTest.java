package com.bootcamp_w3_g3.service;


import com.bootcamp_w3_g3.model.entity.Vendedor;
import com.bootcamp_w3_g3.repository.VendedorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


public class VendedorServiceUnitTest {

    @Autowired
    private VendedorService vendedorService;

    private final VendedorRepository vendedorRepository = Mockito.mock(VendedorRepository.class);

    Vendedor vendedor1 = Vendedor.builder()
            .nome("Marcelo")
            .sobrenome("de Oliveira Santos")
            .codigo("MLVEN-342")
            .cpf("12312344567")
            .endereco("Rua Estraburgo, 45, São Paulo - BR")
            .build()
    ;

    Vendedor vendedor2 = Vendedor.builder()
            .nome("Manuella")
            .sobrenome("Marques da Silva")
            .codigo("MLVEN-377")
            .cpf("12312344569")
            .endereco("Avendia Alsácia, 222, Cotia, BR")
            .build()
    ;

    Vendedor vendedor3 = Vendedor.builder()
            .nome("Manuella")
            .sobrenome("Marques da Silva")
            .codigo("MLVEN-377")
            .cpf("12312344560")
            .endereco("Avendia Alsácia, 222, Cotia, BR")
            .build()
    ;

    List<Vendedor> vendedorList = new ArrayList<>();

    @Test
    public void salvarVendedorTest() {

        Mockito.when(vendedorRepository.save(Mockito.any(Vendedor.class))).thenReturn(vendedor3);

        VendedorService vendedorService = new VendedorService(vendedorRepository);
        Vendedor salvo = vendedorService.salvar(vendedor3);

        Mockito.verify(vendedorRepository, Mockito.times(1)).save(vendedor3);

        assertNotNull(salvo);
    }


    @Test
    public void atualizaVendedorTest()
    {
        vendedor3.setId(222L);
        vendedor3.setCodigo("25");
        vendedor3.setTelefone("777777777");
        vendedor3.setEndereco("Rua Amarildo");
        Mockito.when(vendedorRepository.getByCodigo(Mockito.any(String.class))).thenReturn(vendedor3);
        Mockito.when(vendedorRepository.save(Mockito.any(Vendedor.class))).thenReturn(vendedor3);
        VendedorService vendedorService = new VendedorService(vendedorRepository);
        Vendedor atualizado = vendedorService.atualizar(vendedor3);
        assertNotNull(atualizado);
        assertEquals(atualizado.getTelefone(), vendedor3.getTelefone());

    }

    @Test void listarVendedorTest()
    {
        vendedorList.add(vendedor1);
        vendedorList.add(vendedor2);
        Mockito.when(vendedorRepository.findAll()).thenReturn(vendedorList);

        vendedorService = new VendedorService(vendedorRepository);
        List<Vendedor> lista = vendedorService.listar();

        Mockito.verify(vendedorRepository, Mockito.times(1)).findAll();

        assertEquals(lista.size(),vendedorList.size());

    }

    @Test
    public void obterVendedorTest()
    {
        Vendedor obtido = null;
        vendedor3.setCodigo("25");
        Mockito.when(vendedorRepository.getByCodigo(Mockito.any(String.class))).thenReturn(vendedor3);

        vendedorService = new VendedorService(vendedorRepository);
        obtido = vendedorService.obter(vendedor3.getCodigo());
        assertNotNull(obtido);
       Mockito.verify(vendedorRepository,
               Mockito.times(1))
                .getByCodigo(vendedor3.getCodigo());


        assertEquals(obtido.getCpf(), vendedor3.getCpf());
    }





}
