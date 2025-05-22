package Modelo;

import java.sql.*;

public class PersonaDAO {

    public static int guardarPersona(Persona persona) {
        int idGenerado = -1;

        try (Connection conn = ConexionBD.conectar()) {
            String sql = "INSERT INTO persona (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getEmail());
            ps.setString(3, persona.getTelefono());
            ps.setString(4, persona.getDireccion());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGenerado;
    }
}
