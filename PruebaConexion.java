
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/cityelectronic";
        String usuario = "root";
        String contrasena = "Bigben2024.";

        try {
            Connection conexion = DriverManager.getConnection(
                    url, usuario, contrasena);

            System.out.println("¡Conexion exitosa con MySQL!");
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL:");
            System.out.println(e.getMessage());
        }
    }
}
