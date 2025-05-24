package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;

import javax.swing.*;
import java.util.regex.Pattern;

/**
 * Controlador que gestiona la lógica relacionada con los clientes.
 * Incluye validación de datos, registro y eliminación.
 */
public class ControladorCliente {

    public static Cliente iniciarSesion(int id, String contraseña) {
        return ClienteDAO.iniciarSesion(id, contraseña);
    }

    public static boolean eliminarCliente(int id) {
        return ClienteDAO.eliminarCliente(id);
    }

    /**
     * Valida todos los campos de un cliente antes de registrarlo.
     * Comprueba nombre, email, teléfono, dirección y contraseña.
     * Muestra mensajes de error si algún dato no es válido.
     */
    public static boolean validarCliente(Cliente cliente) {
        String nombre = cliente.getNombre();
        String email = cliente.getEmail();
        String telefono = cliente.getTelefono();
        String direccion = cliente.getDireccion();
        String contraseña = cliente.getContraseña();

        // Validar nombre
        if (nombre == null || nombre.trim().isEmpty() || nombre.length() > 50) {
            mostrarError("El nombre no puede estar vacío ni superar los 50 caracteres.");
            return false;
        }

        // Validar email
        if (email == null || !email.matches("^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$")) {
            mostrarError("El email no tiene un formato válido.");
            return false;
        }

        // Validar teléfono (9 dígitos numéricos)
        if (!telefono.matches("^\\d{9}$")) {
            mostrarError("El teléfono debe contener exactamente 9 dígitos.");
            return false;
        }

        // Validar dirección
        if (direccion == null || direccion.trim().isEmpty() || direccion.length() > 150) {
            mostrarError("La dirección no puede estar vacía ni superar los 150 caracteres.");
            return false;
        }

        // Validar contraseña
        if (contraseña == null || !Pattern.compile("^(?=.*[A-Z])(?=.*\\d).{6,}$").matcher(contraseña).matches()) {
            mostrarError("La contraseña debe tener al menos una mayúscula, un número y 6 caracteres.");
            return false;
        }

        return true;
    }

    private static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
    }
}
