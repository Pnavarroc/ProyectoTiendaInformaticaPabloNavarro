package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Empleado extends Persona {
    private String contraseña;

    public Empleado(String nombre, String email, String telefono, String direccion, String contraseña) {
        super(nombre, email, telefono, direccion);
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public void guardarEnBD() {
        int nuevoId = PersonaDAO.guardarPersona(this);
        this.setId(nuevoId);

        if (this.getId() > 0) {
            try (Connection conn = ConexionBD.conectar()) {
                String sql = "INSERT INTO empleado (id_persona, contraseña) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, this.getId());
                ps.setString(2, this.contraseña);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
