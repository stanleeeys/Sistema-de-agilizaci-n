package com.streamliningsystem.data.DAO;

import com.streamliningsystem.data.Models.FechasER;
import com.streamliningsystem.data.Provider.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FechaErDAO {

    // Variables Globales
    Conexion conexionDb = new Conexion();
    Connection conexion;
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA INGRESAR UNA FECHA
    public boolean ingresarFechas(FechasER fechasER) {

        conexion = conexionDb.getConnection();

        String consulta = "INSERT INTO FechasER"
                + "(fecha_solicitud, fecha_cotizacion, fecha_orden, "
                + "fecha_recepcion, fecha_plan_compras) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setDate(1, new java.sql.Date(fechasER.getFechaSolicitud().getTime()));
            pStatement.setDate(2, new java.sql.Date(fechasER.getFechaCotizacion().getTime()));
            pStatement.setDate(3, new java.sql.Date(fechasER.getFechaOrden().getTime()));
            pStatement.setDate(4, new java.sql.Date(fechasER.getFechaRecepcion().getTime()));
            pStatement.setDate(5, new java.sql.Date(fechasER.getFechaPlanCompras().getTime()));

            pStatement.execute();
            conexion.close();
            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean actualizarFechas(FechasER fechasER) {

        conexion = conexionDb.getConnection();

        String consulta = "UPDATE FechasER SET "
                + "fecha_solicitud = ?, "
                + "fecha_cotizacion = ?, "
                + "fecha_orden = ?, "
                + "fecha_recepcion = ?, "
                + "fecha_plan_compras = ? "
                + "WHERE id_fechas = ?";

        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setDate(1, new java.sql.Date(fechasER.getFechaSolicitud().getTime()));
            pStatement.setDate(2, new java.sql.Date(fechasER.getFechaCotizacion().getTime()));
            pStatement.setDate(3, new java.sql.Date(fechasER.getFechaOrden().getTime()));
            pStatement.setDate(4, new java.sql.Date(fechasER.getFechaRecepcion().getTime()));
            pStatement.setDate(5, new java.sql.Date(fechasER.getFechaPlanCompras().getTime()));
            pStatement.setInt(6, fechasER.getIdFechas());

            int confirmacion = pStatement.executeUpdate();

            return confirmacion > 0;

        } catch (Exception e) {

            return false;
        }
    }

    //METODO PARA BUSCAR ULTIMO CLIENTE
    public int obtenerFecha() {

        conexion = conexionDb.getConnection();
        String consulta = "SELECT id_fechas FROM FechasER ORDER BY id_fechas DESC LIMIT 1";
        int idCliente = -1; // Valor por defecto si no se encuentra ningún fechas

        try {
            pStatement = conexion.prepareStatement(consulta);
            rSet = pStatement.executeQuery();

            if (rSet.next()) {
                idCliente = rSet.getInt("id_fechas");
            }

            conexion.close();
            return idCliente;

        } catch (Exception e) {
            System.err.println("Error: " + e);
            return -1; // También podría lanzar una excepción o manejar el error de otra forma
        }
    }
}
