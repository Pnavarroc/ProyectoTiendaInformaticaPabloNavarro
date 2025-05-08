package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cliente extends Persona {
    private String contraseña;
    private boolean activo;

    public Cliente(int id, String nombre, String email, String telefono, String direccion, String contraseña) {
        super(id, nombre, email, telefono, direccion);
        this.contraseña = contraseña;
        this.activo = true;
    }



        public void guardarEnBD() {
            Connection conn = ConexionBD.conectar();

            try {
                // Insertar en Persona
                String sqlPersona = "INSERT INTO Persona (id_persona, nombre, email, telefono, direccion) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement psPersona = conn.prepareStatement(sqlPersona);
                psPersona.setInt(1, getId());
                psPersona.setString(2, getNombre());
                psPersona.setString(3, getEmail());
                psPersona.setString(4, getTelefono());
                psPersona.setString(5, getDireccion());
                psPersona.executeUpdate();

                // Insertar en Cliente
                String sqlCliente = "INSERT INTO Cliente (id_persona) VALUES (?)";
                PreparedStatement psCliente = conn.prepareStatement(sqlCliente);
                psCliente.setInt(1, getId());
                psCliente.executeUpdate();

                System.out.println("Cliente guardado en la base de datos.");
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





