package Modelo;

import java.sql.*;

public class PersonaDAO {
    public static int guardarPersona(Persona persona) throws SQLException {
        Connection conn = ConexionBD.conectar();

        String sql = "INSERT INTO Persona (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, persona.getNombre());
        ps.setString(2, persona.getEmail());
        ps.setString(3, persona.getTelefono());
        ps.setString(4, persona.getDireccion());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int idGenerado = rs.getInt(1);
            persona.setId(idGenerado);
            return idGenerado;
        } else {
            throw new SQLException("No se pudo obtener el ID generado.");
        }
    }
}
