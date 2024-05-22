/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author veget
 */
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
