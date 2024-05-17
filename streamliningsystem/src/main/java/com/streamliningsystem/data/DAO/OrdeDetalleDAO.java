package com.streamliningsystem.data.DAO;

import com.streamliningsystem.data.Models.DetalleOrden;
import com.streamliningsystem.data.Provider.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrdeDetalleDAO {
    
    // Variables Globales
    Conexion conexionDb = new Conexion();
    Connection conexion;
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA INGRESAR UNA DETALLE ORDEN
    public boolean ingresarDetalleOrden(DetalleOrden detalleOrden) {

        
        conexion = conexionDb.getConnection();
        String consulta = "INSERT INTO detallesorden (num_articulo, cantidad, unidad_medida, descripcion_articulo, precio_unitario, precio_total, orden_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setInt(1, detalleOrden.getNumArticulo());
            pStatement.setInt(2, detalleOrden.getCantidad());
            pStatement.setString(3, detalleOrden.getUnidadMedida());
            pStatement.setString(4, detalleOrden.getDescripcionArticulo());
            pStatement.setDouble(5, detalleOrden.getPrecioUnitario());
            pStatement.setDouble(6, detalleOrden.getPrecioTotal());
            pStatement.setInt(7, detalleOrden.getOrdenId());
            pStatement.execute();

            conexion.close();

            return true;

        } catch (Exception e) {

            return false;
        }
    }    
}
