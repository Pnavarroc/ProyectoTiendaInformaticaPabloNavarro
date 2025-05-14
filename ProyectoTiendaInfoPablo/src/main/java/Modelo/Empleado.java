package Modelo;

import java.sql.*;

public class Empleado extends Persona {
    private String contraseña;

    public Empleado(String nombre, String email, String telefono, String direccion, String contraseña) {
        super(nombre, email, telefono, direccion);
        this.contraseña=contraseña;
    }

    public Empleado() {
    }

    public String getContraseña(){
        return contraseña;
    }

    @Override
    public void guardarEnBD() {
        try {
            PersonaDAO.guardarPersona(this); // El ID se asigna vía setId()

            Connection conn = ConexionBD.conectar();
            String sqlEmpleado = "INSERT INTO Empleado (id_persona, contraseña) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sqlEmpleado);
            ps.setInt(1, getId());
            ps.setString(2,getContraseña());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
