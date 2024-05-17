package com.streamliningsystem.data.DAO;

import com.streamliningsystem.data.Models.DetalleOrden;
import com.streamliningsystem.data.Provider.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrdeDetalleDAO {
    
    // Variables Globales
    Conexion conexionDb = new Conexion();
    Connection conexion = conexionDb.getConnection();
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA INGRESAR UNA DETALLE ORDEN
    public boolean ingresarDetalleOrden(DetalleOrden orden) {

        String consulta = "INSERT INTO DetallesOrden (num_articulo, cantidad, unidad_medida, descripcion_articulo, precio_unitario, fechas_er_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            /*pStatement = conexion.prepareStatement(consulta);

            pStatement.setInt(1, orden.getIdOrden());
            pStatement.setString(2, orden.getCodOrden());
            pStatement.setString(3, orden.getEncargadoOrden());
            pStatement.setDouble(4, orden.getTotales());
            pStatement.setInt(5, orden.getClienteId());
            pStatement.setInt(6, orden.getProveedorId());
            pStatement.setDouble(7, orden.getFechasErId());
            pStatement.execute();

            conexion.close();*/

            return true;

        } catch (Exception e) {

            return false;
        }

    }
    
}
