package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

    public static Cliente iniciarSesion(int id, String contraseña) {
        Cliente cliente = null;

        String sql = """
            SELECT p.nombre, p.email, p.telefono, p.direccion, c.contraseña
            FROM cliente c
            JOIN persona p ON c.id_persona = p.id_persona
            WHERE c.id_persona = ? AND c.contraseña = ?
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("contraseña")
                );
                cliente.setId(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public static boolean eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id_persona = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
