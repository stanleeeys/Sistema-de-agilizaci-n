package com.streamliningsystem.data.DAO;

import com.streamliningsystem.data.Models.Cliente;
import com.streamliningsystem.data.Provider.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

    // Variables Globales
    Conexion conexionDb = new Conexion();
    Connection conexion = conexionDb.getConnection();
    PreparedStatement pStatement;
    ResultSet rSet;

    // METODO PARA INGRESAR UNA CLIENTE
    public void ingresarCliente(Cliente cliente) {

        String consulta = "INSERT INTO Cliente"
                + "(encargado_compra, nombre_institucion, municipio)"
                + "VALUES (?, ?, ?)";

        try {

            pStatement = conexion.prepareStatement(consulta);

            pStatement.setString(1, cliente.getEncargadoCompra());
            pStatement.setString(2, cliente.getNombreInstitucion());
            pStatement.setString(3, cliente.getMunicipio());

            pStatement.execute();
            conexion.close();

        } catch (Exception e) {
            
            System.err.println(e.toString());
        }

    }

}
