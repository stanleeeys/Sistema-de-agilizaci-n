package com.streamliningsystem.controllers;

import com.streamliningsystem.data.DAO.ProveedorDAO;
import com.streamliningsystem.data.Models.Proveedor;
import com.streamliningsystem.models.ViewModels.ProveedorVM;
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
