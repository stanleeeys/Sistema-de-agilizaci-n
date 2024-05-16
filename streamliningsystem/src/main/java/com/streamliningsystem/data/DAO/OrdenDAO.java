package com.streamliningsystem.data.DAO;

import com.streamliningsystem.data.Models.Orden;
import com.streamliningsystem.data.Provider.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdenDAO {

    // Variables Globales
    Conexion conexionDb = new Conexion();
    Connection conexion = conexionDb.getConnection();
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA INGRESAR UNA ORDEN
    public void ingresarOrden(Orden orden) throws SQLException {

        String consulta = "INSERT INTO tb_cotizacion (cantidad, area_de_inversion_y_rubros_especificos, unidad_de_medida, descripcion, precio_unitario, precio_total) VALUES (?, ?, ?, ?, ?, ?)";
        pStatement = conexion.prepareStatement(consulta);

        pStatement.setInt(1, orden.getIdOrden());
        pStatement.setString(2, orden.getCodOrden());
        pStatement.setString(3, orden.getEncargadoOrden());
        pStatement.setDouble(4, orden.getTotales());
        pStatement.setInt(5, orden.getClienteId());
        pStatement.setInt(6, orden.getProveedorId());
        pStatement.setDouble(7, orden.getFechasErId());
        pStatement.execute();

        conexion.close();
    }
}
