/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LAB10;
/**
 *
 * @author Aleyda
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/banco";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private static Connection conexion;

    public static Connection obtenerConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            }
        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }
}
