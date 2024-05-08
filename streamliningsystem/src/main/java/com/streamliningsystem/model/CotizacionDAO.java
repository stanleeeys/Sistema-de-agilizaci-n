
package com.streamliningsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CotizacionDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
     public boolean RegistrarCotizacion(Cotizacion cl) {
        String sql = "INSERT INTO tb_cotizacion (cantidad, area_de_inversion_y_rubros_especificos, unidad_de_medida, descripcion, precio_unitario, precio_total) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getCantidad());
            ps.setString(2, cl.getAreaInversion());
            ps.setString(3, cl.getUnidadMedida());
            ps.setString(4, cl.getDescripcion());
            ps.setDouble(5, cl.getPrecioUnitario());
            ps.setDouble(6, cl.getPrecioTotal());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
     public boolean EliminarCotizacion(int id_cotizacion) throws SQLException {
        if (id_cotizacion <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser un número positivo.");
        }

        String sql = "DELETE FROM tb_cotizacion WHERE id_cotizacion = ?";

        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_cotizacion);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el producto: " + e.getMessage(), e);
        }

    }
      public List ListarCotizacion() {
        List<Cotizacion> procl = new ArrayList<>();
        String sql = "SELECT * FROM tb_cotizacion";
        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cotizacion pc = new Cotizacion();
                pc.setId_cotizacion(rs.getInt("id_cotizacion"));
                pc.setCantidad(rs.getInt("cantidad"));
                pc.setUnidadMedida(rs.getString("unidad_de_medida"));
                pc.setDescripcion(rs.getString("descripcion"));
                pc.setPrecioUnitario(rs.getDouble("precio_unitario"));
                pc.setPrecioTotal(rs.getDouble("precio_total"));
                procl.add(pc);
            }
        } catch (SQLException e) {
            // Manejar la excepción de manera adecuada
            System.out.println("Error al listar: " + e.getMessage());
        }
        return procl;
    }
      public boolean modificarCotizacion(Cotizacion cl) throws SQLException {
       

        String sql = "UPDATE tb_cotizacion SET cantidad=?, unidad_de_medida=?, descripcion=?, precio_unitario=?, precio_total=? WHERE id_cotizacion=?";
        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cl.getCantidad());
            ps.setString(2, cl.getUnidadMedida());
            ps.setString(3, cl.getDescripcion());
            ps.setDouble(4, cl.getPrecioUnitario());
            ps.setDouble(5, cl.getPrecioTotal());
            ps.setInt(6, cl.getId_cotizacion());

            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            throw new SQLException("Error al modificar: " + e.getMessage(), e);
        }
    }

}