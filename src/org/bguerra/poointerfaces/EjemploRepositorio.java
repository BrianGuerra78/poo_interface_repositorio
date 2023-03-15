package org.bguerra.poointerfaces;

import org.bguerra.poointerfaces.modelo.Cliente;
import org.bguerra.poointerfaces.repositorio.*;
import org.bguerra.poointerfaces.repositorio.lista.ClienteListRepositorio;

import java.util.List;

public class EjemploRepositorio {
    public static void main(String[] args) {

        OrdenablePaginableCrudRepositorio<Cliente> repo = new ClienteListRepositorio();
        repo.crear(new Cliente("Jano", "Perez"));
        repo.crear(new Cliente("Bea", "Gonzales"));
        repo.crear(new Cliente("Luci", "Martinez"));
        repo.crear(new Cliente("Andres", "Guzman"));

        List<Cliente> clientes = repo.listar();
        clientes.forEach(System.out::println);
        System.out.println("=======Paginable=======");
        //List<Cliente> paginable = ((PaginableRepositorio) repo).listar(1, 3);
        List<Cliente> paginable = repo.listar(1, 3);
        paginable.forEach(System.out::println);

        System.out.println("=======Ordenar=====");
        //List<Cliente> clientesOrdenAsc = ((OrdenableRepositorio) repo).listar("nombre", Direccion.ASC);
        List<Cliente> clientesOrdenAsc = repo.listar("nombre", Direccion.ASC);
        for (Cliente c : clientesOrdenAsc) {
            System.out.println(c);
        }

        System.out.println("=======Editar=======");
        Cliente beaActualizar = new Cliente("Bea", "Mena");
        beaActualizar.setId(2);
        repo.editar(beaActualizar);
        Cliente bea = repo.porId(2);
        System.out.println(bea);
        System.out.println("=======Ordenar=====");
        //((OrdenableRepositorio) repo).listar("nombre", Direccion.ASC).forEach(System.out::println);
        repo.listar("nombre", Direccion.ASC).forEach(System.out::println);

        System.out.println("=======Eliminar=====");
        repo.eliminar(2);
        repo.listar().forEach(System.out::println);
        System.out.println("=======Total======");
        System.out.println("Total registros: " + repo.total());
    }
}
