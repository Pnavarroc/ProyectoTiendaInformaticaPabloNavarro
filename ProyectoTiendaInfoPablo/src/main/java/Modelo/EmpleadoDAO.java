package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoDAO {
    public static Empleado buscarEmpleadoPorId(int id) {
        Connection conn = ConexionBD.conectar();
        try {
            String sql = "SELECT p.nombre, p.email, p.telefono, p.direccion " +
                    "FROM Persona p JOIN Empleado e ON p.id_persona = e.id_persona " +
                    "WHERE p.id_persona = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Empleado(
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
