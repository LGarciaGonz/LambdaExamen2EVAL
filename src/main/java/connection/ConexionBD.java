package connection;

import java.lang.module.InvalidModuleDescriptorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    /**
     * Completar los datos con la base de datos en cada caso, puerto y jdbc.
     */

    //definimos como constantes los datos de la conexión a la base de datos
    private static final String URL = "jdbc:sqlite:resources/mydb.sqlite"; //conector y localización
    private static final String USUARIO = "";
    private static final String CLAVE = "";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL);
            // System.out.println("Conexión OK BD.");
        } catch (SQLException e) {
            System.out.println("Error en la conexión BD");
            e.getMessage();
        } catch (InvalidModuleDescriptorException e) {
            System.out.println("Error PAM BD");
        }
        return conexion;
    }

}
