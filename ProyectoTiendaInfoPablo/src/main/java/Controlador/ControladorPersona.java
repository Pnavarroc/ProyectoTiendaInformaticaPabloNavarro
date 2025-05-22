package Controlador;

import Modelo.Persona;

public class ControladorPersona {

    public static boolean validarDatos(Persona persona) {
        if (persona.getNombre() == null || persona.getNombre().trim().isEmpty()) return false;
        if (persona.getEmail() == null || persona.getEmail().trim().isEmpty()) return false;
        if (persona.getTelefono() == null || persona.getTelefono().trim().isEmpty()) return false;
        if (persona.getDireccion() == null || persona.getDireccion().trim().isEmpty()) return false;

        return true;
    }

    public static boolean registrarPersona(Persona persona) {
        if (!validarDatos(persona)) return false;

        try {
            persona.guardarEnBD();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

