package Controllers;

import DAO.ClienteDAO;
import Models.Cliente;
import ViewModel.ClienteVM;


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
            cliente.setCodigo(clienteVm.codigo);
            
            clienteDao.ingresarCliente(cliente);
            return true;
            
        } catch (Exception e) {
            
            return false;
        }
    }
    
    public boolean actualizarCliente(ClienteVM clienteVm){
        
        try {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(clienteVm.idCliente);
            cliente.setEncargadoCompra(clienteVm.encargadoCompra);
            cliente.setNombreInstitucion(clienteVm.nombreInstitucion);
            cliente.setMunicipio(clienteVm.municipio);
            cliente.setCodigo(clienteVm.codigo);
            
            clienteDao.actualizarCliente(cliente);
            return true;
            
        } catch (Exception e) {
            
            return false;
        }
    }
    
    
    
    public int obtenerCliente(){
    
        return clienteDao.obtenerCliente();
    }
    
    public boolean eliminarOrden(int id){
    
        return clienteDao.borrarCliente(id);
    }
}
