package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;


/**
 * Controlador encargado de gestionar las operaciones relacionadas con los empleados.
 * Actúa como intermediario entre la vista (UI) y la capa DAO.
 */

public class ControladorEmpleado {

    /**
     * Realiza el proceso de inicio de sesión de un empleado.
     * Valida las credenciales (ID y contraseña) contra la base de datos.
     */

    public static Empleado iniciarSesion(int id, String contraseña) {
        if (contraseña == null || contraseña.isEmpty()) return null;
        return EmpleadoDAO.iniciarSesion(id, contraseña);
    }

    /**
     * Obtiene un empleado específico a partir de su ID.
     * Se utiliza, por ejemplo, al recuperar información desde una compra.
     */

    public static Empleado obtenerPorId(int id) {
        return EmpleadoDAO.obtenerPorId(id);
    }


    /**
     * Devuelve un empleado aleatorio disponible en la base de datos.
     * Usado para asignar la compra a un empleado de forma simple.
     */

    public static Empleado obtenerEmpleadoAleatorio() {
        return EmpleadoDAO.obtenerEmpleadoAleatorio();
    }
}
