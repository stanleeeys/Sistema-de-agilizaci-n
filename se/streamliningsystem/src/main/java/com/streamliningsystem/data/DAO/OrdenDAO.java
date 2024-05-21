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

        String consulta = "INSERT INTO orden ("
                + "    cod_orden, "
                + "    encargado_orden, "
                + "    totales, "
                + "    limite_cotizacion, "
                + "    fecha_de_entrega, "
                + "    hora_entrega_desde, "
                + "    hora_entrega_hasta, "
                + "    tiempo_entrega, "
                + "    plazo_entrega, "
                + "    lugar_entrega, "
                + "    vigencia_de_la_cotizacion, "
                + "    tiempo_de_garantia, "
                + "    cliente_id, "
                + "    proveedor_id, "
                + "    fechas_er_id"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setString(1, orden.getCodOrden());
            pStatement.setString(2, orden.getEncargadoOrden());
            pStatement.setDouble(3, orden.getTotales());
            pStatement.setDate(4, new java.sql.Date(orden.getLimite_cotizacion().getTime()));
            pStatement.setDate(5, new java.sql.Date(orden.getFecha_de_entrega().getTime()));
            pStatement.setString(6, orden.getHora_entrega_desde());
            pStatement.setString(7, orden.getHora_entrega_hasta());
            pStatement.setString(8, orden.getTiempo_entrega());
            pStatement.setString(9, orden.getPlazo_entrega());
            pStatement.setString(10, orden.getLugar_entrega());
            pStatement.setString(11, orden.getVigencia_de_la_cotizacion());
            pStatement.setString(12, orden.getTiempo_de_garantia());
            pStatement.setInt(13, orden.getClienteId());
            pStatement.setInt(14, orden.getProveedorId());
            pStatement.setInt(15, orden.getFechasErId());
            pStatement.execute();

            conexion.close();

            return true;

        } catch (Exception e) {

            return false;
        }

    }

    public boolean actualizarOrden(Orden orden) {

        conexion = conexionDb.getConnection();

        String consulta = "UPDATE orden SET "
                + "cod_orden = ?, "
                + "encargado_orden = ?, "
                + "totales = ?, "
                + "limite_cotizacion = ?, "
                + "fecha_de_entrega = ?, "
                + "hora_entrega_desde = ?, "
                + "hora_entrega_hasta = ?, "
                + "tiempo_entrega = ?, "
                + "plazo_entrega = ?, "
                + "lugar_entrega = ?, "
                + "vigencia_de_la_cotizacion = ?, "
                + "tiempo_de_garantia = ?, "
                + "cliente_id = ?, "
                + "proveedor_id = ?, "
                + "fechas_er_id = ? "
                + "WHERE id_orden = ?";

        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setString(1, orden.getCodOrden());
            pStatement.setString(2, orden.getEncargadoOrden());
            pStatement.setDouble(3, orden.getTotales());
            pStatement.setDate(4, new java.sql.Date(orden.getLimite_cotizacion().getTime()));
            pStatement.setDate(5, new java.sql.Date(orden.getFecha_de_entrega().getTime()));
            pStatement.setString(6, orden.getHora_entrega_desde());
            pStatement.setString(7, orden.getHora_entrega_hasta());
            pStatement.setString(8, orden.getTiempo_entrega());
            pStatement.setString(9, orden.getPlazo_entrega());
            pStatement.setString(10, orden.getLugar_entrega());
            pStatement.setString(11, orden.getVigencia_de_la_cotizacion());
            pStatement.setString(12, orden.getTiempo_de_garantia());
            pStatement.setInt(13, orden.getClienteId());
            pStatement.setInt(14, orden.getProveedorId());
            pStatement.setInt(15, orden.getFechasErId());
            pStatement.setInt(16, orden.getIdOrden());

            int confirmacion = pStatement.executeUpdate();

            return confirmacion > 0;

        } catch (Exception e) {

            return false;
        }

    }

    public int obtenerOrden() {

        conexion = conexionDb.getConnection();
        String consulta = "SELECT id_orden FROM orden ORDER BY id_orden DESC LIMIT 1";
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
        String consulta = "select orden.id_orden, orden.cod_orden, orden.encargado_orden, orden.totales, orden.cliente_id, orden.proveedor_id, orden.fechas_er_id, cliente.encargado_compra, cliente.nombre_institucion, fechaser.fecha_orden, fechaser.fecha_recepcion from orden inner join cliente on orden.cliente_id = cliente.id_cliente inner join fechaser on orden.fechas_er_id = fechaser.id_fechas order by orden.id_orden desc";

        try {

            pStatement = conexion.prepareStatement(consulta);
            rSet = pStatement.executeQuery();

            while (rSet.next()) {

                TablaOrdenVM oOrden = new TablaOrdenVM();
                oOrden.setIdOrden(rSet.getInt("id_orden"));
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

    // METODO PARA VER LOS PROVEEDORES
    public TablaOrdenVM obtenerOrdenId(int idOrden) {

        conexion = conexionDb.getConnection();

        TablaOrdenVM ordenes = new TablaOrdenVM();

        String consulta = "SELECT "
                + "orden.id_orden, "
                + "orden.cod_orden, "
                + "orden.encargado_orden, "
                + "orden.totales, "
                + "orden.limite_cotizacion, "
                + "orden.fecha_de_entrega, "
                + "orden.hora_entrega_desde, "
                + "orden.hora_entrega_hasta, "
                + "orden.tiempo_entrega, "
                + "orden.plazo_entrega, "
                + "orden.lugar_entrega, "
                + "orden.vigencia_de_la_cotizacion, "
                + "orden.tiempo_de_garantia, "
                + "orden.cliente_id, "
                + "orden.proveedor_id, "
                + "orden.fechas_er_id, "
                + "cliente.encargado_compra, "
                + "cliente.nombre_institucion, "
                + "cliente.municipio, "
                + "cliente.codigo_escuela, "
                + "proveedor.nombre_proveedor, "
                + "fechaser.fecha_solicitud, "
                + "fechaser.fecha_cotizacion, "
                + "fechaser.fecha_orden, "
                + "fechaser.fecha_recepcion, "
                + "fechaser.fecha_plan_compras "
                + "FROM orden "
                + "INNER JOIN cliente ON orden.cliente_id = cliente.id_cliente "
                + "INNER JOIN proveedor ON orden.proveedor_id = proveedor.id_proveedor "
                + "INNER JOIN fechaser ON orden.fechas_er_id = fechaser.id_fechas "
                + "WHERE orden.id_orden = ?";

        try {

            pStatement = conexion.prepareStatement(consulta);
            pStatement.setInt(1, idOrden);
            rSet = pStatement.executeQuery();

            if (rSet.next()) {

                ordenes.setIdOrden(rSet.getInt("id_orden"));
                ordenes.setCodOrden(rSet.getString("cod_orden"));
                ordenes.setEncargadoOrden(rSet.getString("encargado_orden"));
                ordenes.setTotales(rSet.getDouble("totales"));
                ordenes.setLimite_cotizacion(rSet.getDate("limite_cotizacion"));
                ordenes.setFecha_de_entrega(rSet.getDate("fecha_de_entrega"));
                ordenes.setHora_entrega_desde(rSet.getString("hora_entrega_desde"));
                ordenes.setHora_entrega_hasta(rSet.getString("hora_entrega_hasta"));
                ordenes.setTiempo_entrega(rSet.getString("tiempo_entrega"));
                ordenes.setPlazo_entrega(rSet.getString("plazo_entrega"));
                ordenes.setLugar_entrega(rSet.getString("lugar_entrega"));
                ordenes.setVigencia_de_la_cotizacion(rSet.getString("vigencia_de_la_cotizacion"));
                ordenes.setTiempo_de_garantia(rSet.getString("tiempo_de_garantia"));
                ordenes.setClienteId(rSet.getInt("cliente_id"));
                ordenes.setProveedorId(rSet.getInt("proveedor_id"));
                ordenes.setFechasErId(rSet.getInt("fechas_er_id"));
                ordenes.setEncargadoCompra(rSet.getString("encargado_compra"));
                ordenes.setNombreInstitucion(rSet.getString("nombre_institucion"));
                ordenes.setMunicipio(rSet.getString("municipio"));
                ordenes.setCodigo_escuela(rSet.getString("codigo_escuela"));
                ordenes.setNombreProveedor(rSet.getString("nombre_proveedor"));
                ordenes.setFechaSolicitud(rSet.getDate("fecha_solicitud"));
                ordenes.setFechaCotizacion(rSet.getDate("fecha_cotizacion"));
                ordenes.setFechaOrden(rSet.getDate("fecha_orden"));
                ordenes.setFechaRecepcion(rSet.getDate("fecha_recepcion"));
                ordenes.setFechaPlanCompras(rSet.getDate("fecha_plan_compras"));
            }

            conexion.close();
            return ordenes;

        } catch (Exception e) {

            System.err.println("error" + e);
            return null;
        }
    }

    public boolean eliminarOrden(int orden) {

        conexion = conexionDb.getConnection();

        String consulta = "DELETE FROM orden WHERE id_orden = ?";

        try {

            pStatement = conexion.prepareStatement(consulta);
            pStatement.setInt(1, orden);

            int eliminadoExitoso = pStatement.executeUpdate();
            return eliminadoExitoso > 0;

        } catch (Exception e) {

            return false;
        }

    }
}
