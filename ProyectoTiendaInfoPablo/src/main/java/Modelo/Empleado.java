package Modelo;

import java.sql.*;

public class Empleado extends Persona {

    public Empleado(String nombre, String email, String telefono, String direccion) {
        super(nombre, email, telefono, direccion);
    }

    @Override
    public void guardarEnBD() {
        try {
            PersonaDAO.guardarPersona(this); // El ID se asigna vía setId()

            Connection conn = ConexionBD.conectar();
            String sqlEmpleado = "INSERT INTO Empleado (id_persona) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sqlEmpleado);
            ps.setInt(1, getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
