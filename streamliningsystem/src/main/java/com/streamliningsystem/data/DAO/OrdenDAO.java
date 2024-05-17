package com.streamliningsystem.data.DAO;

import com.streamliningsystem.data.Models.Orden;
import com.streamliningsystem.data.Provider.Conexion;
import com.streamliningsystem.models.ViewModels.TablaOrdenVM;
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
    public ArrayList<TablaOrdenVM> listarOrdenes() {

        conexion = conexionDb.getConnection();

        ArrayList<TablaOrdenVM> ordenes = new ArrayList<TablaOrdenVM>();
        String consulta = "SELECT Orden.cod_orden, Orden.encargado_orden, Orden.totales, Orden.cliente_id, Orden.proveedor_id, Orden.fechas_er_id, \n"
                + "Cliente.encargado_compra, Cliente.nombre_institucion,\n"
                + "FechasER.fecha_orden, FechasER.fecha_recepcion\n"
                + "from Orden\n"
                + "INNER JOIN Cliente on Orden.cliente_id  = Cliente.id_cliente\n"
                + "INNER JOIN FechasER on Orden.fechas_er_id = FechasER.id_fechas\n"
                + "ORDER BY Orden.id_orden DESC";

        try {

            pStatement = conexion.prepareStatement(consulta);
            rSet = pStatement.executeQuery();

            while (rSet.next()) {

                TablaOrdenVM oOrden = new TablaOrdenVM();
                oOrden.setCodOrden(rSet.getString("cod_orden"));
                oOrden.setEncargadoOrden(rSet.getString("encargado_orden"));
                oOrden.setTotales(rSet.getDouble("totales"));
                oOrden.setClienteId(rSet.getInt("cliente_id"));
                oOrden.setProveedorId(rSet.getInt("proveedor_id"));
                oOrden.setFechasErId(rSet.getInt("fechas_er_id"));
                oOrden.setEncargadoCompra(rSet.getString("encargado_compra"));
                oOrden.setNombreInstitucion(rSet.getString("nombre_institucion"));
                oOrden.setFechaOrden(rSet.getDate("fecha_orden"));
                oOrden.setFechaRecepcion(rSet.getDate("fecha_recepcion"));
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
