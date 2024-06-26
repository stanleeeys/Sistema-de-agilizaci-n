package Controllers;

import DAO.ProveedorDAO;
import Models.Proveedor;
import ViewModel.ProveedorVM;


import java.util.ArrayList;

public class ProveedorController {

    //variables globales
    ProveedorDAO proveedorDAO = new ProveedorDAO();

    //metodo para solicitar lostado de proveedores
    public ArrayList<ProveedorVM> mostrarProveedores() {

        try {

            ArrayList<Proveedor> lProveedor = new ArrayList<Proveedor>();
            ArrayList<ProveedorVM> lProveedorVM = new ArrayList<ProveedorVM>();

            lProveedor = proveedorDAO.listarProveedores();

            for (Proveedor it : lProveedor) {

                ProveedorVM proveedorVM = new ProveedorVM();
                proveedorVM.setIdProveedor(it.getIdProveedor());
                proveedorVM.setNombreProveedor(it.getNombreProveedor());

                lProveedorVM.add(proveedorVM);
            }

            return lProveedorVM;
        } catch (Exception e) {
            
            System.err.println(e.toString());

            return null;
        }
    }
}
