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
    Connection conexion = conexionDb.getConnection();
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA INGRESAR UNA FECHA
    public void ingresarFechas(FechasER fechasER) {

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

        } catch (Exception e) {

            System.err.println(e.toString());
        }
    }
}
