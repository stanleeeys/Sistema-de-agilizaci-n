package DAO;


import Models.Proveedor;
import Provider.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProveedorDAO {

    // Variables Globales
    Conexion conexionDb = new Conexion();
    Connection conexion = conexionDb.getConnection();
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA VER LOS PROVEEDORES
    public ArrayList<Proveedor> listarProveedores() {

        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        String consulta = "SELECT * FROM proveedor";

        try {

            pStatement = conexion.prepareStatement(consulta);
            rSet = pStatement.executeQuery();

            while (rSet.next()) {
                
                Proveedor oProveedor = new Proveedor();
                oProveedor.setIdProveedor(rSet.getInt("id_proveedor"));
                oProveedor.setNombreProveedor(rSet.getString("nombre_proveedor"));
                proveedores.add(oProveedor);
            }
            
            conexion.close();
            return proveedores;

        } catch (Exception e) {

            System.err.println("error" + e);
            return null;
        }
    }
}
