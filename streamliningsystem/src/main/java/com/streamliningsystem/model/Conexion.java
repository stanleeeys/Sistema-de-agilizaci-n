
package com.streamliningsystem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
        Connection con;
    
    public Connection getConnection(){
        try {
            String myBD= "jdbc:mysql://localhost:3306/sistemaagilizacion_bd?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD, "nico", ")P4ssword");
            return con;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.toString());
            return null;
        }
    }
}
