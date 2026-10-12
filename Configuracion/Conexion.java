
package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private static final String URL =
            "jdbc:sqlite:cityelectronic.db";

    public static Connection conectar() throws SQLException {

        Connection conexion =
                DriverManager.getConnection(URL);

        try (Statement consulta = conexion.createStatement()) {
            consulta.execute("PRAGMA foreign_keys = ON");
        }

        return conexion;
    }
}
