package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;

public class ControladorCliente {

    public static boolean registrarCliente(Cliente cliente) {
        // Lógica de negocio → validaciones
        if (cliente.getEmail().isEmpty() || cliente.getNombre().isEmpty()) return false;
        if (cliente.getContraseña().length() < 6) return false;

        try {
            cliente.guardarEnBD(); // Esto llama internamente a DAO
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Cliente iniciarSesion(int id, String contraseña) {
        return ClienteDAO.iniciarSesion(id, contraseña);
    }

    public static boolean eliminarCliente(int id) {
        return ClienteDAO.eliminarCliente(id);
    }
}
