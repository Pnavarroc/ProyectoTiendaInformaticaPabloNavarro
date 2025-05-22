package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;

public class ControladorEmpleado {

    public static Empleado iniciarSesion(int id, String contraseña) {
        if (contraseña == null || contraseña.isEmpty()) return null;
        return EmpleadoDAO.iniciarSesion(id, contraseña);
    }

    public static Empleado obtenerPorId(int id) {
        return EmpleadoDAO.obtenerPorId(id);
    }

    public static Empleado obtenerEmpleadoAleatorio() {
        return EmpleadoDAO.obtenerEmpleadoAleatorio();
    }
}
