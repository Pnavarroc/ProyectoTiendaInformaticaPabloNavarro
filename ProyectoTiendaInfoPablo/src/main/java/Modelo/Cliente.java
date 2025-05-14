package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cliente extends Persona {
    private String contraseña;
    private boolean activo;

    public Cliente(String nombre, String email, String telefono, String direccion, String contraseña) {
        super(nombre, email, telefono, direccion);
        this.contraseña = contraseña;
        this.activo = true;
    }

    public Cliente() {
    }

    @Override
    public void guardarEnBD() {
        try {
            PersonaDAO.guardarPersona(this); // PersonaDAO le asigna el ID con setId()

            Connection conn = ConexionBD.conectar();
            String sql = "INSERT INTO Cliente (id_persona) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Estado de cuenta: " + (activo ? "Activa" : "Inactiva"));
    }
}





