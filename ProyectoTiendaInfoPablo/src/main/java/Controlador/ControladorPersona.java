package Controlador;

import Modelo.Persona;

/**
 * Controlador encargado de gestionar operaciones básicas relacionadas con personas.
 * Es útil como clase base cuando se quiere trabajar con Cliente o Empleado,
 * ya que ambos heredan de Persona.
 */
public class ControladorPersona {

    /**
     * Valida que los datos esenciales de la persona no estén vacíos o nulos.
     */
    public static boolean validarDatos(Persona persona) {
        if (persona.getNombre() == null || persona.getNombre().trim().isEmpty()) return false;
        if (persona.getEmail() == null || persona.getEmail().trim().isEmpty()) return false;
        if (persona.getTelefono() == null || persona.getTelefono().trim().isEmpty()) return false;
        if (persona.getDireccion() == null || persona.getDireccion().trim().isEmpty()) return false;

        return true;
    }

    /**
     * Intenta registrar una persona en la base de datos.
     * Primero valida que los datos no estén vacíos.
     */
    public static boolean registrarPersona(Persona persona) {
        if (!validarDatos(persona)) return false;

        try {
            persona.guardarEnBD(); // Llama al método interno del modelo
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
