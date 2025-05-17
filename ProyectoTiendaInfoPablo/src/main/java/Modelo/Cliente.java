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

    public String getContraseña() {
        return contraseña;
    }

    @Override
    public void guardarEnBD() {
        try {
            // Insertar en Persona
            int idGenerado = PersonaDAO.guardarPersona(this);
            this.setId(idGenerado);

            // Insertar en Cliente con contraseña
            Connection conn = ConexionBD.conectar();
            String sqlCliente = "INSERT INTO Cliente (id_persona, contraseña) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlCliente);
            ps.setInt(1, this.getId());
            ps.setString(2, contraseña);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente en la base de datos.");
            e.printStackTrace();
        }
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Estado de cuenta: " + (activo ? "Activa" : "Inactiva"));
    }
}





