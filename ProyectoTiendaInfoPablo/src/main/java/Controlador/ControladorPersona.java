package Controlador;

import Modelo.Persona;

public class ControladorPersona {

    public void registrarPersona(Persona persona) {
        try {
            persona.guardarEnBD();
            System.out.println("Persona registrada con éxito: " + persona.getNombre());
        } catch (Exception e) {
            System.out.println("Error al registrar persona: " + persona.getNombre());
            e.printStackTrace();
        }
    }
}

