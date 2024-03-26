package Devops.aplicacaoClieneJava;

import java.util.ArrayList;

public class CadastroCliente {
    private ArrayList<Cliente>clientes;

    public CadastroCliente(){
        clientes = new ArrayList<>();
    }

    public void addCliente(Cliente cliente){
        clientes.add(cliente);
    }
    public void viewCliente(){
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void updateCliente(int indice, Cliente cliente){
        clientes.set(indice, cliente);
    }

    public void removeCliente(int indice){
        clientes.remove(indice);
    }

    public ArrayList<Cliente> getClientes(){
        return clientes;
    }
}
