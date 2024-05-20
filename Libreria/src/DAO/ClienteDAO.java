package DAO;


import Models.Cliente;
import Provider.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

    // Variables Globales
    Conexion conexionDb = new Conexion();
    Connection conexion;
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA INGRESAR UNA CLIENTE
    public boolean ingresarCliente(Cliente cliente) {

        conexion = conexionDb.getConnection();

        String consulta = "INSERT INTO cliente"
                + "(encargado_compra, nombre_institucion, municipio)"
                + "VALUES (?, ?, ?)";
        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setString(1, cliente.getEncargadoCompra());
            pStatement.setString(2, cliente.getNombreInstitucion());
            pStatement.setString(3, cliente.getMunicipio());

            pStatement.execute();
            conexion.close();

            return true;

        } catch (Exception e) {

            System.err.println(e.toString());
            return false;
        }
    }

    public boolean actualizarCliente(Cliente cliente) {

        conexion = conexionDb.getConnection();

        String consulta = "UPDATE Cliente SET "
                + "encargado_compra = ?, "
                + "nombre_institucion = ?, "
                + "municipio = ? "
                + "WHERE id_cliente = ?";
        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setString(1, cliente.getEncargadoCompra());
            pStatement.setString(2, cliente.getNombreInstitucion());
            pStatement.setString(3, cliente.getMunicipio());
            pStatement.setInt(4, cliente.getIdCliente());

            int actualizadoExitoso = pStatement.executeUpdate();

            return actualizadoExitoso > 0;

        } catch (Exception e) {

            System.err.println(e.toString());
            return false;
        }
    }

    //METODO PARA BUSCAR ULTIMO CLIENTE
    public int obtenerCliente() {

        conexion = conexionDb.getConnection();

        String consulta = "SELECT id_cliente FROM Cliente ORDER BY id_cliente DESC LIMIT 1";
        int idCliente = -1; // Valor por defecto si no se encuentra ningún cliente

        try {

            pStatement = conexion.prepareStatement(consulta);

            rSet = pStatement.executeQuery();

            if (rSet.next()) {
                idCliente = rSet.getInt("id_cliente");
            }

            conexion.close();
            return idCliente;

        } catch (Exception e) {
            System.err.println("Error: " + e);
            return -1; // También podría lanzar una excepción o manejar el error de otra forma
        }
    }

}
