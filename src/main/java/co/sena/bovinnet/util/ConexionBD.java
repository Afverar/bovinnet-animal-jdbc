package co.sena.bovinnet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria encargada de abrir la conexion JDBC hacia la base
 * de datos "bovinnet" (MySQL), definida en las evidencias del
 * componente de bases de datos del proyecto (GA6-220501096).
 *
 * Los parametros de conexion se centralizan aqui para que ningun
 * otro punto del proyecto tenga que repetir la URL, el usuario o
 * la contrasena de la base de datos.
 */
public final class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/bovinnet?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    private ConexionBD() {
        // Clase de utilidades: no debe instanciarse.
    }

    /**
     * Abre y retorna una nueva conexion JDBC hacia la base de datos.
     *
     * @return una conexion abierta, lista para ejecutar sentencias.
     * @throws SQLException si el controlador no puede conectarse.
     */
    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException error) {
            throw new SQLException("No se encontro el controlador JDBC de MySQL.", error);
        }
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
