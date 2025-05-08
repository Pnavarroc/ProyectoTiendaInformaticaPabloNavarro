package Modelo;

import Modelo.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Empleado extends Persona {

    public Empleado(int id, String nombre, String email, String telefono, String direccion) {
        super(id, nombre, email, telefono, direccion);
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

            // Insertar en Empleado
            String sqlEmpleado = "INSERT INTO Empleado (id_persona) VALUES (?)";
            PreparedStatement psEmpleado = conn.prepareStatement(sqlEmpleado);
            psEmpleado.setInt(1, getId());
            psEmpleado.executeUpdate();

            System.out.println("Empleado guardado en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar empleado en la base de datos.");
            e.printStackTrace();
        }
    }
}
