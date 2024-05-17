package com.streamliningsystem.data.DAO;

import com.streamliningsystem.data.Models.Orden;
import com.streamliningsystem.data.Provider.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrdenDAO {

    // Variables Globales
    Conexion conexionDb = new Conexion();
    Connection conexion;
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA INGRESAR UNA ORDEN
    public boolean ingresarOrden(Orden orden) {
        
        conexion = conexionDb.getConnection();

        String consulta = "INSERT INTO Orden (cod_orden, encargado_orden, totales, cliente_id, proveedor_id, fechas_er_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setString(1, orden.getCodOrden());
            pStatement.setString(2, orden.getEncargadoOrden());
            pStatement.setDouble(3, orden.getTotales());
            pStatement.setInt(4, orden.getClienteId());
            pStatement.setInt(5, orden.getProveedorId());
            pStatement.setInt(6, orden.getFechasErId());
            pStatement.execute();

            conexion.close();

            return true;

        } catch (Exception e) {

            return false;
        }

    }
    
    public int obtenerOrden() {
        
        conexion = conexionDb.getConnection();
        String consulta = "SELECT id_orden FROM Orden ORDER BY id_orden DESC LIMIT 1";
        int idCliente = -1; // Valor por defecto si no se encuentra ningún cliente

        try {
            pStatement = conexion.prepareStatement(consulta);
            rSet = pStatement.executeQuery();

            if (rSet.next()) {
                idCliente = rSet.getInt("id_orden");
            }

            conexion.close();
            return idCliente;

        } catch (Exception e) {
            System.err.println("Error: " + e);
            return -1;
        }
    }
    
    // METODO PARA VER LOS PROVEEDORES
    public ArrayList<Orden> listarOrdenes() {
        
        conexion = conexionDb.getConnection();

        ArrayList<Orden> ordenes = new ArrayList<Orden>();
        String consulta = "SELECT * FROM Orden";

        try {

            pStatement = conexion.prepareStatement(consulta);
            rSet = pStatement.executeQuery();

            while (rSet.next()) {
                
                Orden oOrden = new Orden();
                oOrden.setClienteId(rSet.getInt("id_orden"));
                oOrden.setCodOrden(rSet.getString("cod_orden"));
                oOrden.setEncargadoOrden(rSet.getString("encargado_orden"));
                oOrden.setTotales(rSet.getDouble("totales"));
                oOrden.setClienteId(rSet.getInt("cliente_id"));
                oOrden.setProveedorId(rSet.getInt("proveedor_id"));
                oOrden.setFechasErId(rSet.getInt("fechas_er_id"));
                ordenes.add(oOrden);
            }
            
            conexion.close();
            return ordenes;

        } catch (Exception e) {

            System.err.println("error" + e);
            return null;
        }
    }
}
