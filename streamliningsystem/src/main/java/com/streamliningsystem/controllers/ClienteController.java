package com.streamliningsystem.controllers;

import com.streamliningsystem.data.DAO.ClienteDAO;
import com.streamliningsystem.data.Models.Cliente;
import com.streamliningsystem.models.ViewModels.ClienteVM;

public class ClienteController {
    
    // Objetos globales
    ClienteDAO clienteDao = new ClienteDAO();    
    
    
    // Metodo de Guardado de cliente
    public boolean guardarCliente(ClienteVM clienteVm){
        
        try {
            Cliente cliente = new Cliente();
            cliente.setEncargadoCompra(clienteVm.encargadoCompra);
            cliente.setNombreInstitucion(clienteVm.nombreInstitucion);
            cliente.setMunicipio(clienteVm.municipio);
            
            clienteDao.ingresarCliente(cliente);
            return true;
            
        } catch (Exception e) {
            
            return false;
        }
    }
}
