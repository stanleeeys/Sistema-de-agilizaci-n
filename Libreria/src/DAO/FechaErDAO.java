package DAO;


import Models.FechasER;
import Provider.Conexion;
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

        String consulta = "INSERT INTO fechaser"
                + "(fecha_solicitud, fecha_cotizacion, fecha_orden, "
                + "fecha_recepcion, fecha_plan_compras) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setDate(1, new java.sql.Date(fechasER.getFechaSolicitud().getTime()));
            pStatement.setDate(2, new java.sql.Date(fechasER.getFechaCotizacion().getTime()));
            pStatement.setDate(3, new java.sql.Date(fechasER.getFechaOrden().getTime()));
            pStatement.setDate(4, new java.sql.Date(fechasER.getFechaRecepcion().getTime()));
            pStatement.setDate(5, new java.sql.Date(fechasER.getFechaRecepcion().getTime()));

            pStatement.execute();
            conexion.close();
            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean actualizarFechas(FechasER fechasER) {

        conexion = conexionDb.getConnection();

        String consulta = "UPDATE fechaser SET "
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
            pStatement.setDate(5, new java.sql.Date(fechasER.getFechaRecepcion().getTime()));
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
        String consulta = "SELECT id_fechas FROM fechaser ORDER BY id_fechas DESC LIMIT 1";
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

    public boolean eliminarFechas(int id) {

        conexion = conexionDb.getConnection();

        String consulta = "DELETE FROM fechaser WHERE id_fechas_er = ?";

        try {

            pStatement = conexion.prepareStatement(consulta);
            pStatement.setInt(1, id);

            // Ejecutar la consulta de eliminación
            int eliminadoExitoso = pStatement.executeUpdate();
            conexion.close();
            return eliminadoExitoso > 0;

        } catch (Exception e) {

            return false;
        }
    }
}
