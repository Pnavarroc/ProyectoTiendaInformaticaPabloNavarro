package Modelo;

import java.sql.*;

public class EmpleadoDAO {

    public static Empleado iniciarSesion(int id, String contraseña) {
        Connection conn = ConexionBD.conectar();
        try {
            String sql = "SELECT p.nombre, p.email, p.telefono, p.direccion, e.contraseña " +
                    "FROM Persona p JOIN Empleado e ON p.id_persona = e.id_persona " +
                    "WHERE p.id_persona = ? AND e.contraseña = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, contraseña);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                 Empleado empleado = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("contraseña")
                );
                 empleado.setId(id);
                 return empleado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static Empleado obtenerEmpleadoAleatorio() {
        Connection conn = ConexionBD.conectar();
        try {
            String sql = "SELECT p.id_persona, p.nombre, p.email, p.telefono, p.direccion, e.contraseña " +
                    "FROM Empleado e JOIN Persona p ON e.id_persona = p.id_persona ORDER BY RAND() LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("contraseña")
                );
                empleado.setId(rs.getInt("id_persona"));
                return empleado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Empleado obtenerPorId(int id) {
        Empleado empleado = null;

        String sql = """
        SELECT p.nombre, p.email, p.telefono, p.direccion, e.contraseña
        FROM empleado e
        JOIN persona p ON e.id_persona = p.id_persona
        WHERE e.id_persona = ?
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                empleado = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("contraseña")
                );
                empleado.setId(id); // Muy importante: asignar el ID al objeto
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return empleado;
    }

}
