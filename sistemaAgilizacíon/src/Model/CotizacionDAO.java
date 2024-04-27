
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            ps.setString(2, cl.getArea());
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
}
