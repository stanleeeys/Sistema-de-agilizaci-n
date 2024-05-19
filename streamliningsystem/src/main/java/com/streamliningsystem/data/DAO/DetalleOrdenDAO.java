package com.streamliningsystem.data.DAO;

import com.streamliningsystem.data.Models.DetalleOrden;
import com.streamliningsystem.data.Provider.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DetalleOrdenDAO {

    // Variables Globales
    Conexion conexionDb = new Conexion();
    Connection conexion;
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA INGRESAR UNA DETALLE ORDEN
    public boolean ingresarDetalleOrden(DetalleOrden detalleOrden) {

        conexion = conexionDb.getConnection();
        String consulta = "INSERT INTO DetallesOrden "
                + "(num_articulo, "
                + "cantidad, "
                + "unidad_medida, "
                + "descripcion_articulo, "
                + "precio_unitario, "
                + "precio_total, "
                + "orden_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

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

    public boolean actualizarDetalleOrden(DetalleOrden detalleOrden) {

        conexion = conexionDb.getConnection();
        String consulta = "UPDATE DetallesOrden SET "
                + "num_articulo = ?, "
                + "cantidad = ?, "
                + "unidad_medida = ?, "
                + "descripcion_articulo = ?, "
                + "precio_unitario = ?, "
                + "precio_total = ?, "
                + "orden_id = ? "
                + "WHERE id_detalle_orden = ?";

        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setInt(1, detalleOrden.getNumArticulo());
            pStatement.setInt(2, detalleOrden.getCantidad());
            pStatement.setString(3, detalleOrden.getUnidadMedida());
            pStatement.setString(4, detalleOrden.getDescripcionArticulo());
            pStatement.setDouble(5, detalleOrden.getPrecioUnitario());
            pStatement.setDouble(6, detalleOrden.getPrecioTotal());
            pStatement.setInt(7, detalleOrden.getOrdenId());
            pStatement.setInt(8, detalleOrden.getIdDetalleOrden());

            int confirmacion = pStatement.executeUpdate();

            return confirmacion > 0;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean eliminarDetalleOrden(int idDetalle) {

        conexion = conexionDb.getConnection();
        String consulta = "DELETE FROM DetallesOrden WHERE "
                + "id_detalle_orden = ?";

        try {

            pStatement = conexion.prepareStatement(consulta);
            pStatement.setInt(1, idDetalle);

            int confirmacion = pStatement.executeUpdate();

            return confirmacion > 0;

        } catch (Exception e) {

            return false;
        }
    }

    // METODO PARA VER LOS LISTAR DETALLES
    public ArrayList<DetalleOrden> listarDetalles(int ordenId) {
        conexion = conexionDb.getConnection();

        ArrayList<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
        String consulta = "SELECT * FROM DetallesOrden WHERE orden_id = ?";

        try {
            pStatement = conexion.prepareStatement(consulta);
            pStatement.setInt(1, ordenId);
            rSet = pStatement.executeQuery();

            while (rSet.next()) {
                DetalleOrden oDetalle = new DetalleOrden();
                oDetalle.setIdDetalleOrden(rSet.getInt("id_detalle_orden"));
                oDetalle.setNumArticulo(rSet.getInt("num_articulo"));
                oDetalle.setCantidad(rSet.getInt("cantidad"));
                oDetalle.setUnidadMedida(rSet.getString("unidad_medida"));
                oDetalle.setDescripcionArticulo(rSet.getString("descripcion_articulo"));
                oDetalle.setPrecioUnitario(rSet.getDouble("precio_unitario"));
                oDetalle.setPrecioTotal(rSet.getDouble("precio_total"));
                oDetalle.setOrdenId(rSet.getInt("orden_id"));
                detalles.add(oDetalle);
            }

            conexion.close();
            return detalles;
        } catch (Exception e) {
            System.err.println("error" + e);
            return null;
        }
    }

}
