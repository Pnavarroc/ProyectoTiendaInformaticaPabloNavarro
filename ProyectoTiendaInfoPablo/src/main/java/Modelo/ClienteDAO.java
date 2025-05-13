package Modelo;

import java.sql.*;

public class ClienteDAO {

    public static Cliente iniciarSesion(int id, String contraseña) {
        Connection conn = ConexionBD.conectar();
        try {
            String sql = "SELECT p.nombre, p.email, p.telefono, p.direccion, c.contraseña " +
                    "FROM Persona p JOIN Cliente c ON p.id_persona = c.id_persona " +
                    "WHERE p.id_persona = ? AND c.contraseña = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, contraseña);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("contraseña")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean eliminarCliente(int id) {
        Connection conn = ConexionBD.conectar();
        try {
            String deleteCliente = "DELETE FROM Cliente WHERE id_persona = ?";
            String deletePersona = "DELETE FROM Persona WHERE id_persona = ?";

            PreparedStatement ps1 = conn.prepareStatement(deleteCliente);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(deletePersona);
            ps2.setInt(1, id);
            ps2.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
